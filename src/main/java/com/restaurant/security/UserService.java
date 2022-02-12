package com.restaurant.security;

import com.restaurant.model.Address;
import com.restaurant.model.PersonalData;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with username: " + username));
    }

    public User createNewUser(String username, String password, Role role) {
        User user = new User(null, null, null,
                username, passwordEncoder.encode(password), role);
        return this.userRepository.save(user);
    }


    public Address createAddress(User user, Address address) {
        address.setUser(user);
        user.setAddress(address);
        User updatedUser = this.userRepository.save(user);
        return updatedUser.getAddress();
    }

    public Address changeAddress(String username, Address address){
        User user = (User) loadUserByUsername(username);
        Address addressToUpdate = user.getAddress();
        if(address.getApartmentNumber() != null)
            addressToUpdate.setApartmentNumber(address.getApartmentNumber());
        if(address.getBuildingNumber() != null)
            addressToUpdate.setBuildingNumber(address.getBuildingNumber());
        if(address.getCity() != null)
            addressToUpdate.setCity(address.getCity());
        if(address.getFloor() != null)
            addressToUpdate.setFloor(address.getFloor());
        if(address.getGateCode() != null)
            addressToUpdate.setGateCode(address.getGateCode());
        if(address.getComment() != null)
            addressToUpdate.setComment(address.getComment());
        if(address.getPostCode() != null)
            addressToUpdate.setPostCode(address.getPostCode());
        if(address.getStreet() != null)
            addressToUpdate.setStreet(address.getStreet());
        user.setAddress(addressToUpdate);
        this.userRepository.save(user);
        return user.getAddress();
    }

    public PersonalData createPersonalData(User user, PersonalData personalData){
        personalData.setUser(user);
        user.setPersonalData(personalData);
        User updatedUser = this.userRepository.save(user);
        return updatedUser.getPersonalData();
    }

    public PersonalData changePersonalData(String username, PersonalData personalData) {
        User user = (User) loadUserByUsername(username);
        PersonalData personalDataToUpdate = user.getPersonalData();
        if(personalData.getEmail() != null)
            personalDataToUpdate.setEmail(personalData.getEmail());
        if(personalData.getName() != null)
            personalDataToUpdate.setName(personalData.getName());
        if(personalData.getPhoneNumber() != null)
            personalDataToUpdate.setPhoneNumber(personalData.getPhoneNumber());
        if(personalData.getSurname() != null)
            personalDataToUpdate.setSurname(personalData.getSurname());
        user.setPersonalData(personalDataToUpdate);
        this.userRepository.save(user);
        return user.getPersonalData();
    }

    public Iterable<User> findAll() {
        return this.userRepository.findAll();
    }

    public User deleteUser(String username) {
        User user = (User) loadUserByUsername(username);
        this.userRepository.delete(user);
        return user;
    }
}
