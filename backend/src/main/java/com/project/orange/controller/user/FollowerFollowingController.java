package com.project.orange.controller.user;

import com.project.orange.entity.Response;
import com.project.orange.entity.user.Users;
import com.project.orange.repository.user.FollowFollowingRepository;
import com.project.orange.repository.user.UserRepository;
import com.project.orange.service.user.BadgesUsersService;
import com.project.orange.service.user.FollowerFollowingService;
import com.project.orange.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/follow")
public class FollowerFollowingController {

    @Autowired
    public FollowerFollowingService followerFollowingService;

    @Autowired
    public UserService userService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public BadgesUsersService badgesUsersService;

    @GetMapping("/check/{userId}")
    public ResponseEntity<?> checkMainUser(@PathVariable Long userId){

        String loginUserId = SecurityContextHolder.getContext().getAuthentication().getName(); //여기서 email을 얻어옴
        System.out.println(loginUserId);
        Users userInfo = userRepository.findByEmail(loginUserId);

        followerFollowingService.checkFollowing(userInfo.getUserId(), userId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/follow/{fromUserId}/{toUserId}")
    public Response follow(@PathVariable Long fromUserId, @PathVariable Long toUserId){

        Response response = new Response();

        if(followerFollowingService.checkFirstFollower(toUserId)){
            badgesUsersService.badgeAwardAndNotify(toUserId, 8L);
            response.setData1("나를 처음 팔로우 한 사람이 있습니다.");
        }
        if(followerFollowingService.checkFirstFollowing(fromUserId)){
            badgesUsersService.badgeAwardAndNotify(fromUserId, 7L);
            response.setData2("내가 처음 팔로우 한 사람이 있습니다.");
        }
        followerFollowingService.save(fromUserId,toUserId);
        return response;
    }

    @GetMapping("/unfollow/{fromUserId}/{toUserId}")
    public void unFollow(@PathVariable Long fromUserId, @PathVariable Long toUserId){

        followerFollowingService.deleteByFollowingIdAndFollowerId(fromUserId,toUserId);
    }


}
