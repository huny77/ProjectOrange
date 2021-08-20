package com.project.orange.entity.article;

import com.project.orange.entity.user.Users;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor//(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long commentId;
    @Column(name = "comment_content")
    private String commentContent;
    @Column(name = "comment_writetime")
    private LocalDateTime commentWritetime;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "article_id")
//    private Articles article;

    @Column(name = "article_id")
    private Long article;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private Users user;

    @Column(name = "user_id")
    private Long user;

    //    @Builder
//    public Comments(Long commentId, Long articleId, Long userId, String commentContent, Timestamp commentWritetime) {
//        this.commentId = commentId;
//        this.articleId = articleId;
//        this.userId = userId;
//        this.commentContent = commentContent;
//        this.commentWritetime = commentWritetime;
//    }
}
