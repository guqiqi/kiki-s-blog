package kiki.blog.backend.service;

import kiki.blog.backend.entity.Tag;
import kiki.blog.backend.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getAllTag(){
        List<Tag> tagList;
        try {
            tagList = tagRepository.find();
        } catch (Exception e) {
            System.out.println(e);
            tagList = new ArrayList<>();
        }
        return tagList;
    }
}
