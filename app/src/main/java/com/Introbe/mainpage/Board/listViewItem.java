package com.Introbe.mainpage.Board;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;

import com.Introbe.IntuDatabase.Util.photo;

public class listViewItem {

    private boolean isshown=false;
    private String myUri;
    private Drawable iconDrawable;
    private String titleStr;
    private String descStr;

    public void setShown(boolean ls){isshown=ls;}
    //public void setIcon(Drawable icon) { iconDrawable = icon ; }
    public void setUri(String myUri){this.myUri = myUri; }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }

    public boolean getshown(){return isshown;}
    //public Drawable getIcon() {return this.iconDrawable ;}
    public String getUri(){return this.myUri;}
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }


}
