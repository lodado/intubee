package com.Introbe.mainpage.Board;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;

import com.Introbe.IntuDatabase.Util.photo;

public class listViewItem {

    private Uri myUri;
    private Drawable iconDrawable ;
    private String titleStr ;
    private String descStr ;

    public void setIcon(Drawable icon) { iconDrawable = icon ; }
    public void setUri(Uri myUri){this.myUri = myUri; }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public Uri getUri(){return this.myUri;}
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }

}
