package com.example.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,name="name")
    private String name;

    @Column(nullable=false,name="email")
    private String email;

    @Column(nullable=false,name="password")
    private String password;

//    user field inside the Address class is the owning side — it has the foreign key (user_id)
    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List <Address> addresses=new ArrayList<>();

    public void addAddress(Address address){
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(Address address){
        addresses.remove(address);
        address.setUser(null);
    }


}
