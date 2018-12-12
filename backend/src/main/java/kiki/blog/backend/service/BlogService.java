package kiki.blog.backend.service;

import kiki.blog.backend.entity.Blog;
import kiki.blog.backend.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getAllBlog(){
        List<Blog> blogList;
        try {
            blogList = blogRepository.find();
        } catch (Exception e) {
            System.out.println(e);
            blogList = new ArrayList<>();
        }
        return blogList;
    }

    public boolean saveBlog(String title, String summary, String content){
        Blog blog = new Blog(title, summary, content);
        try {
            blogRepository.save(blog);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Blog getBlogById(int id){
        Blog blog;
        try {
            blog = blogRepository.getBlog(id);
            blogRepository.update(id, blog.getReader()+1);
        } catch (Exception e) {
            System.out.println(e);
            blog = new Blog();
        }
        return blog;
    }
}
