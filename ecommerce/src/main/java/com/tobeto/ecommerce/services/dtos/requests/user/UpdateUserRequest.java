package com.tobeto.ecommerce.services.dtos.requests.user;

import com.tobeto.ecommerce.entities.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private UserType type;
}
