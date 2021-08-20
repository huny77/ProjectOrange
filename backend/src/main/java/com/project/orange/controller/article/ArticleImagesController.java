package com.project.orange.controller.article;

import com.project.orange.entity.article.ArticleImages;
import com.project.orange.service.article.ArticleImagesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/articleimages")
public class ArticleImagesController {
    @Autowired
    private ArticleImagesService articleImagesService;

    @ApiOperation(value = "피드 이미지 목록", notes = "<big>모든 피드 이미지</big>의 <big>목록</big> 반환")
    @GetMapping("/list")
    public ResponseEntity<List<ArticleImages>> selectAll(){
        List<ArticleImages> list = articleImagesService.selectAll();
        if(list != null && !list.isEmpty()) {
            return new ResponseEntity<List<ArticleImages>>(list, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/search/{articleId}")
    public ResponseEntity<List<ArticleImages>> selectAllByArticleId(@PathVariable Long articleId){
        List<ArticleImages> list = articleImagesService.selectAllByArticleId(articleId);
        if(list != null && !list.isEmpty()) {
            return new ResponseEntity<List<ArticleImages>>(list, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
