package com.project.orange.controller.article;

import com.project.orange.entity.article.Articles;
import com.project.orange.entity.article.Comments;
import com.project.orange.entity.challenge.Challenges;
import com.project.orange.entity.user.UsersChallenges;
import com.project.orange.repository.user.UsersChallengesRepository;
import com.project.orange.service.article.ArticlesService;
import com.project.orange.service.article.CommentsService;
import com.project.orange.service.challenge.ChallengeService;
import com.project.orange.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comment")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;

    @Autowired
    private ArticlesService articlesService;

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private UsersChallengesRepository usersChallengesRepository;

    @ApiOperation(value = "모든 댓글 목록", notes = "<big>모든 댓글</big>의 <big>목록</big> 반환")
    @GetMapping("/list")
    public ResponseEntity<List<Comments>> selectAll(){
        List<Comments> list = commentsService.selectAll();
        if(list != null && !list.isEmpty()) {
            return new ResponseEntity<List<Comments>>(list, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createComment(@RequestBody Comments comments) {

        Long targetUserId = comments.getUser();
        Long targetArticleId = comments.getArticle();
        Articles targetArticle = articlesService.selectOne(targetArticleId).get();
        Challenges targetChallenge = challengeService.selectByChallengeId(targetArticle.getChallenge()).get();
        Long targetChallengeId = targetChallenge.getChallengeId();

        if(usersChallengesRepository.findByUserUserIdAndChallengeChallengeId(targetUserId, targetChallengeId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Optional<Comments> createdComment;
        createdComment = commentsService.createComment(comments);

        if(createdComment.isPresent()){
            return new ResponseEntity(createdComment.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/update/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId, @RequestBody Comments comments){
        Optional<Comments> updateComment = commentsService.selectOne(commentId);

        updateComment.ifPresent(selectComment->{
            selectComment.setCommentContent(comments.getCommentContent());

            commentsService.updateComment(selectComment);
        });
        return new ResponseEntity(updateComment, HttpStatus.OK);
    }


    @GetMapping("/article/{articleId}")
    public ResponseEntity<List<Comments>> selectAllByArticleId(@PathVariable Long articleId){
        List<Comments> list;
        list = commentsService.selectAllByArticleId(articleId);
        if(list == null || list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> delete(@PathVariable Long commentId){
        try {
            commentsService.deleteByCommentId(commentId);
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("failed", HttpStatus.NO_CONTENT);
        }
    }
}
