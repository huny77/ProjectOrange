package com.project.orange;

import com.project.orange.entity.user.RequestLoginUser;
import com.project.orange.entity.user.Users;
import com.project.orange.repository.user.UserRepository;
import com.project.orange.service.user.login.AuthService;
import com.project.orange.service.user.login.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
public class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void signUp(){
        Users user = new Users();
        user.setEmail("test1");
        user.setNickname("test1");
        user.setPassword("1234");

        authService.signUpUser(user);
    }


    @Test
    public void delete(){
        Optional<Users> user = Optional.ofNullable(userRepository.findByUserId(17L));
        user.ifPresent(selectUser->{
            userRepository.delete(selectUser);
        });
    }


    @Test
    public void login(){
        RequestLoginUser loginUser = new RequestLoginUser("test2","1234");
        try{
            authService.loginUser(loginUser.getEmail(),loginUser.getPassword());
            log.info("로그인 성공");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void setData(){
        redisUtil.setDataExpire("11","11",20);
    }

    //성공하면 redis와 연결 잘 된 것
   @Test
    void redisConnectionTest() {
        final String key = "a";
        final String data = "1";

        final ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, data);

        final String result = valueOperations.get(key);
        assertEquals(data, result);
    }
    //테스트 진행 후 "\xac\xed\x00\x05t\x00\x01a" 이런 값이 들어감
    //자바가 serialize하는 과정에서 "1"라는 데이터만 저장하는 것이 아니라 데이터의 클래스 정보도 추가로 저장하기 때문
    //redis.cli 에서 get "\xac\xed\x00\x05t\x00\x01a" 하면 "\xac\xed\x00\x05t\x00\x011"로 잘 출력 됨

}

