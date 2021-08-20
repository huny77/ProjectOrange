package com.project.orange.service.challenge;

import com.project.orange.entity.challenge.BattleMatching;
import com.project.orange.entity.challenge.Challenges;
import com.project.orange.entity.notification.Notifications;
import com.project.orange.entity.user.Users;
import com.project.orange.entity.user.UsersChallenges;

import java.util.List;
import java.util.Optional;

public interface ChallengeService {
    // insert, update
    public Optional<Challenges> save(Challenges challenge);

    // delete
    public void deleteById(Long id);

    // search
    public Optional<Challenges> selectByChallengeId(Long challengeId);
    public List<Challenges> selectAll();
    public List<Challenges> selectAllSortedByTotalPoint();
    public List<Challenges> selectAllSortedByStartDate();
    public List<Challenges> selectAllByRandom(int number);
    public List<Challenges> selectAllByChallengeTitleContaining(String searchTitle);
    public List<Challenges> selectAllByCategoryId(Long categoryId);
    public List<Challenges> selectAllByPeriodId(Long periodId);
    public List<Challenges> selectAllByUserId(Long userId);
    public Optional<Challenges> findOpponentByChallengeId(Long challengeId);

    // registerNewChallenge : 새로운 Challenge 등록 -> Challenge의 BattleMatching 결과 리턴
    public Optional<BattleMatching> registerNewChallenge(Challenges challenge);
    public Optional<UsersChallenges> registerNewUserToChallenge(Long challengeId, Long userId);

    // util
    public boolean isUserAlreadyInChallenge(Long challengeId, Long userId);
    public List<Notifications> notifyMatchMaking(BattleMatching battleMatching);
}
