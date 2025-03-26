package com.sadiqov.tech_app_three.service;

import com.sadiqov.tech_app_three.dto.request.AuthenticationRequestDto;
import com.sadiqov.tech_app_three.dto.request.UserRequestDTO;
import com.sadiqov.tech_app_three.dto.response.CommonResponse;
import com.sadiqov.tech_app_three.dto.response.Status;
import com.sadiqov.tech_app_three.dto.response.StatusCode;
import com.sadiqov.tech_app_three.dto.response.UserResponseDto;
import com.sadiqov.tech_app_three.entity.TechUser;
import com.sadiqov.tech_app_three.exception.NoSuchUserExits;
import com.sadiqov.tech_app_three.exception.UserAlreadyExit;
import com.sadiqov.tech_app_three.repository.UserRepository;
import com.sadiqov.tech_app_three.util.DTOUtil;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService {

    @Autowired
    DTOUtil dtoUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    public CommonResponse<?> saveUser(UserRequestDTO userRequestDTO) {
        dtoUtil.isValid(userRequestDTO);
        if (userRepository.findBYPin(userRequestDTO.getPin()).isPresent()) {
            throw UserAlreadyExit.builder().commonResponse(CommonResponse.builder().

                    status(Status.builder().statusCode(StatusCode.USER_EXITS).
                            message("User with pin" + userRequestDTO.getPin() +
                                    " is Exits. Please enter a pin  that has not been registered before."
                            ).
                            build()).build()).build();
        }

        TechUser user = TechUser.builder().
                name(userRequestDTO.getName()).
                surName(userRequestDTO.getSurName())
                .password(passwordEncoder.encode(userRequestDTO.getPassword()))
                .pin(userRequestDTO.getPin()).role("ROLE_USER").build();

        user.addAccountToUser(userRequestDTO.getAccountRequestDTOList());

        return CommonResponse.builder().
                status(Status.builder().statusCode(StatusCode.SUCCES)
                        .message("User created Successfully !!!").build()).
                data(UserResponseDto.entityResponse(userRepository.save(user))).build();
    }

    public CommonResponse<?> loginUser(AuthenticationRequestDto authenticationRequestDto) {
        dtoUtil.isValid(authenticationRequestDto);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (authenticationRequestDto.getPin(),
                            authenticationRequestDto.getPassword()));
        } catch (Exception e) {


            throw NoSuchUserExits.builder().commonResponse(CommonResponse.builder().
                    status(Status.builder().statusCode(StatusCode.USER_NOT_EXITS).
                            message("pin: " + authenticationRequestDto.getPin() + " or password: "
                                    + authenticationRequestDto.getPassword() + " is wrong.")
                            .build()).build()).build();
        }
        return CommonResponse.builder().data(authenticationRequestDto).
                status(Status.builder().statusCode(StatusCode.SUCCES).
                        message("Welcome to our Fin_App_Three").build()).build();
    }

}
