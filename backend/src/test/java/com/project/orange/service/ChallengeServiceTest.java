package com.project.orange.service;

import com.project.orange.entity.article.Articles;
import com.project.orange.entity.challenge.BattleMatching;
import com.project.orange.entity.challenge.Challenges;
import com.project.orange.entity.notification.Notifications;
import com.project.orange.entity.user.UsersChallenges;
import com.project.orange.repository.challenge.BattleMatchingRepository;
import com.project.orange.repository.challenge.ChallengesRepository;
import com.project.orange.repository.notification.NotificationsRepository;
import com.project.orange.repository.user.UsersChallengesRepository;
import com.project.orange.service.challenge.ChallengeService;
import com.project.orange.service.challenge.PeriodService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChallengeServiceTest {

    @Autowired
    private ChallengeService challengeService;
    @Autowired
    private PeriodService periodService;

    @Autowired
    private ChallengesRepository challengesRepository;
    @Autowired
    private BattleMatchingRepository battleMatchingRepository;
    @Autowired
    private UsersChallengesRepository usersChallengesRepository;
    @Autowired
    private NotificationsRepository notificationsRepository;

    @Test
    void registerNewChallengeTest(){
        //given
        Long periodId = 2L;
        Long categoryId = 1L;
        int minMembers = 1;
        int maxMembers = 10;
        int totalPoint1 = 400;
        int totalPoint2 = 600;
        int currentMembers = 1;
        LocalDateTime now = LocalDateTime.now();
        int plusDate = periodService.selectOne(periodId).get().getPeriodDays();
        //assertEquals(15, plusDate);
        String profileImagePath = "testPath";

        Long blueTeamManagerId = 17L;
        String blueTeamTitle = "purpleTeamTitle";
        String blueTeamDescribe = "purpleTeamDescribe";

        Long redTeamManagerId = 18L;
        String redTeamTitle = "greenTeamTitle";
        String redTeamDescribe = "greenTeamDescribe";

        Challenges blueTeam = Challenges.builder()
                .managerId(blueTeamManagerId)
                .challengeTitle(blueTeamTitle)
                .challengeDescribe(blueTeamDescribe)
                .categoryId(categoryId)
                .periodId(periodId)
                .startDate(now)
                .endDate(now.plusDays(plusDate))
                .totalPoint(totalPoint1)
                .minMembers(minMembers)
                .maxMembers(maxMembers)
                .currentMembers(currentMembers)
                .imagePath(profileImagePath)
                .build();

        Challenges redTeam = Challenges.builder()
                .managerId(redTeamManagerId)
                .challengeTitle(redTeamTitle)
                .challengeDescribe(redTeamDescribe)
                .categoryId(categoryId)
                .periodId(periodId)
                .startDate(now)
                .endDate(now.plusDays(plusDate))
                .totalPoint(totalPoint2)
                .minMembers(minMembers)
                .maxMembers(maxMembers)
                .currentMembers(currentMembers)
                .imagePath(profileImagePath)
                .build();

        //when
        Optional<BattleMatching> matchMakingResult1 = challengeService.registerNewChallenge(blueTeam);
        List<UsersChallenges> usersChallengesList1 = usersChallengesRepository.findAll();
        assertEquals(true, matchMakingResult1.isEmpty());
        if(matchMakingResult1.isPresent()) {
            List<Notifications> notificationsList1 = challengeService.notifyMatchMaking(matchMakingResult1.get());
        }
        //assertEquals(1, usersChallengesList1.size());

        Optional<BattleMatching> matchMakingResult2 = challengeService.registerNewChallenge(redTeam);
        List<UsersChallenges> usersChallengesList2 = usersChallengesRepository.findAll();
        assertEquals(true, matchMakingResult2.isPresent());
        if(matchMakingResult2.isPresent()) {
            List<Notifications> notificationsList2 = challengeService.notifyMatchMaking(matchMakingResult2.get());
        }
        //assertEquals(2, usersChallengesList2.size());

//        List<Notifications> notificationsList = notificationsRepository.findAll();
//        assertEquals(2, notificationsList.size());

        //then
//        usersChallengesRepository.deleteAll();
//        battleMatchingRepository.deleteAll();
//        challengesRepository.deleteAll();
//        notificationsRepository.deleteAll();
    }

    @Test
    void updateExistingChallenge(){
        //given
        Long targetChallengeId = 1L;
        String newChallengeDescribe = "수정된 챌린지 소개를 또 수정함";
        Challenges targetChallenge = challengeService.selectByChallengeId(targetChallengeId).get();
        targetChallenge.setChallengeDescribe(newChallengeDescribe);

        //when
        Optional<Challenges> updatedChallenge = challengeService.save(targetChallenge);

        //then
        assertEquals(true, updatedChallenge.isPresent());
        assertEquals(newChallengeDescribe, updatedChallenge.get().getChallengeDescribe());
    }
}
