package com.project.orange.entity.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor //(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "articles")
@Builder
public class Articles{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long articleId;

//    피드에서 이미지와 댓글에 접근
    @JsonIgnore
    @OneToMany(mappedBy = "article")
    private List<ArticleImages> imagesList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "article")
    private List<Comments> commentsList = new ArrayList<>();

//    피드에서 챌린지와 유저에 접근
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "challenge_id")
//    private Challenges challenge;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private Users user;

    @Column(name = "challenge_id")
    private Long challenge;

    @Column(name = "user_id")
    private Long user;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "article_content")
    private String articleContent;

//    @Temporal(TemporalType.DATE)
    @Column(name = "article_writetime")
    private LocalDateTime articleWritetime;

    @Column(name = "verified")
    private Boolean verified;
}
