package com.restaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "personals")
public class PersonalData{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    @Pattern(regexp = "[A-Za-zżźćńółęąśŻŹĆŚŁÓ]+", message = "name is not valid")
    private String name;
    @Column(name = "SURNAME")
    @Pattern(regexp = "[A-Za-zżźćńółęąśŻŹĆŚŁÓ]+", message = "surname is not valid")
    private String surname;
    @Column(name = "EMAIL")
    @Email
    @NotBlank
    private String email;
    @Column(name = "PHONE_NUMBER")
    @Pattern(regexp = "\\d{9}",
            message = "Phone number has to consist exactly 9 numbers")
    private String phoneNumber;
    @JsonBackReference
    @OneToOne(mappedBy = "personalData", fetch = FetchType.LAZY)
    //@JoinColumn(name = "order_id")
    private Order order;


    public PersonalData(String name, String surname, String email, String phoneNumber){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PersonalData that = (PersonalData) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
