package com.blog.services;

import com.blog.dtos.CommentDTO;

import java.util.List;

public interface CommentService {

    public CommentDTO createComment(Long publicationId, CommentDTO commentDTO);

    public List<CommentDTO> getCommentsByPublicationId(Long publicationId);

    public CommentDTO findCommentById(Long publicationId, Long commentId);

    public CommentDTO updateComment(Long publicationId,Long commentId, CommentDTO commentDTO);

    public void deleteComment(Long publicationId, Long commentId);
}
