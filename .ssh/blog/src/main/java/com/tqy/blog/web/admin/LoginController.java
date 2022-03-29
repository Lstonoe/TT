package com.tqy.blog.web.admin;

import com.tqy.blog.po.User;
import com.tqy.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author tqy
 * @version 1.0
 * @date 2021/2/22 11:59 下午
 */
@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;
    @GetMapping()
    public String loginPage() {
        return "admin/login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.chekUser(username,password);
        if(user !=null) {
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }
        else {
            attributes.addFlashAttribute("message","用户名和密码错误！");
            return "redirect:/admin"; //重定向到login这个界面
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";

    }
}
