package com.moblog.dev;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.moblog.dev.service.BlogService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping({ "/", "/blogs" })
    public String blogs(Model model) {
        var blogs = blogService.getBlogs();
        model.addAttribute("blogs", blogs);
        return "blogs";
    }

    @GetMapping("/blog")
    public String blog(@RequestParam int id, Model model) {
        var blog = blogService.getBlog(id);
        model.addAttribute("blog", blog);
        return "blog";
    }

    @GetMapping("/add-blog")
    public String addBlog() {
        return "add-blog";
    }

    @PostMapping("/add-blog")
    public String addBlog(@ModelAttribute Blog blog) {
        blogService.addBlog(blog);
        return "redirect:/blogs";
    }
}
