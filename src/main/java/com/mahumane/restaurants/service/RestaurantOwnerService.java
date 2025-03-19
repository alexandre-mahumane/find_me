package com.mahumane.restaurants.service;

import com.mahumane.restaurants.domain.users.RestaurantsOwners;
import com.mahumane.restaurants.domain.users.User;
import com.mahumane.restaurants.dto.request.RestaurantOwnerRequestDto;
import com.mahumane.restaurants.exception.ConflictException;
import com.mahumane.restaurants.exception.NotFoundException;
import com.mahumane.restaurants.repository.RestaurantOwnerRepository;
import com.mahumane.restaurants.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestaurantOwnerService {

    private final RestaurantOwnerRepository ownerRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Transactional
    public void register(RestaurantOwnerRequestDto dto){
        this.userService.register(dto.user());
        var checkPhone = this.ownerRepository.findOwnerByPhone(dto.phone());

        if (checkPhone != null){
            throw new ConflictException("Phone number already exist");
        }

        User user = this.userRepository.findUserByUsername(dto.user().username());

        RestaurantsOwners owner = new RestaurantsOwners();
        owner.setUser(user);
        owner.setPhone(dto.phone());
    }

    @Transactional
    public void update(RestaurantOwnerRequestDto dto){
        this.userService.update(dto.user());

        var owner = this.ownerRepository.findOwnerByPhone(dto.phone());

        if (owner == null){
            throw new NotFoundException("Restaurant owner not found");
        }

        owner.setPhone(dto.phone());

        this.ownerRepository.save(owner);
    }


}
