package com.project.orange.entity.challenge;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.orange.entity.article.Articles;
import com.project.orange.entity.user.UsersChallenges;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="challenges")
@Builder
public class Challenges {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="challenge_id")
    private Long challengeId;

    @Column(name="manager_id")
    private Long managerId;

    @Column(name="challenge_title")
    private String challengeTitle;

    @Column(name="challenge_describe")
    private String challengeDescribe;

    @Column(name="category_id")
    private Long categoryId;

    @Column(name="period_id")
    private Long periodId;

    @Column(name="start_date")
    private LocalDateTime startDate;

    @Column(name="end_date")
    private LocalDateTime endDate;

    @Column(name="total_point")
    private Integer totalPoint;

    @Column(name="min_members")
    private Integer minMembers;

    @Column(name="max_members")
    private Integer maxMembers;

    @Column(name="current_members")
    private Integer currentMembers;

    @Column(name="image_path")
    private String imagePath;

    // fetch-join으로 ?
    @JsonIgnore
    @OneToMany(mappedBy = "challenge", fetch = FetchType.EAGER) // 주체가 아닌 애가 주인을 가리키기 위해서 쓰는 거
    private List<UsersChallenges> usersChallengesList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "challenge")
    private List<Articles> articlesList = new ArrayList<>();
}
