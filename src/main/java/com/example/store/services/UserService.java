


package com.example.store.services;

import com.example.store.entities.Address;
import com.example.store.entities.Category;
import com.example.store.entities.Product;
import com.example.store.entities.User;
import com.example.store.repositories.*;
import com.example.store.repositories.specifications.ProductSpec;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class UserService {
    private final EntityManager entityManager;
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

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

    public void persistRelated(){
        var user=User.builder()
                .name("doe")
                .email("doe@mail")
                .password("password")
                .build();
        var address= Address.builder()
                .street("street")
                .city("city")
                .state("state")
                .zip("zip")
                .build();
        user.addAddress(address);
        userRepository.save(user);
    }
    @Transactional
    public void deleteRelated(){
//        userRepository.deleteById(4L);
        var user=userRepository.findById(7L).orElseThrow();
        var address =user.getAddresses().getFirst();
        user.removeAddress(address);
        userRepository.save(user);
    }

//    @Transactional
//    public void manageProducts(){
//        var category=categoryRepository.findById((byte)1).orElseThrow();
//
//
//        var product= Product.builder()
//                .name("product 2")
//                .description("description 2")
//                .price(BigDecimal.valueOf(10.99))
//                .category(category)
//                .build();
//        productRepository.save(product);
//    }

//    @Transactional
//    public void manageProducts(){
//        var user=userRepository.findById(4L).orElseThrow();
//        var products=productRepository.findAll();
//        products.forEach(user::addFavouriteProduct);
//        userRepository.save(user);
//    }

    @Transactional
    public void manageProducts() {
        productRepository.deleteById(4L);
    }

    @Transactional
    public void updateProductPrices(){
        productRepository.updatePriceByCategory(BigDecimal.valueOf(10),(byte)2);
    }

    @Transactional
    public void fetchProducts(){
        var products=productRepository.findProducts(BigDecimal.valueOf(1),BigDecimal.valueOf(15));
        products.forEach(System.out::println);
    }

    @Transactional
    public void fetchUsers(){
        var users=userRepository.findAllWithTags();
        users.forEach(u ->{
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        } );
    }

    @Transactional
    public void printLoyalProfiles() {
        var users = userRepository.findLoyalUsers(2);
        users.forEach(p -> System.out.println(p.getId() + ": " + p.getEmail()));
    }



    public void fetchProductsByCriteria() {
        var products = productRepository.findProductsByCriteria(null, BigDecimal.valueOf(1), BigDecimal.valueOf(15));
        products.forEach(System.out::println);
    }

    public void fetchProductsBySpecification(String name,BigDecimal minPrice,BigDecimal maxPrice) {
        Specification<Product> spec = Specification.where(null);

        if (name!=null){
            spec=spec.and(ProductSpec.hasName(name));
        }
        if (minPrice!=null){
            spec=spec.and(ProductSpec.hasPriceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice!=null){
            spec=spec.and(ProductSpec.hasPriceLessThanOrEqualTo(maxPrice));
        }
        productRepository.findAll(spec).forEach(System.out::println);
    }
}
