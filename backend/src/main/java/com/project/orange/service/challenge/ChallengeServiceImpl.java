package com.project.orange.service.challenge;

import com.project.orange.entity.challenge.BattleMatching;
import com.project.orange.entity.challenge.Challenges;
import com.project.orange.entity.notification.Notifications;
import com.project.orange.entity.user.Users;
import com.project.orange.entity.user.UsersChallenges;
import com.project.orange.repository.challenge.BattleMatchingRepository;
import com.project.orange.repository.challenge.ChallengesRepository;
import com.project.orange.repository.notification.NotificationsRepository;
import com.project.orange.repository.user.UserRepository;
import com.project.orange.repository.user.UsersChallengesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.*;

import static com.project.orange.management.Constants.*;

@Service
@Transactional
public class ChallengeServiceImpl implements ChallengeService{

    // 생성자 주입 (Constructor Injection)
    private final ChallengesRepository challengesRepository;
    private final BattleMatchingRepository battleMatchingRepository;
    private final NotificationsRepository notificationsRepository;
    private final UsersChallengesRepository usersChallengesRepository;
    private final UserRepository userRepository;


    @Autowired
    public ChallengeServiceImpl(ChallengesRepository challengesRepository,
                                BattleMatchingRepository battleMatchingRepository,
                                NotificationsRepository notificationsRepository,
                                UsersChallengesRepository usersChallengesRepository,
                                UserRepository userRepository) {
        this.challengesRepository = challengesRepository;
        this.battleMatchingRepository = battleMatchingRepository;
        this.notificationsRepository = notificationsRepository;
        this.usersChallengesRepository = usersChallengesRepository;
        this.userRepository = userRepository;
    }

    @Autowired
    private EntityManager entityManager;

    @Override
    public Optional<Challenges> save(Challenges challenge) {
        return Optional.of(challengesRepository.save(challenge));
    }

    @Override
    public Optional<Challenges> selectByChallengeId(Long challengeId) {
        return challengesRepository.findById(challengeId);
    }

    @Override
    public List<Challenges> selectAll() {
        return challengesRepository.findAll();
    }

    @Override
    public List<Challenges> selectAllSortedByTotalPoint() {
        return challengesRepository.findAll(Sort.by(Sort.Direction.DESC, "totalPoint"));
    }

    @Override
    public List<Challenges> selectAllSortedByStartDate() {
        return challengesRepository.findAll(Sort.by(Sort.Direction.DESC, "startDate"));
    }

    @Override
    public List<Challenges> selectAllByRandom(int number) {
        List<Challenges> totalList = challengesRepository.findAll();
        if(totalList.size() <= number){
            return totalList;
        } else {
            List<Challenges> randomSelectedList = new ArrayList<>();
            Set<Integer> selectedIdxSet = new HashSet<>();
            do {
                selectedIdxSet.add((int) (Math.random() * totalList.size()));
            } while (selectedIdxSet.size() < number);
            for(int selectedIdx : selectedIdxSet){
                randomSelectedList.add(totalList.get(selectedIdx));
            }
            return randomSelectedList;
        }
    }

    @Override
    public Optional<BattleMatching> registerNewChallenge(Challenges challenge) {
        // 전달받은 Challenge 객체로 DB 저장
        // entity manager 를 autowire 로 불러와서 flush 혹은 clear
        Challenges currentChallenge = challengesRepository.save(challenge);
        entityManager.flush(); // DB에 반영 (transaction 마지막에 flush 해줌)
        entityManager.clear(); //

        // Todo : userschallenges에서 이 챌린지 생성한 사용자를 manger 로 등록할 것!!!
        UsersChallenges manager;
        manager = UsersChallenges.builder()
                .user(userRepository.findById(currentChallenge.getManagerId()).get())
                .challenge(currentChallenge)
                .isManager(true)
                .point(initialPointForChallenge)
                .build();

        UsersChallenges currentChallengeManager = usersChallengesRepository.save(manager);

        // 현재 저장한 Challenge 정보
        Long currentChallengeId = currentChallenge.getChallengeId();
        Long currentPeriodId = currentChallenge.getPeriodId();

        // 현재 저장한 Challenge 와 같은 도전 기간을 갖는 Challenge 의 List
        List<Challenges> samePeriodChallengesList = challengesRepository.findAllByPeriodId(currentPeriodId);
        // matching 이 아직 이루어지지 않은 Challenge 를 저장할 List
        List<Challenges> matchmakingPool = new ArrayList<>();

        // BattleMatching 테이블에 존재하지 않는 Challenge 만 선별
        for(Challenges each : samePeriodChallengesList){
            if(each.getChallengeId() != currentChallengeId &&
                    battleMatchingRepository.findByBlueTeamChallengeId(each.getChallengeId()).isEmpty() &&
                    battleMatchingRepository.findByRedTeamChallengeId(each.getChallengeId()).isEmpty()){
                matchmakingPool.add(each);
            }
        }

        BattleMatching savedBattleMatching = null;
        if(!matchmakingPool.isEmpty()){
            // matchmaking pool 에서 랜덤으로 Challenge 하나 선택, opponent Challenge 로 선정
            int selectedIdx = (int) (Math.random() * matchmakingPool.size());
            Challenges opponentChallenge;
            opponentChallenge = challengesRepository.findById(matchmakingPool.get(selectedIdx).getChallengeId()).get();
            currentChallenge = challengesRepository.findById(currentChallengeId).get(); // 영속성 컨텍스트 안에 이미 있어서 DB를 거친게 아니고 그냥 있는걸 가져옴
            // matchmaking 결과를 DB에 저장
            BattleMatching newBattleMatching = new BattleMatching();
            newBattleMatching.setBlueTeam(currentChallenge);
            newBattleMatching.setRedTeam(opponentChallenge);
            savedBattleMatching = battleMatchingRepository.save(newBattleMatching);

            // 생성될 notification 을 담을 List
//            List<Notifications> notificationsForChallengeMembers;
//            notificationsForChallengeMembers = new ArrayList<>();
//
//            List<Challenges> matchedChallenges = new ArrayList<>();
//            matchedChallenges.add(opponentChallenge);
//            matchedChallenges.add(currentChallenge);
//
//            // 두 챌린지에 소속된 모든 User 에 대한 notification 생성
//            // fetch join ??? -> 한번에 받아온다? JPQL ! N+1 문제 ! <<< 면접 단골 ㄷㄷㄷ
//            for(Challenges eachChallenge : matchedChallenges){
//                List<UsersChallenges> usersChallengesList = eachChallenge.getUsersChallengesList();
//                for(UsersChallenges eachUsersChallenges : usersChallengesList){
//                    Notifications notification = new Notifications();
//                    notification.setUser(eachUsersChallenges.getUser());
//                    notification.setNotificationTitle(challengeMatchingAcceptedTitle);
//                    notification.setNotificationContent(eachChallenge.getChallengeTitle() +
//                                                        challengeMatchingAcceptedContent);
//
//                    notificationsForChallengeMembers.add(notification);
//                }
//            }
//
//            // 생성한 notification 을 DB에 저장
//            notificationsRepository.saveAll(notificationsForChallengeMembers);
        }

        return Optional.ofNullable(savedBattleMatching);
    }

    @Override
    public Optional<UsersChallenges> registerNewUserToChallenge(Long challengeId, Long userId) {
        Challenges targetChallenge = challengesRepository.getById(challengeId);
        UsersChallenges newMember;
        newMember = UsersChallenges.builder()
                .challenge(targetChallenge)
                .isManager(false)
                .point(initialPointForChallenge)
                .user(userRepository.findById(userId).get())
                .build();

        targetChallenge.setTotalPoint(targetChallenge.getTotalPoint() + initialPointForChallenge);
        usersChallengesRepository.save(newMember);
        challengesRepository.save(targetChallenge);

        return Optional.of(newMember);
    }

    @Override
    public boolean isUserAlreadyInChallenge(Long challengeId, Long userId) {
        Challenges targetChallenge = challengesRepository.getById(challengeId);
        List<UsersChallenges> challengeMembers = targetChallenge.getUsersChallengesList();

        for(UsersChallenges eachMember : challengeMembers){
            Users user = eachMember.getUser();
            if(user.getUserId() == userId){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Notifications> notifyMatchMaking(BattleMatching battleMatching) {
        Challenges blueTeam = battleMatching.getBlueTeam();
        Challenges redTeam = battleMatching.getRedTeam();

        List<Challenges> targetTeams =  new ArrayList<>();
        targetTeams.add(blueTeam);
        targetTeams.add(redTeam);

        List<Notifications> matchmakingNotificationList = new ArrayList<>();

        for(Challenges eachTeam : targetTeams){
            List<UsersChallenges> eachTeamMembers = eachTeam.getUsersChallengesList();
            for(UsersChallenges eachMember : eachTeamMembers){
                Notifications notification = new Notifications();
                notification.setUser(eachMember.getUser());
                notification.setNotificationTitle(challengeMatchingAcceptedTitle);
                notification.setNotificationContent(eachTeam.getChallengeTitle() +
                        challengeMatchingAcceptedContent);

                matchmakingNotificationList.add(notification);
            }
        }

        return notificationsRepository.saveAll(matchmakingNotificationList);
    }

    @Override
    public void deleteById(Long challengeId) {
        challengesRepository.deleteById(challengeId);
    }
}