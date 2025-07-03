package org.example;
import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String password;
    private String userType; // e.g., "voter" or "candidate"

    public UserDTO(String emailAddress, String password, String userType) {
        this.email=emailAddress;
        this.password=password;
        this.userType=userType;
    }
}
