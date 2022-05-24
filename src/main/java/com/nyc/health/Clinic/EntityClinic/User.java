package com.nyc.health.Clinic.EntityClinic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
  //  @Column(nullable = false)
    private String username;
 //   @Column(nullable = false)
    private String password;
    private String email;

}
