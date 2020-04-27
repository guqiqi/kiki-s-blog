package kiki.blog.backend.entity;

import javax.persistence.*;

@Entity //加入这个注解就会进行持久化了
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "name")
    private String tagName;

    public Tag(){

    }

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public int getTagId() {
        return id;
    }
}
