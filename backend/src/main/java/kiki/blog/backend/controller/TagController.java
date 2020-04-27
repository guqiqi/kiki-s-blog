package kiki.blog.backend.controller;

import kiki.blog.backend.entity.Tag;
import kiki.blog.backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/tag/")
public class TagController {
    @Autowired
    private TagService tagService;

    @RequestMapping(value="getAllTag", method = RequestMethod.GET)
    @ResponseBody
    public List<Tag> getAllTag() {
        return tagService.getAllTag();
    }
}
