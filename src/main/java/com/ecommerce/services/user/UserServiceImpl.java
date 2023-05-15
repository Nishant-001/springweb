package com.ecommerce.services.user;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.SignupRequest;
import com.ecommerce.dto.UserDto;
import com.ecommerce.entity.User;
import com.ecommerce.repo.UserRepo;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public void createAdminAccount() {
        User adminAccount = userRepo.findByRole("ADMIN");
        if(null == adminAccount){
            User user = new User();
            user.setEmail("admin@gmail.com");
            user.setName("Admin");
            user.setRole("ADMIN");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepo.save(user);
        }
    }

    @Transactional
    public User createUser(SignupRequest signupRequest) throws Exception {
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setRole(signupRequest.getRole());
        user = userRepo.save(user);
        return user;
    }


    public Boolean hasUserWithEmail(String email) {
        return userRepo.findFirstByEmail(email) != null;
    }

    @Override
    public UserDto getUser(Long userId) {
        UserDto userDto = null;
        Optional<User> optionalUser = userRepo.findById(userId);
        if(optionalUser.isPresent()){
            userDto = optionalUser.get().mapUsertoUserDto();
            userDto.setReturnedImg(optionalUser.get().getImg());
        }
        return userDto;
    }

}
