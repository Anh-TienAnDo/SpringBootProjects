package com.ptit.product_search.entity.user;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import com.ptit.product_search.entity.base.BaseEntity;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity{

    @Field(name = "fname")
    private String fname;

    @Field(name = "lname")
    private String lname;

    @Field(name = "gender")
    private String gender;

    @Field(name = "birthday")
    private Date birthday;

    @Field(name = "location_name")
    private String locationName;

    @Field(name = "account")
    private Account account;

    @Field(name = "avatar")
    private String avatar;

    @Field(name = "address_ids")
    private List<Integer> addressIds;

    @Field(name = "is_active")
    private boolean isActive = true;

    
}
