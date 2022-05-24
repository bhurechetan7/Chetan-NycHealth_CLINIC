package com.nyc.health.Clinic.EntityClinic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserAuthenticate {

       private String username;
       private String password;

}
