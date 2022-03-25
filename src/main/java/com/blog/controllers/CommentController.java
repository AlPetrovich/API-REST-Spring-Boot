package com.blog.controllers;

import com.blog.dtos.CommentDTO;
import com.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/publication/{publicationId}/comments")
    public ResponseEntity<List<CommentDTO>> listCommentByPublication(@PathVariable("publicationId") Long publicationId){
        return new ResponseEntity<>(commentService.getCommentsByPublicationId(publicationId), HttpStatus.OK);
    }

    @GetMapping("publication/{publicationId}/comment/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(
            @PathVariable("publicationId") Long publicationId,
            @PathVariable("commentId") Long commentId
    ){
        CommentDTO commentDTO = commentService.findCommentById(publicationId,commentId);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @PostMapping("/publication/{publicationId}/comment")
    public ResponseEntity<CommentDTO> saveComment(
        @PathVariable("publicationId") Long publicationId,
        @RequestBody CommentDTO commentDTO
    ){
        return new ResponseEntity<>(commentService.createComment(publicationId, commentDTO), HttpStatus.CREATED);
    }

    @PutMapping("publication/{publicationId}/comment/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable("publicationId") Long publicationId,
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentDTO commentDTO
    ){
        CommentDTO commentUpdated = commentService.updateComment(publicationId,commentId, commentDTO);
        return new ResponseEntity<>(commentUpdated, HttpStatus.OK);
    }

    @DeleteMapping("publication/{publicationId}/comment/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable("publicationId") Long publicationId,
            @PathVariable("commentId") Long commentId
    ){
        commentService.deleteComment(publicationId,commentId);
        return new ResponseEntity<>("Comment Deleted", HttpStatus.OK);
    }

}
