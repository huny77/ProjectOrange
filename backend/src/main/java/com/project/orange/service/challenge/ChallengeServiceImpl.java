package com.project.orange.service.challenge;

import com.project.orange.entity.challenge.BattleMatching;
import com.project.orange.entity.challenge.Challenges;
import com.project.orange.entity.notification.Notifications;
import com.project.orange.entity.user.BadgesUsers;
import com.project.orange.entity.user.Users;
import com.project.orange.entity.user.UsersChallenges;
import com.project.orange.repository.badge.BadgeRepository;
import com.project.orange.repository.challenge.BattleMatchingRepository;
import com.project.orange.repository.challenge.ChallengesRepository;
import com.project.orange.repository.challenge.PeriodsRepository;
import com.project.orange.repository.notification.NotificationsRepository;
import com.project.orange.repository.user.BadgesUsersRepository;
import com.project.orange.repository.user.UserRepository;
import com.project.orange.repository.user.UsersChallengesRepository;
import com.project.orange.service.nofitication.NotificationService;
import com.project.orange.service.user.BadgesUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
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
    private final BadgeRepository badgeRepository;
    private final PeriodsRepository periodsRepository;
    private final BadgesUsersService badgesUsersService;
    private final NotificationService notificationService;


    @Autowired
    public ChallengeServiceImpl(ChallengesRepository challengesRepository,
                                BattleMatchingRepository battleMatchingRepository,
                                NotificationsRepository notificationsRepository,
                                UsersChallengesRepository usersChallengesRepository,
                                UserRepository userRepository,
                                BadgesUsersRepository badgesUsersRepository,
                                BadgeRepository badgeRepository,
                                PeriodsRepository periodsRepository,
                                BadgesUsersService badgesUsersService,
                                NotificationService notificationService) {
        this.challengesRepository = challengesRepository;
        this.battleMatchingRepository = battleMatchingRepository;
        this.notificationsRepository = notificationsRepository;
        this.usersChallengesRepository = usersChallengesRepository;
        this.userRepository = userRepository;
        this.badgeRepository = badgeRepository;
        this.periodsRepository = periodsRepository;
        this.badgesUsersService = badgesUsersService;
        this.notificationService = notificationService;
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
    public List<Challenges> selectAllByChallengeTitleContaining(String searchTitle) {
        return challengesRepository.findAllByChallengeTitleContaining(searchTitle);
    }

    @Override
    public List<Challenges> selectAllByCategoryId(Long categoryId) {
        return challengesRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public List<Challenges> selectAllByPeriodId(Long periodId) {
        return challengesRepository.findAllByPeriodId(periodId);
    }

    @Override
    public List<Challenges> selectAllByUserId(Long userId) {
        List<UsersChallenges> usersChallengesList;
        usersChallengesList = usersChallengesRepository.findAllByUserUserId(userId);

        List<Challenges> challengesList = new ArrayList<>();
        for(UsersChallenges eachUsersChallenges : usersChallengesList){
            challengesList.add(eachUsersChallenges.getChallenge());
        }
        return challengesList;
    }

    @Override
    public Optional<Challenges> findOpponentByChallengeId(Long challengeId) {
        Optional<BattleMatching> blueTeamSearchResult = battleMatchingRepository.findByBlueTeamChallengeId(challengeId);
        Optional<BattleMatching> redTeamSearchResult = battleMatchingRepository.findByRedTeamChallengeId(challengeId);

        if(blueTeamSearchResult.isEmpty() && redTeamSearchResult.isEmpty()){
            return Optional.empty();
        }
        else{
            Challenges opponent = null;
            if(blueTeamSearchResult.isPresent()){
                opponent = blueTeamSearchResult.get().getRedTeam();
            }
            if(redTeamSearchResult.isPresent()){
                opponent = redTeamSearchResult.get().getBlueTeam();
            }
            return Optional.of(opponent);
        }
    }

    @Override
    public Optional<BattleMatching> registerNewChallenge(Challenges challenge) {
        // 전달받은 Challenge 객체로 DB 저장
        // entity manager 를 autowire 로 불러와서 flush 혹은 clear

        LocalDateTime startDate = challenge.getStartDate();
        int plusDate = periodsRepository.getById(challenge.getPeriodId()).getPeriodDays();
        challenge.setEndDate(startDate.plusDays(plusDate));
        challenge.setCurrentMembers(1);
        challenge.setTotalPoint(initialPointForChallenge);
        challenge.setImagePath(DefaultImage);

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
        Long managerId = manager.getUser().getUserId();
        usersChallengesRepository.save(manager);

        // 첫 챌린지 참여 뱃지 지급
        Optional<BadgesUsers> joinChallengeFirstTime;
        badgesUsersService.badgeAwardAndNotify(managerId, HereComesANewChallengerBadgeId);
        Long targetChallengeCategoryId = currentChallenge.getCategoryId();
        // 첫 "운동" 챌린지 참여 뱃지
        if(targetChallengeCategoryId.equals(WorkoutCategoryId)){
            badgesUsersService.badgeAwardAndNotify(managerId, WowFriendsItsYourBaldManBadgeId);
        }
        // 첫 "음식" 챌린지 참여 뱃지
        else if(targetChallengeCategoryId.equals(FoodCategoryId)){
            badgesUsersService.badgeAwardAndNotify(managerId, WithBaSilBadgeId);
        }
        // 첫 "영양제" 챌린지 참여 뱃지
        else if(targetChallengeCategoryId.equals(SupplementCategoryId)){
            badgesUsersService.badgeAwardAndNotify(managerId, TimeForPillsBadgeId);
        }
        // "삼위일체" 뱃지
        if(badgesUsersService.isTrinityCondition(managerId)){
            badgesUsersService.badgeAwardAndNotify(managerId, TrinityBadgeId);
        }

        // 첫 챌린지 주최 badge 지급
        badgesUsersService.badgeAwardAndNotify(managerId, HandsInHandsBadgeId);

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

        Long targetChallengeCategoryId = targetChallenge.getCategoryId();

        // 첫 챌린지 참여 뱃지
        Optional<BadgesUsers> joinChallengeFirstTime;
        badgesUsersService.badgeAwardAndNotify(userId, HereComesANewChallengerBadgeId);

        // 첫 "운동" 챌린지 참여 뱃지
        if(targetChallengeCategoryId.equals(WorkoutCategoryId)){
            badgesUsersService.badgeAwardAndNotify(userId, WowFriendsItsYourBaldManBadgeId);
        }
        // 첫 "음식" 챌린지 참여 뱃지
        else if(targetChallengeCategoryId.equals(FoodCategoryId)){
            badgesUsersService.badgeAwardAndNotify(userId, WithBaSilBadgeId);
        }
        // 첫 "영양제" 챌린지 참여 뱃지
        else if(targetChallengeCategoryId.equals(SupplementCategoryId)){
            badgesUsersService.badgeAwardAndNotify(userId, TimeForPillsBadgeId);
        }
        // "삼위일체" 뱃지
        if(badgesUsersService.isTrinityCondition(userId)){
            badgesUsersService.badgeAwardAndNotify(userId, TrinityBadgeId);
        }

        targetChallenge.setTotalPoint(targetChallenge.getTotalPoint() + initialPointForChallenge);
        targetChallenge.setCurrentMembers(targetChallenge.getCurrentMembers() + 1);
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
            if(user.getUserId().equals(userId)){
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
