package com.tqy.blog.web;

import com.tqy.blog.po.Tag;
import com.tqy.blog.service.BlogService;
import com.tqy.blog.service.TagService;
import com.tqy.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author tqy
 * @version 1.0
 * @date 2021/5/10 8:15 下午
 */
@Controller
public class TagShowController {

    @Autowired
    private TagService TagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String Tags(@PageableDefault(size=8,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        Model model, @PathVariable Long id) {
        List<Tag> tags = TagService.listTagTop(10000);
        if(id == -1) {
            id = tags.get(0).getId();
        }
        model.addAttribute("tags",tags);
        model.addAttribute("page",blogService.ListBlog(id,pageable));
        model.addAttribute("activeTagId",id);
        return "tags";
    }
}
