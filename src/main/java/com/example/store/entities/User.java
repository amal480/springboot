package com.example.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
    @OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval = true )
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

    @ManyToMany
    @JoinTable(
            name="user_tags",
            joinColumns =@JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="tag_id")
    )
    @Builder.Default
    private Set<Tag> tags=new HashSet<>();

    public void addTag(String tagname){
        var tag=new Tag(tagname);
        tags.add(tag);
        tag.getUsers().add(this);
    }


    @OneToOne(mappedBy = "user",cascade = CascadeType.REMOVE)
    private Profile profile;

    @ManyToMany
    @JoinTable(name="wishlist",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> wishlist=new HashSet<>();

    public void addFavouriteProduct(Product product) {
        wishlist.add(product);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "email = " + email + ")";
    }
}
