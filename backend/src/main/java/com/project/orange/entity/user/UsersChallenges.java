package com.project.orange.entity.user;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.project.orange.entity.challenge.Challenges;
import com.project.orange.entity.user.Users;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="users_challenges")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class UsersChallenges {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="user_id")
    @ManyToOne
    private Users user;

    @JoinColumn(name="challenge_id")
    @ManyToOne
    private Challenges challenge;

    @Column(name="point")
    private Integer point;

    @Column(name="is_manager")
    private boolean isManager;
}
