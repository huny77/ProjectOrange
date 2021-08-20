package com.project.orange.controller.user;

import com.project.orange.entity.Response;
import com.project.orange.entity.user.FollowerFollowing;
import com.project.orange.entity.user.RequestLoginUser;
import com.project.orange.entity.user.Users;
import com.project.orange.repository.user.FollowFollowingRepository;
import com.project.orange.service.user.*;


import com.project.orange.service.user.login.AuthService;
import com.project.orange.service.user.login.CookieUtil;
import com.project.orange.service.user.login.JwtUtil;
import com.project.orange.service.user.login.RedisUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
//@CrossOrigin("*")
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private FollowFollowingRepository followFollowingRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/signup")
    public Response signUpUser(@RequestBody Users user) {
        try {
            authService.signUpUser(user);
            return new Response("success", "회원가입을 성공적으로 완료했습니다.", null,null,null);
        } catch (Exception e) {
            return new Response("error", "회원가입을 하는 도중 오류가 발생했습니다.", null,null,null);
        }
    }

    @GetMapping("/check/email/{email}")
    @ApiOperation(value = "email", notes = "이메일이 중복되면 true, 중복아니면 false 반환")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email){
        return ResponseEntity.ok(userService.checkEmailDuplicate(email));
    }

    @GetMapping("/check/nickname/{nickname}")
    @ApiOperation(value = "nickname", notes = "닉네임이 중복되면 true, 중복아니면 false 반환")
    public ResponseEntity<Boolean> checkNicknameDuplicate(@PathVariable String nickname){
        return ResponseEntity.ok(userService.checkNicknameDuplicate(nickname));
    }


    @PostMapping("/login")
    public Response login(@RequestBody RequestLoginUser loginUser,
                          HttpServletRequest req,
                          HttpServletResponse res){

        try {

            final Users user = authService.loginUser(loginUser.getEmail(), loginUser.getPassword());
            final String token = jwtUtil.generateToken(user);
            System.out.println("토큰 정보"+token);
            final String refreshJwt = jwtUtil.generateRefreshToken(user);
            System.out.println("refresh 토큰 정보"+token);
            Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
            Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);
            redisUtil.setDataExpire(refreshJwt, user.getEmail(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
            res.addCookie(accessToken);
            res.addCookie(refreshToken);

            return new Response("success", "로그인에 성공했습니다.", token, user.getUserId(),null);

        } catch (Exception e){
            return new Response("error", "로그인에 실패했습니다.", e.getMessage(),null,null);
        }
    }

    @GetMapping("/list")
    @ApiOperation(value = "list",notes = "전체 사용자 리스트를 출력")
    public Response list(){
        List<Users> list = userService.userList();

        if(list == null || list.isEmpty()){
            return new Response("fail", "회원 리스트를 불러오지 못했습니다.", null,null,null);
        }else return new Response("success", "회원 리스트를 불러오는데 성공했습니다.", list,null,null);
    }


    @GetMapping("/userinfo/{userId}")
    public Response readUser(@PathVariable Long userId){ //@Param Long userId 로 바뀔 가능성 있음
        Users userInfo = userService.selectAllByUserId(userId);
        if(userInfo == null){
            return new Response("fail", "회원정보가 없습니다.",  null,null,null);
        }else {
            List<FollowerFollowing> followerList = followFollowingRepository.findByToUserId(userId);
            List<FollowerFollowing> followingList = followFollowingRepository.findByFromUserId(userId);
//            System.out.println(followerList);
//            Map<List<FollowerFollowing>, List<FollowerFollowing>> fwList;
//            fwList.
            return new Response("success", "회원을 불러오는데 성공했습니다.", userInfo, followerList,followingList);
        }
    }

    @PutMapping("/update/{userId}")
    public Response updateUser(@PathVariable Long userId, @RequestBody Users userInfo){
        userService.updateById(userId,userInfo);
        return new Response("success", "회원정보 수정에 성공했습니다.", null,null,null);
    }

    @DeleteMapping("/delete/{userId}")
    public Response deleteUser(@PathVariable Long userId){
        userService.deleteById(userId);
        return new Response("success", "회원탈퇴에 성공했습니다.", null,null,null);
    }

}
