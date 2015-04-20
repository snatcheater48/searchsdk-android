package com.yahoo.android.search.showcase.data;

import java.util.List;

public class TumblrData {

    private String blog_name;
    private String post_url;
    private List<String> tags;
    private String caption;
    private String photoUrl;

    public TumblrData(String blog_name, String post_url, List<String> tags, String caption, String pUrl) {
        this.blog_name = blog_name;
        this.post_url = post_url;
        this.tags = tags;
        this.caption = caption;
        this.photoUrl = pUrl;
    }

    public String getBlog_name() {
        return blog_name;
    }

    public void setBlog_name(String blog_name) {
        this.blog_name = blog_name;
    }

    public String getPost_url() {
        return post_url;
    }

    public void setPost_url(String post_url) {
        this.post_url = post_url;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getHashTagSeparatedTagList() {
        StringBuffer hashtagList = new StringBuffer();
        if (tags != null) {
            for (String s : tags) {
                hashtagList.append("#" + s);
            }
        }
        return hashtagList.toString();
    }
}
