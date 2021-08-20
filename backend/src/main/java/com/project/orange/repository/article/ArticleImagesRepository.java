package com.project.orange.repository.article;

import com.project.orange.entity.article.ArticleImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleImagesRepository extends JpaRepository<ArticleImages, Long> {
    public List<ArticleImages> findAllByArticleArticleId(Long articleId);
}
