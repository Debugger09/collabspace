package com.ime.api.service.dto;

import com.ime.api.model.enums.ROLE;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String firstName;   
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String job;
    private boolean status = true;
    private Date createdAt;
    private Date updatedAt;   
    private ROLE role;
    

}
