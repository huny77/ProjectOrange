package com.project.orange.controller.user;

import com.project.orange.entity.user.BadgesUsers;
import com.project.orange.service.user.BadgesUsersService;
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
@RequestMapping("/api/badgesusers")
public class BadgesUsersController {
    @Autowired
    private BadgesUsersService badgesUsersService;

    @ApiOperation(value = "해당 유저가 가지고있는 모든 뱃지")
    @GetMapping("/{userId}")
    public ResponseEntity<List<BadgesUsers>> selectAllByUserId(@PathVariable Long userId){
        List<BadgesUsers> list;
        list = badgesUsersService.selectAllByUserId(userId);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }
}