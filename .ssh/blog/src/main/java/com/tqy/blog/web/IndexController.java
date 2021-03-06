package com.tqy.blog.web;


import com.sun.org.apache.xpath.internal.operations.Mod;
import com.tqy.blog.NotFoundException;
import com.tqy.blog.service.BlogService;
import com.tqy.blog.service.TagService;
import com.tqy.blog.service.TypeService;
import com.tqy.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size=8,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                         Model model) {
        model.addAttribute("page",blogService.ListBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendBlogs",blogService.ListRecommedBlogTop(8));
        return "index";
    }

    @PostMapping("/search")
    public String Search(@PageableDefault(size=8,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam  String query, Model model) {
        model.addAttribute("page",blogService.ListBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);

        return "search";
    }
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model) {
        model.addAttribute("blog",blogService.getAndConvert(id));
        return "blog";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newBlogs",blogService.ListRecommedBlogTop(3));
        return "_fragments :: newblogList";
    }
}


