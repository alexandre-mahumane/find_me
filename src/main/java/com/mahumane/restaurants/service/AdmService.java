package com.mahumane.restaurants.service;

import com.mahumane.restaurants.domain.users.Adm;
import com.mahumane.restaurants.domain.users.User;
import com.mahumane.restaurants.dto.request.UserRequestDto;
import com.mahumane.restaurants.dto.response.UserResponseDto;
import com.mahumane.restaurants.repository.AdmRepository;
import com.mahumane.restaurants.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdmService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final AdmRepository admRepository;

    public List<UserResponseDto> show(){
        List<Adm> administrators = this.admRepository.findAll();

        return administrators.stream().map(
                (item) -> new UserResponseDto(
                        item.getUser().getUsername(),
                        item.getUser().getEmail()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void register(UserRequestDto dto){
        this.userService.register(dto);

        User user = this.userRepository.findUserByUsername(dto.username());

        Adm adm = new Adm();

        adm.setUser(user);

        this.admRepository.save(adm);
    }

}
