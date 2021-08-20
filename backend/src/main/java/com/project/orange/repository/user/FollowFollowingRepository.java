package com.project.orange.repository.user;

import com.project.orange.entity.user.FollowerFollowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FollowFollowingRepository extends JpaRepository<FollowerFollowing, Long> {

    @Modifying
    @Transactional
    void deleteByFromUserIdAndToUserId(Long fromUserId, Long toUserId);
    int countByFromAndTo(Long fromUserId, Long toUserId);
    int countByFromUserId(Long fromUserId);
    int countByToUserId(Long toUserId);

    List<FollowerFollowing> findByFromUserId(Long fromUserId);
    List<FollowerFollowing> findByToUserId(Long toUserId);
}
