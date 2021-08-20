package com.project.orange.service.article;

import com.project.orange.entity.article.ArticleImages;
import com.project.orange.entity.article.Articles;
import com.project.orange.entity.challenge.Challenges;
import com.project.orange.entity.user.Users;
import com.project.orange.entity.user.UsersChallenges;
import com.project.orange.repository.article.ArticleImagesRepository;
import com.project.orange.repository.challenge.ChallengesRepository;
import com.project.orange.repository.user.UserRepository;
import com.project.orange.repository.user.UsersChallengesRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.project.orange.management.Constants.ImageBonusPoint;

@Service
@Transactional
public class ArticleImagesServiceImpl implements ArticleImagesService{

    @Autowired
    private ArticleImagesRepository articleImagesRepository;

    @Autowired
    private UsersChallengesRepository usersChallengesRepository;

    @Autowired
    private ChallengesRepository challengesRepository;

    @Override
    public Optional<ArticleImages> selectOne(Long imageId) {return articleImagesRepository.findById(imageId); }

    @Override
    public List<ArticleImages> selectAll() { return articleImagesRepository.findAll(); }

    @Override
    public List<ArticleImages> selectAllByArticleId(Long articleId) {
        return articleImagesRepository.findAllByArticleArticleId(articleId);
    }

    @Override
    public List<ArticleImages> saveAll(List<ArticleImages> articleImagesList) {

        Articles targetArticle = articleImagesList.get(0).getArticle();
        Long targetUserId = targetArticle.getUser();
        Challenges targetChallenge = challengesRepository.getById(targetArticle.getChallenge());
        Long targetChallengeId = targetChallenge.getChallengeId();
        UsersChallenges targetUser;
        targetUser= usersChallengesRepository.
                findByUserUserIdAndChallengeChallengeId(targetUserId, targetChallengeId).get();

        // challenge total point에 반영
        int currentTotalPoint = targetChallenge.getTotalPoint();
        targetChallenge.setTotalPoint(currentTotalPoint + ImageBonusPoint);
        challengesRepository.save(targetChallenge);

        // 개인 기록에 반영
        int currentPersonalPoint = targetUser.getPoint();
        targetUser.setPoint(currentPersonalPoint + ImageBonusPoint);
        usersChallengesRepository.save(targetUser);

        return articleImagesRepository.saveAll(articleImagesList);
    }
}
