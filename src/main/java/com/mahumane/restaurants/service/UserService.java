package com.mahumane.restaurants.service;

import com.mahumane.restaurants.domain.users.User;
import com.mahumane.restaurants.dto.request.AuthRequestDto;
import com.mahumane.restaurants.dto.request.UserRequestDto;
import com.mahumane.restaurants.dto.response.TokenResponseDto;
import com.mahumane.restaurants.dto.response.UserResponseDto;
import com.mahumane.restaurants.exception.ConflictException;
import com.mahumane.restaurants.exception.NotFoundException;
import com.mahumane.restaurants.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private  final UserRepository userRepository;

    public TokenResponseDto login(AuthRequestDto dto){
        return  new TokenResponseDto("");
    }


    public List<UserResponseDto> show(){
        List<User> users = this.userRepository.findAll();

        return users.stream()
                .map((item)->
                        new UserResponseDto(
                                item.getUsername(),
                                item.getEmail()))
                .collect(Collectors.toList());

    }


    @Transactional
    public void register(UserRequestDto dto){
        User checkUsername = this.userRepository.findUserByUsername(dto.username());
        User checkEmail = this.userRepository.findUserByEmail(dto.email());

        if(checkUsername != null){
            throw  new ConflictException("Username already exits");
        }

        if(checkEmail != null){
            throw  new ConflictException("Email already exits");
        }

        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(dto.password());

        this.userRepository.save(user);

    }

    @Transactional
    public void update(UserRequestDto dto){
        User user = this.userRepository
                .findById(1L)
                .orElseThrow(
                        ()-> new NotFoundException("User not found")
                );

        if (!user.getEmail().equals(dto.email())){
            user.setEmail(dto.email());
        }

        if (!user.getUsername().equals(dto.username())){
            user.setUsername(dto.username());
        }

        //Encode process first
        user.setPassword(dto.password());

        this.userRepository.save(user);

    }

    @Transactional
    public void remove(){
        User user = this.userRepository
                .findById(1L)
                .orElseThrow(
                        ()-> new NotFoundException("User not found")
                );

        this.userRepository.delete(user);
    }

}
