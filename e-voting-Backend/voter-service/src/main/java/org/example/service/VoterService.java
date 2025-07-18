package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.UserDTO;
import org.example.config.MessageProducer;
import org.example.entity.Voter;
import org.example.exception.CustomException;
import org.example.model.VoterModel;
import org.example.repo.VoterRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoterService {

    @Autowired
    private VoterRepo voterRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Value("${server.port}")
    private String port;
    @Value("${user.topic}")
    private String userTopic;
    @Autowired
    private MessageProducer messageProducer;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VoterKafkaProducer voterKafkaProducer;
    public VoterModel addVoter(VoterModel voterModel) throws JsonProcessingException {

     Optional<Voter> voterOpt=   voterRepo.findByAadharNumberOrEmailAddress(voterModel.getAadharNumber(),voterModel.getEmailAddress());
   if(voterOpt.isEmpty())
   {
       Voter voter= this.modelMapper.map(voterModel,Voter.class);
       voter.setRegisteredAt(LocalDateTime.now());
       voter =voterRepo.save(voter);
       voterKafkaProducer.sendVoterRegisteredEvent(voter);

       UserDTO userModel = new UserDTO(voter.getEmailAddress(),voter.getPassword(),"VOTER");
       messageProducer.sendMessage(userTopic,objectMapper.writeValueAsString(userModel));
       return this.modelMapper.map(voter,VoterModel.class);
   }
        throw  new CustomException(HttpStatus.BAD_REQUEST.value(),"Voter already exist with the aadhar number : " + voterModel.getAadharNumber());
    }

    public boolean isEligibleToVote(String aadharNumber)
    {
        System.out.println("AADhar number Request Param wala");
        Optional<Voter> voterOpt= getVoterByAadhar(aadharNumber);
       if(voterOpt.isPresent())
       {
           Voter voter = voterOpt.get();
           return (voter.getAge()>18 && !voter.getHasVoted());
       }
      return false;

    }

  @Cacheable(value = "voterCache", key = "#aadharNumber")
    public Optional<Voter> getVoterByAadhar(String aadharNumber) {
        System.out.println("Fetching from DB for Aadhar: " + aadharNumber);
        return voterRepo.findByAadharNumber(aadharNumber);
    }
    public VoterModel markAsVoted(String aadharNumber){
        System.out.println(" markAsVoted called on port: " + port + " for Aadhar: " + aadharNumber);

        System.out.println("Voted Done");
        if(isEligibleToVote(aadharNumber))
        {
            Optional<Voter> voterOpt= getVoterByAadhar(aadharNumber);
            Voter voter = voterOpt.get();
            voter.setHasVoted(true);
          return this.modelMapper.map(voterRepo.save(voter),VoterModel.class) ;
        }
        throw  new CustomException(HttpStatus.NOT_FOUND.value(),"Voter not found with aadhar : "+aadharNumber);
    }
    public List<VoterModel> getAllVoter() {
        List<Voter> voters = voterRepo.findAll();

        return voters.stream().map(voter -> this.modelMapper.map(voter, VoterModel.class)).collect(Collectors.toList());

    }

    public VoterModel getVoterById(Integer voterId) {
        Optional<Voter> voterOpt = voterRepo.findById(voterId);
        if (voterOpt.isPresent()) {
            return this.modelMapper.map(voterOpt.get(), VoterModel.class);
        }

        throw  new CustomException(HttpStatus.NOT_FOUND.value(),"Voter does not found with this id: "+voterId);

    }
    public String deleteVoterById(Integer voterId) {
        boolean voterOpt = voterRepo.existsById(voterId);
        if (voterOpt) {
            voterRepo.deleteById(voterId);
            return "Voter has been successfully deleted";
        }
       throw  new CustomException(HttpStatus.NOT_FOUND.value(),"Voter does not found with this id: "+voterId);

    }

    public Map<String,Long>  getVoterInsight(String constituency) {
       List<Voter> voters = voterRepo.findByConstituency(constituency);
       Map<String,Long> m=new HashMap<>();
       if(!voters.isEmpty())
       {
        long totalCount=   voters.stream().count();
           long totalMales = voters.stream()
                   .filter(e -> e.getGender()=='M')
                   .count();
           long totalFemales = voters.stream()
                   .filter(e -> e.getGender()=='F')
                   .count();
           long totalHindus = voters.stream().filter(e->e.getReligion().equals("Hindu")).count();
           long totalMuslims = voters.stream().filter(e->e.getReligion().equals("Muslim")).count();
           long youngVoters= voters.stream().filter(e->e.getAge()<=30).count();
           long seniorVoters= voters.stream().filter(e->e.getAge()>=60).count();


           m.put("totalVoters",totalCount);
           m.put("averageAge",(long)voters.stream().map(e->e.getAge()).collect(Collectors.summarizingInt(e->e)).getAverage());
           m.put("totalMale",totalMales);
           m.put("malePercentage",(totalMales*100/totalCount));
           m.put("totalFemales",totalFemales);
           m.put("femalePercentage",(totalFemales*100/totalCount));
           m.put("totalHindu",totalHindus);
           m.put("hinduPercentage",(totalHindus*100/totalCount));
           m.put("totalMuslim",totalMuslims);
           m.put("muslimPercentage",(totalMuslims*100/totalCount));
           m.put("totalYoung",youngVoters);
           m.put("youngPercentage",(youngVoters*100/totalCount));
           m.put("totalSenior",seniorVoters);
           m.put("seniorPercentage",(seniorVoters*100/totalCount));




       }
       return m;
    }

    public VoterModel getVoterByEmail(String email) {


    return this.modelMapper.map( voterRepo.findByEmailAddress(email),VoterModel.class);
    }
}
