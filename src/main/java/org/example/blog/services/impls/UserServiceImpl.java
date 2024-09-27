package org.example.blog.services.impls;

import org.example.blog.dtos.authdtos.RegisterDto;
import org.example.blog.dtos.userdtos.UserDashboardListDto;
import org.example.blog.models.UserEntity;
import org.example.blog.repositories.UserRepository;
import org.example.blog.services.EmailService;
import org.example.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    @Override
    public boolean register(RegisterDto registerDto) {
        UserEntity user = userRepository.findByEmail(registerDto.getEmail());
        if (user != null) {
            return false;
        }
        String hashPassword = bCryptPasswordEncoder.encode(registerDto.getPassword());
        String token = bCryptPasswordEncoder.encode(registerDto.getEmail());
        UserEntity newUser = modelMapper.map(registerDto, UserEntity.class);

        newUser.setEmailConfirmed(false);
        newUser.setPassword(hashPassword);
        newUser.setConfirmationToken(token);

        userRepository.save(newUser);
        emailService.sendConfirmationEmail(registerDto.getEmail(),token);
        return true;
    }

    @Override
    public boolean confirmEmail(String email, String token) {
        UserEntity findUser = userRepository.findByEmail(email);
        if (findUser.getConfirmationToken().equals(token) && findUser!=null){
            findUser.setEmailConfirmed(true);
            userRepository.saveAndFlush(findUser);
            return true;
        }
        return false;
    }

    @Override
    public List<UserDashboardListDto> getDashboardUsers() {
        List<UserEntity> findUsers = userRepository.findAll();
        List<UserDashboardListDto> users = findUsers.stream().map(user->modelMapper.map(user,UserDashboardListDto.class)).collect(Collectors.toList());
        return users;
    }
}
