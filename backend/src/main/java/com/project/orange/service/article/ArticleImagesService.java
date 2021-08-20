package com.project.orange.service.article;

import com.project.orange.entity.article.ArticleImages;

import java.util.List;
import java.util.Optional;

public interface ArticleImagesService {
    public Optional<ArticleImages> selectOne(Long imageId);
    public List<ArticleImages> selectAll();
    public List<ArticleImages> selectAllByArticleId(Long articleId);
    public List<ArticleImages> saveAll(List<ArticleImages> articleImagesList);
}
