package com.project.orange.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "badges_users")
@Builder
public class BadgesUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "badge_count")
    private Integer badgeCount;

    @Column(name = "represent_badge")
    private boolean representBadge;


    @Column(name = "badge_id")
    private Long badge;

    @Column(name = "user_id")
    private Long user;
}
