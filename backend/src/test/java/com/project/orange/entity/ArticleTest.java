package com.project.orange.entity;

import com.project.orange.entity.article.Articles;
import com.project.orange.entity.challenge.Challenges;
import com.project.orange.repository.article.ArticlesRepository;
import com.project.orange.service.article.ArticlesService;
import com.project.orange.service.challenge.ChallengeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ArticleTest {

    @Autowired
    private ArticlesService articlesService;

    @Autowired
    private ArticlesRepository articlesRepository;

    @Test
    void createNewArticleTest(){
        Long challengeId = 7L;
//        Optional<Challenges> challenge = ChallengeService.selectByChallengeId(challengeId);
        Long userId = 18L;
        String title = "15점짜리 게시글";
        String articleContent = "이미지 저장 api는 별도로 호출해야 합니다";
        LocalDateTime now = LocalDateTime.now();
        Boolean verified = Boolean.TRUE;

        Articles article = Articles.builder()
                .title(title)
                .articleContent(articleContent)
                .articleWritetime(now)
                .verified(verified)
                .challenge(challengeId)
                .user(userId)
                .build();

        Optional<Articles> createdArticle = articlesService.createArticle(article);
        assertEquals(createdArticle.isEmpty(), false);

//        articlesRepository.deleteAll();


    }
}
