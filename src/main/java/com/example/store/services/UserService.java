package com.example.store.services;

import com.example.store.entities.User;
import com.example.store.repositories.AddressRepository;
import com.example.store.repositories.ProfileRepository;
import com.example.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {
    private final EntityManager entityManager;
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public void showEntityStates(){
        var user= User.builder()
                .name("John")
                .email("johnmail@mail.com")
                .password("password")
                .build();
        if (entityManager.contains(user)){
            System.out.println("Persistent");
        }
        else{
            System.out.println("Transient or detached");
        }
    userRepository.save(user);
        if (entityManager.contains(user)){
            System.out.println("Persistent");
        }
        else{
            System.out.println("Transient or detached");
        }
    }

    @Transactional
    public void showRelatedEntities(){
        var profile=profileRepository.findById(4L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
    }

    public void fetchAddress(){
        var address=addressRepository.findById(1L).orElseThrow();
    }
}
