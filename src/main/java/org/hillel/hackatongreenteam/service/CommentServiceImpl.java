package org.hillel.hackatongreenteam.service;

import org.hillel.hackatongreenteam.model.Comment;
import org.hillel.hackatongreenteam.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ArticleService articleService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ArticleService articleService) {
        this.commentRepository = commentRepository;
        this.articleService = articleService;
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment read(int id) {
        return commentRepository.getOne(id);
    }

    @Override
    public List<Comment> readAll(int articleId) {
        return commentRepository.findAllByArticleOrderByDateCreatedDesc(articleService.read(articleId));
    }

    @Override
    public void delete(int id) {
        Comment comment = commentRepository.getOne(id);
        commentRepository.delete(comment);
    }
}
