package kiki.blog.backend.requestBody;

public class BlogPost {
    private String title;
    private String summary;
    private String content;

    public BlogPost(String title, String summary, String content) {
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getContent() {
        return content;
    }

}
