package com.project.orange.service.article;

import com.project.orange.entity.article.Articles;
import com.project.orange.entity.challenge.Challenges;
import com.project.orange.entity.user.UsersChallenges;
import com.project.orange.repository.article.ArticlesRepository;
import com.project.orange.repository.challenge.ChallengesRepository;
import com.project.orange.repository.user.UsersChallengesRepository;
import com.project.orange.service.user.BadgesUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static com.project.orange.management.Constants.*;

@Service
@Transactional
public class ArticlesServiceImpl implements ArticlesService{

    @Autowired
    private ArticlesRepository articlesRepository;

    @Override
    public Optional<Articles> selectOne(Long articleId) { return articlesRepository.findById(articleId); }

    @Override
    public List<Articles> selectAll() { return articlesRepository.findAll(); }

    @Autowired
    private BadgesUsersService badgesUsersService;

    @Autowired
    private ChallengesRepository challengesRepository;

    @Autowired
    private UsersChallengesRepository usersChallengesRepository;


    @Override
    public Optional<Articles> createArticle(Articles article) {
        // 처음 피드 작성시 9번 뱃지 부여 로직
        badgesUsersService.badgeAwardAndNotify(article.getUser(), HereICameBadgeId);

        article.setArticleWritetime(LocalDateTime.now());
        Articles newArticle = articlesRepository.save(article);

        Long targetUserId = newArticle.getUser();
        Long targetChallengeId = newArticle.getChallenge();

        Challenges targetChallenge = challengesRepository.getById(targetChallengeId);
        UsersChallenges targetUsersChallenges;
        targetUsersChallenges = usersChallengesRepository.
                findByUserUserIdAndChallengeChallengeId(targetUserId, targetChallengeId).get();

        // 챌린지 total 점수에 반영
        int currentTotalPoint = targetChallenge.getTotalPoint();
        targetChallenge.setTotalPoint(currentTotalPoint + ArticleBasePoint);
        challengesRepository.save(targetChallenge);

        // 개인 기록에 반영
        int currentPersonalPoint = targetUsersChallenges.getPoint();
        targetUsersChallenges.setPoint(currentPersonalPoint + ArticleBasePoint);
        usersChallengesRepository.save(targetUsersChallenges);

        return Optional.ofNullable(newArticle);
    }

    @Override
    public Optional<Articles> updateArticle(Articles article) {
        Articles updatedArticle = articlesRepository.save(article);

        return Optional.ofNullable(updatedArticle);
    }

    @Override
    public List<Articles> selectAllByUserId(Long userId) {
        return articlesRepository.findAllByUser(userId);
    }

    @Override
    public List<Articles> selectAllByChallengeId(Long challenge) {
        return articlesRepository.findAllByChallenge(challenge);
    }


    @Override
    public void deleteByArticleId(Long articleId) { articlesRepository.deleteById(articleId); }

}
