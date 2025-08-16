package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.example.UserDTO;
import org.example.entity.User;
import org.example.exception.CustomException;
import org.example.feign.AdminClient;
import org.example.feign.CandidateClient;
import org.example.feign.VoterClient;
import org.example.model.UserModel;
import org.example.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private CandidateClient candidateClient;

    @Autowired
    private VoterClient voterClient;

    @Autowired
    private AdminClient adminClient;
    public void registerUser(UserDTO userDto) {

        User user =new User();
        user.setUserType(userDto.getUserType());
        user.setEmail(userDto.getEmail());
        userRepo.save(user);
    }

    public Map<String, String> login(UserModel userModel) throws JsonProcessingException {


        Optional<User> userOpt= userRepo.findByEmail(userModel.getEmail());
        if(userOpt.isPresent())
        {
            User user = userOpt.get();

            ResponseEntity<String> responseApi = ResponseEntity.ok("");
            try {
                if (user.getUserType().equals("CANDIDATE")) {
                    responseApi = candidateClient.login(userModel);
                } else if (user.getUserType().equals("VOTER")) {
                    responseApi = voterClient.login(userModel);
                }
                else {
                    responseApi=adminClient.login(userModel);
                }


            } catch (FeignException e) {

System.out.println( e.contentUTF8());

                JsonNode rootNode = objectMapper.readTree( e.contentUTF8());

                String errorMessage = rootNode.get("error").asText();

                System.out.println(errorMessage); // prints: incorrect password

                throw new CustomException(e.status(),errorMessage);
            }

            if(responseApi.getStatusCode().value()==HttpStatus.OK.value())
            {
                Map<String, String> response = new HashMap<>();
               response.put("userType", user.getUserType());
               response.put("userName", getName(user.getEmail()));
                response.put("accessToken", responseApi.getBody());
                return response;
            }

            throw new CustomException(HttpStatus.BAD_REQUEST.value(),"Interval server error!");
        }
        throw  new CustomException(HttpStatus.NOT_FOUND.value(),"Invalid credentials");

    }
    public String getName(String name)
    {
        String temp="";
        int i=0;
        while (name.charAt(i) != '@') {
            temp += name.charAt(i);
            i++;
        }
        return  temp;
    }
}
