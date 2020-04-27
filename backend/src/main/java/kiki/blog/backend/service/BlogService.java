package kiki.blog.backend.service;

import kiki.blog.backend.entity.Blog;
import kiki.blog.backend.entity.Tag;
import kiki.blog.backend.repository.BlogRepository;
import kiki.blog.backend.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private TagRepository tagRepository;

    private String SPLITE = "&%#";

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

    public boolean saveBlog(String title, String summary, String content, String[] tags){
        StringBuilder tagIds = new StringBuilder();
        StringBuilder tagNames = new StringBuilder();

        List<Tag> tagList = tagRepository.find();
        for(int i = 0; i < tags.length; i++){
            if(!tags[i].equals("")){
                boolean isFound = false;
                for(int j = 0; j < tagList.size(); j++){
                    if(tagList.get(j).getTagName().equals(tags[i])){
                        tagIds.append(SPLITE).append(tagList.get(j).getTagId());
                        tagNames.append(SPLITE).append(tags[i]);
                        isFound = true;
                        break;
                    }
                }
                if(!isFound){
                    Tag tag = new Tag(tags[i]);
                    tag = tagRepository.save(tag);
                    tagIds.append(SPLITE).append(tag.getTagId());
                    tagNames.append(SPLITE).append(tag.getTagName());
                }
            }
        }

        Blog blog = new Blog(title, summary, content, tagIds.toString(), tagNames.toString());

        try {
            blogRepository.save(blog);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Blog> getBlogByYear(String year){
        return blogRepository.getBlogByYear(year);
    }

    public List<Blog> getBlogByTag(String tag) {
        List<Blog> blogList = blogRepository.find();
        List<Blog> result = new ArrayList<>();

        for(int i = 0; i < blogList.size(); i++){
            if(blogList.get(i).getTagNames() != null && !blogList.get(i).getTagNames().equals("")){
                String[] tags = blogList.get(i).getTagNames().split(SPLITE);
                for(int j = 0; j < tags.length; j++){
                    if(tags[j].equals(tag)){
                        result.add(blogList.get(i));
                        break;
                    }
                }
            }
        }

        return result;
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
