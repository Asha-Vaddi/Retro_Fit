package model;

import org.json.JSONObject;

import java.util.ArrayList;

public class Article {
    public String author;
    public String title;
    public String description;
    public String url;
    public String urlToimage;
    public String publishedAt;
    public String content;
    public Source source;

   /* public static Article parseArticleResponse(JSONObject jsonObject){
        Article item = new Article();
        item.author =jsonObject.optString("author");
        item.title = jsonObject.optString("title");
        item.description = jsonObject.optString("description");
        item.url = jsonObject.optString("url");
        item.urlToimage = jsonObject.optString("urlToImage");
        item.publishedAt = jsonObject.optString("publishedAt");
        item.content = jsonObject.optString("content");
        item.source = Source.parseSourceResponse(jsonObject.optJSONObject("source"));
        return item;
    } */
}
