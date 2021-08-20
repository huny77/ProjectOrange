package com.project.orange.controller.image;

import com.project.orange.entity.article.ArticleImages;
import com.project.orange.entity.badge.Badges;
import com.project.orange.entity.challenge.Challenges;
import com.project.orange.entity.user.BadgesUsers;
import com.project.orange.entity.user.Users;
import com.project.orange.service.article.ArticleImagesService;
import com.project.orange.service.article.ArticlesService;
import com.project.orange.service.badge.BadgesService;
import com.project.orange.service.challenge.ChallengeService;
import com.project.orange.service.user.BadgesUsersService;
import com.project.orange.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.project.orange.management.Constants.*;

@RestController
@RequestMapping("/api/image")
public class ImageHandleController {

    @Autowired
    private ArticleImagesService articleImagesService;

    @Autowired
    private ArticlesService articlesService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private BadgesService badgesService;

    @Autowired
    private BadgesUsersService badgesUsersService;

    @PostMapping("/save/article")
    private ResponseEntity<?> saveArticleImage(@RequestParam("image") MultipartFile[] images,
                                               @RequestParam("articleId") Long articleId) throws IOException {

        Map<String, String> response = new HashMap<>();
        if(!images[0].isEmpty()){
            List<String> savedImageNameList = new ArrayList<>();
            String today = new SimpleDateFormat("yyMMdd").format(new Date());
            String saveDirectory = ImageRealPath + File.separator + today;

            File dir = new File(saveDirectory);
            if(!dir.exists()){
                dir.mkdirs();
            }
            for(MultipartFile eachImage : images){
                String originalImageName = eachImage.getOriginalFilename();
                if(!originalImageName.isEmpty()){
                    String extension = originalImageName.substring(originalImageName.lastIndexOf('.'));
                    String saveImageName = UUID.randomUUID().toString() + extension;

                    eachImage.transferTo(new File(dir, saveImageName));
                    savedImageNameList.add(today + "/" + saveImageName);
                }
            }

            List<ArticleImages> articleImagesList = new ArrayList<>();
            for(String savedImage : savedImageNameList){
                ArticleImages articleImage = new ArticleImages();
                articleImage.setArticle(articlesService.selectOne(articleId).get());
                articleImage.setImagePath(savedImage);

                articleImagesList.add(articleImage);
            }
            articleImagesService.saveAll(articleImagesList);

            response.put("result", "success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("result", "failed");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save/profile")
    private ResponseEntity<?> saveProfileImage(@RequestParam("image") MultipartFile[] images,
                                               @RequestParam("userId") Long userId) throws IOException {

        Map<String, String> response = new HashMap<>();
        System.out.println(images.length);
        if(images.length < 2) {
            if (!images[0].isEmpty()) {
                List<String> savedImageNameList = new ArrayList<>();
                String today = new SimpleDateFormat("yyMMdd").format(new Date());
                String saveDirectory = ImageRealPath + "/" + today;

                File dir = new File(saveDirectory);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                for (MultipartFile eachImage : images) {
                    String originalImageName = eachImage.getOriginalFilename();
                    if (!originalImageName.isEmpty()) {
                        String extension = originalImageName.substring(originalImageName.lastIndexOf('.'));
                        String saveImageName = UUID.randomUUID().toString() + extension;

                        eachImage.transferTo(new File(dir, saveImageName));
                        savedImageNameList.add(today + "/" + saveImageName);
                    }
                }

                Users user = userService.selectAllByUserId(userId);
                user.setProfileImagePath(savedImageNameList.get(0));
                userService.updateById(userId, user);

                response.put("result", "success");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.put("result", "there is no image.");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
        else {
            response.put("result", "expected 1 image, received : " + images.length);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/save/challenge")
    private ResponseEntity<?> saveChallengeImage(@RequestParam("image") MultipartFile[] images,
                                               @RequestParam("challengeId") Long challengeId) throws IOException {

        Map<String, String> response = new HashMap<>();
        System.out.println(images.length);
        if(images.length < 2) {
            if (!images[0].isEmpty()) {
                List<String> savedImageNameList = new ArrayList<>();
                String today = new SimpleDateFormat("yyMMdd").format(new Date());
                String saveDirectory = ImageRealPath + "/" + today;

                File dir = new File(saveDirectory);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                for (MultipartFile eachImage : images) {
                    String originalImageName = eachImage.getOriginalFilename();
                    if (!originalImageName.isEmpty()) {
                        String extension = originalImageName.substring(originalImageName.lastIndexOf('.'));
                        String saveImageName = UUID.randomUUID().toString() + extension;

                        eachImage.transferTo(new File(dir, saveImageName));
                        savedImageNameList.add(today + "/" + saveImageName);
                    }
                }

                Challenges challenge = challengeService.selectByChallengeId(challengeId).get();
                challenge.setImagePath(savedImageNameList.get(0));
                challengeService.save(challenge);

                response.put("result", "success");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.put("result", "there is no image.");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
        else {
            response.put("result", "expected 1 image, received : " + images.length);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get/article/{articleId}")
    public ResponseEntity<?> getArticleImageUrl(@PathVariable Long articleId){
        List<ArticleImages> articleImagesList;
        articleImagesList = articleImagesService.selectAllByArticleId(articleId);
        if(articleImagesList != null && !articleImagesList.isEmpty()) {
            List<String> result = new ArrayList<>();
            for (ArticleImages eachArticleImages : articleImagesList) {
                String image = eachArticleImages.getImagePath();
                result.add(ContextPath + ImageShowUrlPrefix + image);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            Map<String, String> noContentResult = new HashMap<>();
            noContentResult.put("result", "No Image!");
            return new ResponseEntity<>(noContentResult, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/get/challenge/{challengeId}")
    public ResponseEntity<?> getChallengeImageUrl(@PathVariable Long challengeId){
        Map<String, String> result = new HashMap<>();
        try {
            Challenges targetChallenge = challengeService.selectByChallengeId(challengeId).get();
            String challengeImage = targetChallenge.getImagePath();
            if(challengeImage != null) {
                result.put("result", ContextPath + ImageShowUrlPrefix + challengeImage);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            else {
                result.put("result", "No Image!");
                return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception e){
            result.put("result", "Internal Server Error");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/profile/{userId}")
    public ResponseEntity<?> getProfileImageUrl(@PathVariable Long userId){
        Map<String, String> result = new HashMap<>();
        try {
            Users targetUser = userService.selectAllByUserId(userId);
            String profileImage = targetUser.getProfileImagePath();
            if(profileImage != null) {
                result.put("result", ContextPath + ImageShowUrlPrefix + profileImage);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            else {
                result.put("result", "No Image!");
                return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception e){
            result.put("result", "Internal Server Error");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/badge-status/{userId}")
    public ResponseEntity<?> getBadgeStatusOfUser(@PathVariable Long userId){
        Map<String, String> result = new HashMap<>();
        if(userService.selectAllByUserId(userId)==null){
            result.put("result", "No User");
            return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
        }

        try {
            List<Badges> badgesList = badgesService.selectAll();
            List<BadgesUsers> targetUserBadgeList = badgesUsersService.selectAllByUserId(userId);
            List<Long> targetUserBadgeIdList = new ArrayList<>();

            for(BadgesUsers eachBadgesUsers : targetUserBadgeList){
                targetUserBadgeIdList.add(eachBadgesUsers.getBadge());
            }

            List<String> badgeImageUrlList = new ArrayList<>();
            for(Badges eachBadge : badgesList){
                Long badgeId = eachBadge.getBadgeId();
                boolean occupied = false;
                for(Long eachTargetUserBadgeId : targetUserBadgeIdList){
                    if(eachTargetUserBadgeId.equals(badgeId)){
                        occupied = true;
                        break;
                    }
                }
                String urlPrefix = ContextPath + ImageShowUrlPrefix + BadgeImageDirectory + BadgeImageNamePrefix;
                if(occupied){
                    badgeImageUrlList.add(urlPrefix + String.valueOf(badgeId) + BadgeImageExtension);
                } else {
                    badgeImageUrlList.add(urlPrefix + String.valueOf(badgeId) + DefaultBadgePostfix +BadgeImageExtension);
                }
            }
            return new ResponseEntity<>(badgeImageUrlList, HttpStatus.OK);
        }
        catch (Exception e){
            result.put("result", "Internal Server Error");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/init/badge-image")
    public ResponseEntity<?> initializeBadgeImages(@RequestParam("image") MultipartFile[] images) throws IOException {

        Map<String, String> response = new HashMap<>();
        System.out.println(images.length);
        if(images.length < 2) {
            if (!images[0].isEmpty()) {

                File dir = new File(BadgeImageRealPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                for (MultipartFile eachImage : images) {
                    String originalImageName = eachImage.getOriginalFilename();
                    if (!originalImageName.isEmpty()) {
                        eachImage.transferTo(new File(dir, originalImageName));
                    }
                }

                response.put("result", "success");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.put("result", "there is no image.");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
        else {
            response.put("result", "expected 1 image, received : " + images.length);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
}
