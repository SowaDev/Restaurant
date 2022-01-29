package com.restaurant.security;

import com.restaurant.model.Address;
import com.restaurant.model.PersonalData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with username: " + username));
    }

    public Address createAddress(User user, Address address) {
        address.setUser(user);
        user.setAddress(address);
        User updatedUser = this.userRepository.save(user);
        return updatedUser.getAddress();
    }

    public Address changeAddress(User user, Address address){
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

    public PersonalData changePersonalData(User user, PersonalData personalData) {
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
}
