package com.project.orange.service.user;

import com.project.orange.entity.user.FollowerFollowing;
import com.project.orange.repository.user.FollowFollowingRepository;
import com.project.orange.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowerFollowingServiceImpl implements FollowerFollowingService {

    @Autowired
    FollowFollowingRepository followFollowingRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public void save(Long fromUserId, Long toUserId){
        FollowerFollowing fw = new FollowerFollowing();

        fw.setFrom(userRepository.findByUserId(fromUserId));
        fw.setTo(userRepository.findByUserId(toUserId));

        followFollowingRepository.save(fw);
        System.out.println(fw.toString());
    }

    @Override
    public void deleteByFollowingIdAndFollowerId(Long fromUserId, Long toUserId){
        followFollowingRepository.deleteByFromUserIdAndToUserId(fromUserId,toUserId);
    }

    @Override
    public boolean checkFollowing(Long fromUserId, Long toUserId) { // 팔로우가 되어있는지를 확인하기위해
        if(followFollowingRepository.countByFromAndTo(fromUserId, toUserId) == 0)
            return false; // 팔로우 안되어있음
        return true; // 되어있음
    }

    //내가 누군가를 처음 팔로우 했을 때
    @Override
    public boolean checkFirstFollowing(Long FromUserId){
        if(followFollowingRepository.countByFromUserId(FromUserId) == 0)
            return true; //이전에 팔로우 한 사람이 없으니 이번이 처음이다.
        else return false; //이전에 1명이상 있었다.
    }

    //누군가가 나를 처음 팔로우 했을 때
    @Override
    public boolean checkFirstFollower(Long toUserId){
        if(followFollowingRepository.countByToUserId(toUserId)==0)
            return true; //나를 팔로우한 사람이 0 일 때
        else return false; //나를 이미 팔로우 한 사람이 1명 이상일 때
    }

}
