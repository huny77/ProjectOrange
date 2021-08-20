package com.project.orange.controller.challenge;

import com.project.orange.entity.challenge.BattleMatching;
import com.project.orange.entity.challenge.Challenges;
import com.project.orange.entity.notification.Notifications;
import com.project.orange.entity.user.Users;
import com.project.orange.entity.user.UsersChallenges;
import com.project.orange.service.challenge.ChallengeService;
import com.project.orange.service.nofitication.NotificationService;
import com.project.orange.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.project.orange.management.Constants.challengeNewMemberRegisteredContent;
import static com.project.orange.management.Constants.challengeNewMemberRegisteredTitle;

@RestController
@RequestMapping("/api/challenge")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @GetMapping("/{challengeId}")
    public ResponseEntity<Challenges> selectOne(@PathVariable Long challengeId){
        Optional<Challenges> challenge;
        challenge = challengeService.selectByChallengeId(challengeId);
        if(challenge.isPresent()){
            return new ResponseEntity<>(challenge.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Challenges>> selectAll(){
        List<Challenges> list;
        list = challengeService.selectAll();
        if (list == null || list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/ranking/point")
    public ResponseEntity<List<Challenges>> selectAllSortedByTotalPointDesc(){
        List<Challenges> list;
        list = challengeService.selectAllSortedByTotalPoint();
        if (list == null || list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/ranking/startdate")
    public ResponseEntity<List<Challenges>> selectAllSortedByStartDate(){
        List<Challenges> list;
        list = challengeService.selectAllSortedByStartDate();
        if (list == null || list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/list/random/{number}")
    public ResponseEntity<List<Challenges>> selectAllByRandom(@PathVariable int number){
        List<Challenges> list;
        list = challengeService.selectAllByRandom(number);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/search/title/{searchTitle}")
    public ResponseEntity<List<Challenges>> selectAllByChallengeTitleContaining(@PathVariable String searchTitle){
        List<Challenges> list;
        list = challengeService.selectAllByChallengeTitleContaining(searchTitle);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/search/category/{searchCategoryId}")
    public ResponseEntity<List<Challenges>> selectAllByCategoryId(@PathVariable Long searchCategoryId){
        List<Challenges> list;
        list = challengeService.selectAllByCategoryId(searchCategoryId);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/search/period/{searchPeriodId}")
    public ResponseEntity<List<Challenges>> selectAllByPeriod(@PathVariable Long searchPeriodId){
        List<Challenges> list;
        list = challengeService.selectAllByPeriodId(searchPeriodId);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/search/user/{userId}")
    public ResponseEntity<List<Challenges>> selectdAllByUserId(@PathVariable Long userId){
        List<Challenges> list;
        list = challengeService.selectAllByUserId(userId);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @PostMapping("/register/new-user")
    public ResponseEntity<Map<String, String>>  registerNewUserToChallenge(@RequestBody Map<String, Long> parameters){
        Long challengeId = parameters.get("challengeId");
        Long userId = parameters.get("userId");
        Optional<Challenges> targetChallenge = challengeService.selectByChallengeId(challengeId);
        Optional<Users> targetUser = Optional.ofNullable(userService.selectAllByUserId(userId));
        Map<String, String> response = new HashMap<>();

        // 대상 챌린지, 유저가 존재하는지 확인
        if(targetChallenge.isEmpty() || targetUser.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // 최대 멤버 수를 넘는 참여 요청은 불가
        int maxMembers = targetChallenge.get().getMaxMembers();
        int currentMembers = targetChallenge.get().getCurrentMembers();

        if(currentMembers + 1 >= maxMembers){
            response.put("result", "Max Member Reached");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // 대상 유저가 이미 챌린지에 참여중인지 확인
        if(challengeService.isUserAlreadyInChallenge(challengeId, userId)){
            response.put("result", "Already In Challenge");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        Optional<UsersChallenges> newUser = challengeService.registerNewUserToChallenge(challengeId, userId);


        if(newUser.isEmpty()){ // users_challenges 테이블에 저장 실패
            response.put("result", "Failed");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            String challengeTitle = challengeService.selectByChallengeId(challengeId).get().getChallengeTitle();
            String newUserNickname = newUser.get().getUser().getNickname();
            String content = challengeTitle + challengeNewMemberRegisteredContent + "@" + newUserNickname;
            Long managerId = targetChallenge.get().getManagerId();

            Optional<Notifications> notification;
            notification = notificationService.sendNotificationTo(
                    managerId,
                    challengeNewMemberRegisteredTitle,
                    content
            );

            if(notification.isPresent()) {
                response.put("result", "Success");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("result", "Notification Error");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping("/register/new-challenge")
    public ResponseEntity<Map<String, String>> registerNewChallenge(@RequestBody Challenges challenge){
        Optional<BattleMatching> matchMakingResult;
        matchMakingResult = challengeService.registerNewChallenge(challenge);
        Map<String, String> response = new HashMap<>();
        if(matchMakingResult.isPresent()){
            // Todo : notification 연동
            List<Notifications> notificationsList = challengeService.notifyMatchMaking(matchMakingResult.get());
            if(notificationsList != null && !notificationsList.isEmpty()) {
                response.put("result", "matched");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("result", "Notification Error");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            response.put("result", "unmatched");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, String>> modify(@RequestBody Challenges challenge){
        Optional<Challenges> modifiedChallenge = challengeService.save(challenge);
        Map<String, String> response = new HashMap<>();
        if(modifiedChallenge.isEmpty()){
            response.put("result", "failed");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            response.put("result", "success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/opponent/{challengeId}")
    public ResponseEntity<?> findOpponentByChallengeId(@PathVariable Long challengeId){
        Map<String, String> result = new HashMap<>();
        Optional<Challenges> opponent = challengeService.findOpponentByChallengeId(challengeId);
        if(opponent.isEmpty()){
            result.put("result", "no content");
            return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(opponent, HttpStatus.OK);
        }
    }
}
