package com.blog.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.blog.service.CommentService;
import com.blog.vo.Comment;
import com.blog.vo.Result;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class CommentController {
    
    @Autowired
    CommentService commentService;

    @PostMapping("/comment")
    public Object savePost(HttpServletResponse response, @RequestBody Comment commentParam) {
        Comment comment = new Comment(commentParam.getPostId(), commentParam.getUser(), commentParam.getComment());

        boolean isSuccess = commentService.saveComment(comment);

        if (isSuccess) {
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
    }

    @GetMapping("/comment")
    public Object getComment(@RequestParam("id") Long id) {
        Comment comment = commentService.getComment(id);

        return comment;
    }

    // get all comments from same post_id
    @GetMapping("/comments")
    public List<Comment> getComments(@RequestParam("post_id") Long postId) {
        List<Comment> comments = commentService.getCommentList(postId);

        return comments;
    }

    // Advanced exercise
    // search comment by post_id and query as param
    @GetMapping("/comments/search")
    public List<Comment> searchCommentsByPostIdAndQuery(@RequestParam("post_id") Long postId, @RequestParam("query") String query) {
        List<Comment> comments = commentService.searchCommentsByPostIdAndQuery(postId, query);

        return comments;
    }

    @DeleteMapping("/comment")
    public Object deleteComment(HttpServletResponse response, @RequestParam("id") Long id) {
        boolean isSuccess = commentService.deleteComment(id);

        if (isSuccess) {
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            
            return new Result(500, "Fail");
        }
    }

}
