package com.sqa.project_sqa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
//@Table(name="Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "UserName", unique = true, nullable = false)
    private String userName;

    @Column(name = "Password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "Email", unique = true, nullable = false)
    private String email;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Role")
    @Enumerated(EnumType.STRING)
    private ERole role ;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private List<HoaDon> hoaDonList;

}
