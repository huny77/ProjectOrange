package com.project.orange.service.user;

public interface FollowerFollowingService {

    public void save(Long fromUserId, Long toUserId);
    public void deleteByFollowingIdAndFollowerId(Long fromUserId, Long toUserId);
    public boolean checkFollowing(Long FromUserId, Long toUserId);
    public boolean checkFirstFollowing(Long FromUserId); //내가 누군가를 처음 팔로우 했을 때
    public boolean checkFirstFollower(Long toUserId); //누군가가 나를 처음 팔로우 했을 때

}
