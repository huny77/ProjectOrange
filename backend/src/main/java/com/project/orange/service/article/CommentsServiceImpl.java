package com.project.orange.service.article;

import com.project.orange.entity.article.Comments;
import com.project.orange.repository.article.CommentsRepository;
import com.project.orange.repository.user.BadgesUsersRepository;
import com.project.orange.service.user.BadgesUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.project.orange.management.Constants.ReplyCommitteeBadgeId;

@Service
@Transactional
public class CommentsServiceImpl implements CommentsService{

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    public BadgesUsersRepository badgesUsersRepository;

    // 뱃지 부여 위한 코드
    @Autowired
    public BadgesUsersService badgesUsersService;

    @Override
    public Optional<Comments> createComment(Comments comments) {
        // 처음 댓글 작성시 10번 뱃지 부여 로직
        badgesUsersService.badgeAwardAndNotify(comments.getUser(), ReplyCommitteeBadgeId);
        comments.setCommentWritetime(LocalDateTime.now());
        Comments newComment = commentsRepository.save(comments);

        return Optional.ofNullable(newComment);
    }

    @Override
    public Optional<Comments> selectOne(Long commentId) { return commentsRepository.findById(commentId); }

    @Override
    public List<Comments> selectAll() { return commentsRepository.findAll(); }

    @Override
    public  List<Comments> selectAllByArticleId(Long article) {
        return commentsRepository.findAllByArticle(article);
    }

    @Override
    public Optional<Comments> updateComment(Comments comments) {
        Comments updatedComment = commentsRepository.save(comments);

        return Optional.ofNullable(updatedComment);
    }

    @Override
    public void deleteByCommentId(Long commentId) { commentsRepository.deleteById(commentId); }
}
