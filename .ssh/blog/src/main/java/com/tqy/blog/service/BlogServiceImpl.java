package com.tqy.blog.service;

import com.tqy.blog.NotFoundException;
import com.tqy.blog.dao.BlogRepository;
import com.tqy.blog.po.Blog;
import com.tqy.blog.po.Type;
import com.tqy.blog.util.MarkdownUtils;
import com.tqy.blog.util.MyBeanUtils;
import com.tqy.blog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.*;


/**
 * @author tqy
 * @version 1.0
 * @date 2021/3/15 10:21 下午
 */
@Service
public class BlogServiceImpl implements BlogService{
    @Autowired
    private BlogRepository blogRepository;

    @Transactional
    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public Blog getAndConvert(Long id) {
        Blog blog =blogRepository.getOne(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");

        }
        Blog b =new Blog();
        BeanUtils.copyProperties(blog,b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        blogRepository.updateViews(id);
        return b;
    }

    @Transactional
    @Override
    public Page<Blog> ListBlog(Pageable pageable, BlogQuery blog) {


        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() !=null) {
                    predicates.add(cb.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
                }
                if(blog.getTypeId() !=null){
                    predicates.add(cb.equal(root.<Type>get("type").get("id"),blog.getTypeId()));
                }
                if(blog.isRecommend()){
                    predicates.add(cb.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }


    @Override
    public Page<Blog> ListBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> ListBlog(Long tagId, Pageable pageable) {

        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join = root.join("tags");
                return cb.equal(join.get("id"),tagId);
            }
        },pageable);
    }

    @Override
    public Page<Blog> ListBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery(query,pageable);
    }


    @Override
    public List<Blog> ListRecommedBlogTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");;
        Pageable pageable = PageRequest.of(0, size, sort);
        return blogRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if (blog.getId() == null) {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else {
            blog.setUpdateTime(new Date());
        }

        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.getOne(id);
        if (b == null) {
            throw new NotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(blog,b, MyBeanUtils.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());
        return blogRepository.save(b);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);

    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogRepository.findGroupYears();
        TreeMap<String,List<Blog>> map = new TreeMap<>((o1, o2)->Integer.parseInt(o2)-Integer.parseInt(o1));
        for (String year : years) {
            map.put(year,blogRepository.findByYear(year));
        }

        return map;
    }

    @Override
    public Long countBlog() {

        return blogRepository.count();
    }
}
