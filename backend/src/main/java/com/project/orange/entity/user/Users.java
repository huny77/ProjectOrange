package com.project.orange.entity.user;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.project.orange.entity.notification.Notifications;
import com.project.orange.entity.report.Reports;
import com.project.orange.entity.article.Articles;
import com.project.orange.entity.article.Comments;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "userId")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salt")
    private Salt salt;

    private String password;
    private String email;
    private String nickname;
    private String token;
    private String profileImagePath;

    @Column
    private String role;

    public Users(){}

    public Users(String username,  String password,  String nickname,  String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Notifications> notificationsList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Comments> commentsList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Articles> articlesList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<BadgesUsers> badgesUsersList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<UsersChallenges> usersChallengesList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "from", fetch = FetchType.LAZY)
    List<FollowerFollowing> followerList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "to", fetch = FetchType.LAZY)
    List<FollowerFollowing> followingList = new ArrayList<>();

}
