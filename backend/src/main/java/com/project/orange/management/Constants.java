package com.project.orange.management;

import java.io.File;

public final class Constants {
    public static final String challengeMatchingAcceptedTitle = "챌린지 매칭 완료";
    public static final String challengeMatchingAcceptedContent = " 챌린지의 매칭이 완료되었습니다! 지금 바로 시작하세요!";

    public static final String challengeNewMemberRegisteredTitle = "챌린지에 새 멤버 합류";
    public static final String challengeNewMemberRegisteredContent = " 챌린지에 새 멤버가 합류했습니다!";

    public static final String awardedNewBadgeTitle = "새 뱃지 획득";
    public static final String awardedNewBadgeContent = " 뱃지를 획득했습니다! ";

    public static final int initialPointForChallenge = 100;

    public static final Long HereComesANewChallengerBadgeId = 1L;
    public static final Long HandsInHandsBadgeId = 2L;
    public static final Long WowFriendsItsYourBaldManBadgeId = 3L;
    public static final Long WithBaSilBadgeId = 4L;
    public static final Long TimeForPillsBadgeId = 5L;
    public static final Long TrinityBadgeId = 6L;

    public static final Long BeMyColleagueBadgeId = 7L;
    public static final Long NowWeAreFriendsBadgeId = 8L;

    public static final Long HereICameBadgeId = 9L;
    public static final Long ReplyCommitteeBadgeId = 10L;
    public static final Long IAmInevitable = 11L;

    public static final Long WorkoutCategoryId = 1L;
    public static final Long FoodCategoryId = 2L;
    public static final Long SupplementCategoryId = 3L;

    public static final String ImageRealPath = "/media/image";//"/media/image";
    public static final String BadgeImageRealPath = "/media/image/badge";
    public static final String DefaultImage = "default_image";

    public static final int ArticleBasePoint = 10;
    public static final int ImageBonusPoint = 5;

    public static final String ImageUploadUrl = "/api/image/show/**";
    public static final String ResourcePath = "file:///media/image/"; //C:/SSAFY/imageSaveTest/";
    public static final String ContextPath = "http://i5b102.p.ssafy.io:8181";
    public static final String ImageShowUrlPrefix = "/api/image/show/";

    public static final String DefaultBadgePostfix = "_none";
    public static final String BadgeImageExtension = ".png";
    public static final String BadgeImageDirectory = "badge/";
    public static final String BadgeImageNamePrefix = "badge";
}
