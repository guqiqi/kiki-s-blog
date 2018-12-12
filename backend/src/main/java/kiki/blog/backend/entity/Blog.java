package kiki.blog.backend.entity;

import javax.persistence.*;

@Entity //加入这个注解，Demo就会进行持久化了
@Table(name="blog")
public class Blog {
    @Id
    @GeneratedValue
    private int id;
    @Column(name="title")
    private String title;
    @Column(name="summary")
    private String summary;
    @Column(name="content")
    private String content;
    @Column(name="writer")
    private String writer;
    @Column(name="date")
    private String date;
    @Column(name="reader")
    private int reader;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getReader() {
        return reader;
    }

    public void setReader(int reader) {
        this.reader = reader;
    }
}
