package com.example.android.newsreader;

public class Article {

    // MARK:- Properties
    private String sectionName;
    private String publicationDate;
    private String title;
    private String contentURL;
    private String authorName;

    public Article(String articleTitle, String articleSection, String articleAuthor, String articlePublication, String articleURL) {
        title = articleTitle;
        sectionName = articleSection;
        publicationDate = articlePublication;
        contentURL = articleURL;
        authorName = articleAuthor;
    }

    public String getTitle() { return title; }
    public String getSectionName() { return sectionName; }
    public String getPublicationDate() { return "Published: " + publicationDate; }
    public String getContentURL() { return contentURL; }
    public String getAuthorName() { return "By " + authorName; }
}
