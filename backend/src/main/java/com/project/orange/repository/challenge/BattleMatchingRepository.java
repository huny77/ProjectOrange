package com.project.orange.repository.challenge;

import com.project.orange.entity.challenge.BattleMatching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BattleMatchingRepository extends JpaRepository<BattleMatching, Long> {
    public Optional<BattleMatching> findByBlueTeamChallengeId(Long blueTeamId);
    public Optional<BattleMatching> findByRedTeamChallengeId(Long RedTeamId);
}
