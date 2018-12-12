package kiki.blog.backend.service;

import kiki.blog.backend.entity.Blog;
import kiki.blog.backend.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getAllBlog(){
        List<Blog> blogList = null;
        try {
            blogList = blogRepository.find();
        } catch (Exception e) {
        }
        return blogList;
    }
}
