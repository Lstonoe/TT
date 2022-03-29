package com.tqy.blog.service;

import com.tqy.blog.po.Blog;
import com.tqy.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author tqy
 * @version 1.0
 * @date 2021/3/15 10:17 下午
 */
public interface BlogService {
    Blog getBlog(Long id);
    Blog getAndConvert(Long id);

    Page<Blog> ListBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> ListBlog(Pageable pageable);


    Page<Blog> ListBlog(Long tagId,Pageable pageable);
    Page<Blog> ListBlog(String query,Pageable pageable);


    List<Blog> ListRecommedBlogTop(Integer size);


    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);

    Map<String,List<Blog>> archiveBlog();
    Long countBlog();



}
