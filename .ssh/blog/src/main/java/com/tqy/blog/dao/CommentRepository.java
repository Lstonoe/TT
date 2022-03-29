package com.tqy.blog.dao;

import com.tqy.blog.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author tqy
 * @version 1.0
 * @date 2021/4/25 11:23 下午
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByBlogIdAndParentCommentNull(Long BlogId, Sort sort);
}
