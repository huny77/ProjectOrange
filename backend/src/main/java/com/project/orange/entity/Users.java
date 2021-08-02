package com.project.orange.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED) //접근 권한을 최소화
@ToString
@Getter
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;
    private String email;
    private String nickname;

    //일대다 관계는 엔티티를 하나 이상 참조할 수 있으므로, 자바 컬렉션인 Collection, List, Set, Map 중에 하나를 사용해야한다.(p209)
    //@OneToMany는 연관관계의 주인이 될 수 없다.
    //그래서 주인이 아닌 @OneToMany인 애가 주인을 가리키는 용도로 mappedby를 가짐
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Notifications> notificationsList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)//하나의 user가 여러 개의 report를 가진다.
    List<Reports> reportsList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY) //한 명의 user는 여러 개의 comment를 가진다.
    List<Comments> commentsList = new ArrayList<>();

//    @OneToMany(mappedBy = "user")
//    List<Articles> articlesList = new ArrayList<>();
    
    //getter setter와는 다른, 따로 존재하는 메소드인듯,,?
    public void addNotificationList(Notifications notification){
        notificationsList.add(notification);
        notification.setUser(this);
    }

    public void addReportsList(Reports report){
        reportsList.add(report);
        report.setUser(this);
    }

    public void addCommentsList(Comments comment){
        commentsList.add(comment);
        comment.setUser(this);
    }
//
//    //단축키 alt+insert
//    @Builder
//    public Users(Long userId, String email, String nickname) {
//        this.userId = userId;
//        this.email = email;
//        this.nickname = nickname;
//    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<Notifications> getNotificationsList() {
        return notificationsList;
    }

    public void setNotificationsList(List<Notifications> notificationsList) {
        this.notificationsList = notificationsList;
    }

    public List<Reports> getReportsList() {
        return reportsList;
    }

    public void setReportsList(List<Reports> reportsList) {
        this.reportsList = reportsList;
    }

    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }
}