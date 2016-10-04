package com.android.ipshita.newsapp;

/**
 * Created by Ipshita on 03-10-2016.
 */
public class news {

    private String Murl;
    private String Mtitle;
    private String Msubtitle;
    private String[] Mauthor;
    private boolean hasAuthor;

    public news(String title,String stitle, String url)
    {
        Murl=url;
        Msubtitle=stitle;
        Mtitle=title;
        hasAuthor = false;
    }

    public news(String title, String stitle, String url, String[] author)
    {
        Murl=url;
        Msubtitle=stitle;
        Mtitle=title;
        Mauthor=author;
        hasAuthor = true;
    }

    public String getTitle(){return Mtitle;}
    public String getSubTitle(){return Msubtitle;}
    public String getUrl(){return Murl;}
    public String[] getAuthor() {
        return Mauthor;
    }
    public boolean getHasAuthor() {
        return hasAuthor;
    }
}
