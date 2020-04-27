package kiki.blog.backend.requestBody;

public class BlogPost {
    private String title;
    private String summary;
    private String content;
    private String[] tags;

    public BlogPost(String title, String summary, String content, String[] tags) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.tags = tags;
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

    public String[] getTags() {
        return tags;
    }
}
