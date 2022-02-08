package com.hibernate.entity;

import com.hibernate.converter.BirthdayConverter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", schema = "public")
@TypeDef(name = "jsonbName", typeClass = JsonBinaryType.class)
public class User {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "birth_date")
//    @Convert(converter = BirthdayConverter.class)
    private Birthday birthDate;

    @Column(name = "info")
    @Type(type = "jsonbName")
    private String info;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
}
