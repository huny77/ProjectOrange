package com.project.orange.service.user;

import com.project.orange.entity.user.BadgesUsers;

import java.util.List;
import java.util.Optional;

public interface BadgesUsersService {
    public List<BadgesUsers> selectAllByUserId(Long user);
}
