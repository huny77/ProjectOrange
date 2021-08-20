package com.project.orange.service.user;

import com.project.orange.entity.badge.Badges;
import com.project.orange.entity.user.BadgesUsers;
import com.project.orange.repository.badge.BadgeRepository;
import com.project.orange.repository.user.BadgesUsersRepository;
import com.project.orange.service.nofitication.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.project.orange.management.Constants.*;

@Service
@Transactional
public class BadgesUsersServiceImpl implements BadgesUsersService{

    @Autowired
    private BadgesUsersRepository badgesUsersRepository;

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public List<BadgesUsers> selectAllByUserId(Long user) {
        return badgesUsersRepository.findAllByUser(user);
    }

    @Override
    public Optional<BadgesUsers> badgeAwardAndNotify(Long userId, Long badgeId) {
        try {
            List<BadgesUsers> targetBadgeSearchList = new ArrayList<>();
            targetBadgeSearchList = badgesUsersRepository.findByUserAndBadge(userId, badgeId);

            if (targetBadgeSearchList.isEmpty()) {
                BadgesUsers awardedBadge = BadgesUsers.builder()
                        .badge(badgeId)
                        .user(userId)
                        .badgeCount(1)
                        .build();

                Optional<BadgesUsers> awarded = Optional.of(badgesUsersRepository.save(awardedBadge));
                Badges targetBadge = badgeRepository.getById(badgeId);
                String content = targetBadge.getBadgeTitle();
                content += awardedNewBadgeContent;
                content += "(" + targetBadge.getBadgeDescribe() + ")";

                notificationService.sendNotificationTo(userId, awardedNewBadgeTitle, content);

                return awarded;
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Boolean isTrinityCondition(Long userId) {
        Boolean workout = false;
        Boolean food = false;
        Boolean supplement = false;

        List<BadgesUsers> badgesUsersList = badgesUsersRepository.findAllByUser(userId);
        for(BadgesUsers eachBadgesUsers : badgesUsersList){
            if(eachBadgesUsers.getBadge().equals(WowFriendsItsYourBaldManBadgeId)){
                workout = true;
            }
            if(eachBadgesUsers.getBadge().equals(WithBaSilBadgeId)){
                food = true;
            }
            if(eachBadgesUsers.getBadge().equals(TimeForPillsBadgeId)){
                supplement = true;
            }
        }
        if(workout && food && supplement){
            return true;
        }
        else {
            return false;
        }
    }
}
