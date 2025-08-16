package org.example;
import lombok.Data;

@Data
public class UserDTO {
    private String email;

    private String userType; // e.g., "voter" or "candidate"

    public UserDTO(String emailAddress, String userType) {
        this.email=emailAddress;

        this.userType=userType;
    }
}
