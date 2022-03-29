package com.tqy.blog.service;

import com.tqy.blog.po.Comment;

import java.util.List;

/**
 * @author tqy
 * @version 1.0
 * @date 2021/4/25 11:17 下午
 */
public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);
    Comment saveComment(Comment comment);
}
