package com.project.orange.repository.article;

import com.project.orange.entity.article.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticlesRepository extends JpaRepository<Articles, Long> {
    public List<Articles> findAllByChallenge(Long challenge);
    public List<Articles> findAllByUser(Long user);
}
