package com.sparklecow.cowradio.services;

import com.sparklecow.cowradio.config.jwt.JwtUtils;
import com.sparklecow.cowradio.models.dtos.user.AuthLogin;
import com.sparklecow.cowradio.models.dtos.user.AuthRequest;
import com.sparklecow.cowradio.entities.user.ActivationCode;
import com.sparklecow.cowradio.entities.user.User;
import com.sparklecow.cowradio.repositories.ActivationCodeRepository;
import com.sparklecow.cowradio.repositories.UserRepository;
import com.sparklecow.cowradio.services.email.EmailService;
import com.sparklecow.cowradio.services.mappers.UserMapper;
import com.sparklecow.cowradio.models.enums.EmailTemplateName;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImp implements AuthService{

    private final Logger logger = LoggerFactory.getLogger(AuthServiceImp.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ActivationCodeRepository activationCodeRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    @Value("${spring.application.mailing.activation-url}")
    private String activationUrl;


    @Transactional
    @Override
    public Integer register(AuthRequest request) throws MessagingException {
        //Create a User with the request information
        User user = userRepository.save(userMapper.toUser(request));
        sendVerificationCode(user);
        return user.getId();
    }

    public void sendVerificationCode(User user) throws MessagingException {
        final String code = saveAndGenerateActivationCode(user);
        emailService.sendEmail(user.getEmail(), "Account activation", user.getUsername(),
                code, activationUrl, EmailTemplateName.ACTIVATION_CODE);
    }

    public String saveAndGenerateActivationCode(User user){
        final String code = generateVerificationCode(6);
        ActivationCode activationCode = ActivationCode
                .builder()
                .code(code)
                .user(user)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .build();
        activationCodeRepository.save(activationCode);
        return code;
    }

    //Create a code for validate the user's email
    public String generateVerificationCode(Integer length){
        final String characters = "1234567890";
        StringBuilder code = new StringBuilder(length);
        SecureRandom random = new SecureRandom();
        for(int i=0;i<length;i++){
            int randomIndex = random.nextInt(characters.length());
            code.append(characters.charAt(randomIndex));
        }
        return code.toString();
    }

    @Override
    public String login(AuthLogin loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
        authenticationManager.authenticate(authToken);
        logger.info("User authenticated: "+loginRequest.username());
        UserDetails user = userRepository.findByUsername(loginRequest.username()).orElseThrow(()->
                new UsernameNotFoundException("User with username "+loginRequest.username()+" not found"));
        return jwtUtils.createToken(user);
    }

    @Transactional
    @Override
    public void validateCode(String code) throws MessagingException {
        ActivationCode activationCode = activationCodeRepository.findByCode(code).orElseThrow(
                //TODO Implement a better exception
                () -> new RuntimeException(""));
        if(activationCode.getValidatedAt() != null){
            logger.error("Activation code has been already activated");
            throw new RuntimeException("");
        }
        if(activationCode.getExpiresAt().isBefore(LocalDateTime.now())){
            sendVerificationCode(activationCode.getUser());
            logger.error("Activation code expired, new code sent to user");
            //TODO Implement a better exception
            throw new RuntimeException("");
        }
        activationCode.setValidatedAt(LocalDateTime.now());
        User user = activationCode.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        activationCodeRepository.save(activationCode);
    }
}
