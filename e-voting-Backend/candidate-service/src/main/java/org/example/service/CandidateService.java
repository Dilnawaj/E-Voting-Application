package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.UserDTO;
import org.example.config.MessageProducer;
import org.example.entity.Candidate;
import org.example.exception.CustomException;
import org.example.model.CandidateModel;
import org.example.repo.CandidateRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateService {
    private final CandidateRepo candidateRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${candidate.topic}")
    private String topicName;
    @Value("${user.topic}")
    private String userTopic;

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    public CandidateService(CandidateRepo candidateRepo) {
        this.candidateRepo = candidateRepo;
    }


    public CandidateModel addCandidate(CandidateModel candidateModel) throws JsonProcessingException {

        Optional<Candidate> candidateOpt=candidateRepo.findByEmailAddress(candidateModel.getEmailAddress());
if(candidateOpt.isEmpty())
{
    Candidate candidate = modelMapper.map(candidateModel, Candidate.class);
    candidate.setTotalVotes(0);
    candidate=candidateRepo.save(candidate);

    messageProducer.sendMessage(topicName,objectMapper.writeValueAsString(candidate));
    UserDTO userModel = new UserDTO(candidate.getEmailAddress(),candidate.getPassword(),"CANDIDATE");
    messageProducer.sendMessage(userTopic,objectMapper.writeValueAsString(userModel));
    return this.modelMapper.map(candidate, CandidateModel.class);
}
      throw  new CustomException(HttpStatus.BAD_REQUEST.value(),"Candidate already exist with this emailAddress : " + candidateModel.getEmailAddress());
    }


    public CandidateModel updateCandidateVote(Integer candidateId) {


        Optional<Candidate> candidate = candidateRepo.findById(candidateId);
        if(candidate.isPresent())
        {
            candidate.ifPresent(value -> value.setTotalVotes(value.getTotalVotes() + 1));
            System.out.println("Candidate id in candidate Service "+candidateId);
            return this.modelMapper.map(candidateRepo.save(candidate.get()), CandidateModel.class);

        }
        throw  new CustomException(HttpStatus.NOT_FOUND.value(),"Candidate not found with this id : " + candidateId);
    }

    public List<CandidateModel> allCandidatesOfParty(String partyName) {
        List<Candidate> candidates = candidateRepo.findByParty(partyName);
        return candidates.stream().map(c -> this.modelMapper.map(c, CandidateModel.class)).toList();
    }


    public String deleteByCandidateId(Integer candidateId) {
        if (!candidateRepo.existsById(candidateId)) {

            throw  new CustomException(HttpStatus.NOT_FOUND.value(),"Candidate not found with this id : " + candidateId);
        }
        candidateRepo.deleteById(candidateId);
        return ("Candidate has been successfully deleted");
    }

    public CandidateModel getCandidateById(Integer candidateId) {
        return this.modelMapper.map(candidateRepo.findById(candidateId).orElseThrow(() -> new RuntimeException("Candidate with ID " + candidateId + " not found")), CandidateModel.class);
    }

    public List<CandidateModel> getAllCandidates() {

        return candidateRepo.findAll().stream().map(e -> this.modelMapper.map(e, CandidateModel.class)).collect(Collectors.toList());

    }
}
