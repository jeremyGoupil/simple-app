package fr.arolla.core;

public class Feed {
    private long id;
    private String title;
    private String link;
    private String pubDate;
    private String creator;
    private String description;

    public Feed(long id, String title, String link, String pubDate, String creator, String description) {
        super();
        this.id = id;
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.creator = creator;
        this.description = description;
    }


    public Feed() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Feed [creator=" + creator + ", pubDate=" + pubDate + ", title="
                + title + "]";
    }


}
