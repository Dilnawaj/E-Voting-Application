package org.example.service;

import org.example.UserDTO;
import org.example.entity.User;
import org.example.exception.CustomException;
import org.example.model.UserModel;
import org.example.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

    public String login(UserModel userModel) {


        Optional<User> userOpt= userRepo.findByEmail(userModel.getEmail());
        if(userOpt.isPresent())
        {
            User user = userOpt.get();
            if(user.getPassword().equals(user.getPassword()))
            {
                return "User has successfully login";
            }
            throw new CustomException(HttpStatus.BAD_REQUEST.value(),"Invalid password!");
        }
        throw  new CustomException(HttpStatus.NOT_FOUND.value(),"Invalid credentials");

    }
}
