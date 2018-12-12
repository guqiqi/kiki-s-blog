package kiki.blog.backend.controller;

import kiki.blog.backend.entity.Blog;
import kiki.blog.backend.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping("getAllBlog")
    @ResponseBody
    private List<Blog> getAllUser() {
        List<Blog> blogs = blogService.getAllBlog();

        return blogs;
    }
}
