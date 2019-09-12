package com.mylistview.model;

import java.io.Serializable;

/**
 * Created by SHARAD on 11/2/2017.
 */

public class RowData implements Serializable
{

    private String main_title,sub_title,version_title;
    private  int img_title;

    public RowData(String main_title,String sub_title,String version_title,int img_title)
    {
      this.main_title=main_title;
        this.sub_title=sub_title;
        this.version_title=version_title;
        this.img_title=img_title;
    }


    public String getMain_title() {
        return main_title;
    }

    public void setMain_title(String main_title) {
        this.main_title = main_title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getVersion_title() {
        return version_title;
    }

    public void setVersion_title(String version_title) {
        this.version_title = version_title;
    }

    public int getImg_title() {
        return img_title;
    }

    public void setImg_title(int img_title) {
        this.img_title = img_title;
    }


}
