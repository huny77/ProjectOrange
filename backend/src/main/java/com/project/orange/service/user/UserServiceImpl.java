package com.project.orange.service.user;

import antlr.StringUtils;
import com.project.orange.entity.user.Users;
import com.project.orange.repository.user.UserRepository;
import com.project.orange.service.user.login.AuthService;
import org.mapstruct.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Users> userList() {
        List<Users> list = (List<Users>) userRepository.findAll();
        return list;
    }

    @Override
    public Users selectAllByUserId(Long userId) {
        Users user = userRepository.findByUserId(userId);
        return user;
    }

    @Override
    @Transactional
    public void updateById(Long userId, Users userInfo){
        Users user = userRepository.findByUserId(userId);

        user.setPassword(userInfo.getPassword());
        user.setNickname(userInfo.getNickname());
        user.setUsername(userInfo.getUsername());
        user.setProfileImagePath(userInfo.getProfileImagePath());

        authService.signUpUser(user);
    }

    @Override
    public void deleteById(Long userId){
        userRepository.deleteById(userId);
    }

    @Override
    public boolean checkNicknameDuplicate(String nickname){
        return userRepository.existsByNickname(nickname);
    }

    @Override
    public boolean checkEmailDuplicate(String email){
        return userRepository.existsByEmail(email);
    }
}
