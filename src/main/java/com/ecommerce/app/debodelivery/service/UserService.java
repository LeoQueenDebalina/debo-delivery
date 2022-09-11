package com.ecommerce.app.debodelivery.service;

import com.ecommerce.app.debodelivery.common.ApiResponse;
import com.ecommerce.app.debodelivery.entity.ConfirmationToken;
import com.ecommerce.app.debodelivery.repository.ConfirmationTokenRepository;
import com.ecommerce.app.debodelivery.util.EmailSender;
import com.ecommerce.app.debodelivery.util.MessageBuilder;
import com.ecommerce.app.debodelivery.helper.Type;
import com.ecommerce.app.debodelivery.entity.User;
import com.ecommerce.app.debodelivery.exception.DataNotFoundException;
import com.ecommerce.app.debodelivery.model.UserRequest;
import com.ecommerce.app.debodelivery.model.UserResponse;
import com.ecommerce.app.debodelivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private EmailSender emailService;
    @Autowired
    private MessageBuilder messageBuilder;


    public ApiResponse addUser(UserRequest userRequest) {
        UUID uuid = UUID.randomUUID();
        String token = UUID.randomUUID().toString();
        String link = "http://localhost:4040/api/v1/registration/confirm?token=" + token;
        if (!userRepository.ifNumberIsExist(userRequest.getMobileNumber())) {
            this.userRepository.save(User.builder()
                    .userId(String.valueOf(uuid))
                    .userName(userRequest.getUserName())
                    .userEmail(userRequest.getUserEmail())
                    .password(bCryptPasswordEncoder.encode(userRequest.getPassword()))
                    .createdAt(new Date())
                    .type(Type.USER)
                    .address(userRequest.getAddress())
                    .isEmailVerified(false)
                    .mobileNumber(userRequest.getMobileNumber())
                    .isDeleted(false)
                    .build());
            this.confirmationTokenRepository.save(ConfirmationToken.builder()
                    .tokenId(token)
                    .token(token)
                    .createdAt(LocalDateTime.now())
                    .expiresAt(LocalDateTime.now().plusMinutes(15))
                    .confirmedAt(null)
                    .user(userRepository.findByMobileNumber(userRequest.getMobileNumber())).build());
            this.emailService.send(userRepository.findByMobileNumber(userRequest.getMobileNumber()).getUserEmail(),
                    messageBuilder.buildEmail(userRepository.findByMobileNumber(userRequest.getMobileNumber()).getUserName(), link),
                    "Confirm your email");
            return new ApiResponse(false, "User successfully register, Please verify Your email");
        } else {
            return new ApiResponse(true, "This phone number is already exist");
        }
    }

    public ApiResponse updateUserData(UserRequest userRequest) {
        if (userRepository.ifNumberIsExist(userRequest.getMobileNumber()) && !userRepository.getUserIsDeletedByNumber(userRequest.getMobileNumber())) {
            if (userRepository.findByMobileNumber(userRequest.getMobileNumber()).getIsEmailVerified()) {
                User newData = new User();
                String id = this.userRepository.getUserIdByNumber(userRequest.getMobileNumber());
                Optional<User> oldData = this.userRepository.findById(id);
                newData.setUserId(id);
                if (userRequest.getUserName() != "") {
                    newData.setUserName(userRequest.getUserName());
                } else {
                    newData.setUserName(oldData.get().getUserName());
                }
                if (userRequest.getUserEmail() != "") {
                    newData.setUserEmail(userRequest.getUserEmail());
                } else {
                    newData.setUserEmail(oldData.get().getUserEmail());
                }
                newData.setMobileNumber(oldData.get().getMobileNumber());
                if (userRequest.getPassword() != "") {
                    newData.setPassword(userRequest.getPassword());
                } else {
                    newData.setPassword(oldData.get().getPassword());
                }
                newData.setCreatedAt(oldData.get().getCreatedAt());
                newData.setType(oldData.get().getType());
                if (userRequest.getAddress() != "") {
                    newData.setAddress(userRequest.getAddress());
                } else {
                    newData.setAddress(oldData.get().getAddress());
                }
                newData.setIsEmailVerified(oldData.get().getIsEmailVerified());
                newData.setIsDeleted(oldData.get().getIsDeleted());
                this.userRepository.save(newData);
                return new ApiResponse(false, "Information updated successfully");
            } else {
                return new ApiResponse(true, "Email not verified");
            }
        } else {
            return new ApiResponse(true, "User not found");
        }
    }

    public ApiResponse deleteUser(String mobileNumber, Boolean cStatus) {
        if (userRepository.ifNumberIsExist(mobileNumber) && cStatus && !userRepository.getUserIsDeletedByNumber(mobileNumber)) {
            this.userRepository.deleteAccount(mobileNumber);
            return new ApiResponse(false, "Your account is successfully deleted");
        } else {
            return new ApiResponse(true, "User not found");
        }
    }

    public UserResponse getUserByMobileNumber(String mobileNumber) throws DataNotFoundException {
        if (userRepository.ifNumberIsExist(mobileNumber) && !userRepository.getUserIsDeletedByNumber(mobileNumber)) {
            String id = this.userRepository.getUserIdByNumber(mobileNumber);
            Optional<User> data = this.userRepository.findById(id);
            return new UserResponse(
                    data.get().getUserName(),
                    data.get().getUserEmail(),
                    data.get().getAddress(),
                    data.get().getMobileNumber());
        } else {
            throw new DataNotFoundException("User not found");
        }
    }

    public ApiResponse emailVerify(String mobileNumber) {
        if (userRepository.ifNumberIsExist(mobileNumber)) {
            String token = UUID.randomUUID().toString();
            String link = "http://localhost:4040/api/v1/registration/confirm?token=" + token;
            this.confirmationTokenRepository.save(ConfirmationToken.builder().tokenId(token).token(token).createdAt(LocalDateTime.now()).expiresAt(LocalDateTime.now().plusMinutes(15)).confirmedAt(null).user(userRepository.findByMobileNumber(mobileNumber)).build());
            this.emailService.send(userRepository.findByMobileNumber(mobileNumber).getUserEmail(), messageBuilder.buildEmail(userRepository.findByMobileNumber(mobileNumber).getUserName(), link),"Confirm your email");
            return new ApiResponse(false, "Confirmation link send Successfully in your email Please verify Your email");
        } else {
            return new ApiResponse(true, "User Not Found");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByMobileNumber(username);
        if (user != null){
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getMobileNumber(), user.getPassword(), new ArrayList<>());
    }

    @Transactional
    public ApiResponse confirmToken(String token) throws IllegalStateException {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token).orElseThrow(() ->
                new IllegalStateException("Token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token expired");
        }

        confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
        userRepository.activateAccount(confirmationToken.getUser().getMobileNumber());
        this.emailService.send(confirmationToken.getUser().getUserEmail(),
                messageBuilder.confirmationEmail(confirmationToken.getUser().getUserName()),"Verified email");
        return new ApiResponse(false, "Confirmed");
    }
    public Boolean isEmailVerified(String mobileNumber){
        return !this.userRepository.findByMobileNumber(mobileNumber).getIsEmailVerified();
    }
}
