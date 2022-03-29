package com.tqy.blog.web;

import com.tqy.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author tqy
 * @version 1.0
 * @date 2021/5/17 7:05 下午
 */

@Controller
public class ArchivesShowController {
    @Autowired
    public BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("blogCount",blogService.countBlog());
        return "/archives";
    }
}
