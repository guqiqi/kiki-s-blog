package kiki.blog.backend.controller;

import kiki.blog.backend.entity.Blog;
import kiki.blog.backend.requestBody.BlogPost;
import kiki.blog.backend.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping(value="getAllBlog", method = RequestMethod.GET)
    @ResponseBody
    public List<Blog> getAllBlog() {
        return blogService.getAllBlog();
    }

    @RequestMapping(value="getBlogByYear", method = RequestMethod.GET)
    @ResponseBody
    public List<Blog> getBlogByYear(@RequestParam String year) {
        return blogService.getBlogByYear(year);
    }

    @RequestMapping(value="getBlogByTag", method = RequestMethod.GET)
    @ResponseBody
    public List<Blog> getBlogByTag(@RequestParam String tag) {
        return blogService.getBlogByTag(tag);
    }

    @RequestMapping(value= "postBlog", method = RequestMethod.POST)
    @ResponseBody
    public void addBlog(@RequestBody BlogPost blogPost)  {
        String title = blogPost.getTitle();
        String summary = blogPost.getSummary();
        String content = blogPost.getContent();
        String[] tags = blogPost.getTags();

        blogService.saveBlog(title, summary, content, tags);
    }

    @RequestMapping(value= "getBlog", method = RequestMethod.GET)
    @ResponseBody
    public Blog getBlogById(@RequestParam int id)  {
        return blogService.getBlogById(id);
    }
}
