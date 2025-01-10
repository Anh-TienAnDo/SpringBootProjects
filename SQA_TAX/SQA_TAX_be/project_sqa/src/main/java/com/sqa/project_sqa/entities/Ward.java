package com.sqa.project_sqa.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wards")
public class Ward {
    @Id
    @Column(name = "code")
    private String code;
    private String name;
    @Column(name = "name_en")
    private String nameEn;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "full_name_en")
    private String fullNameEn;
    @Column(name = "code_name")
    private String codeName;

    @ManyToOne
    @JoinColumn(name = "administrative_unit_id",referencedColumnName = "id", insertable = false, updatable = false)
    private AdministrativeUnit administrativeUnit;
    @ManyToOne
    @JoinColumn(name = "district_code",referencedColumnName = "code", insertable = false, updatable = false)
    private District district;

}
