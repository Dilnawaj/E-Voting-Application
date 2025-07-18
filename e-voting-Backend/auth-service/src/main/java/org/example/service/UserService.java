package org.example.service;

import org.example.UserDTO;
import org.example.entity.User;
import org.example.exception.CustomException;
import org.example.model.UserModel;
import org.example.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;


    public void registerUser(UserDTO userDto) {

        User user =new User();
        user.setUserType(userDto.getUserType());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepo.save(user);
    }

    public Map<String, String> login(UserModel userModel) {


        Optional<User> userOpt= userRepo.findByEmail(userModel.getEmail());
        if(userOpt.isPresent())
        {
            User user = userOpt.get();
            if(user.getPassword().equals(user.getPassword()))
            {
                Map<String, String> response = new HashMap<>();
               response.put("userType", user.getUserType());
               response.put("userName", getName(user.getEmail()));
                return response;
            }
            throw new CustomException(HttpStatus.BAD_REQUEST.value(),"Invalid password!");
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
