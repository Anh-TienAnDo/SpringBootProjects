package com.ptit.product_search.entity.user;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Field(name = "username")
    private String username;

    @Field(name = "password")
    private String password;

    @Field(name = "email")
    private String email;

    @Field(name = "is_staff")
    private boolean isStaff = false;

    @Field(name = "is_superuser")
    private boolean isSuperuser = false;

}
