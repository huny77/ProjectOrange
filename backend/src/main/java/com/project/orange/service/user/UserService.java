package com.project.orange.service.user;

import com.project.orange.entity.user.Users;

import java.util.List;

public interface UserService {

    public List<Users> userList();
    public Users selectAllByUserId(Long userId);
    public void updateById(Long userId, Users user);
    public void deleteById(Long userId);
    public boolean checkNicknameDuplicate(String nickname);
    public boolean checkEmailDuplicate(String email);

}
