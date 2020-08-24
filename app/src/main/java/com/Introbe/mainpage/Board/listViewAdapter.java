package com.Introbe.mainpage.Board;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.Introbe.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class listViewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<listViewItem> listViewItemList = new ArrayList<listViewItem>() ;

    private int count =0;

    // ListViewAdapter의 생성자
    public listViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_sns, parent, false);
            ViewGroup.LayoutParams layoutParams = convertView.getLayoutParams();
            layoutParams.height=850;
            convertView.setLayoutParams(layoutParams);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        final ImageView iconImageView = (ImageView) convertView.findViewById(R.id.imageView1) ;
        final TextView titleTextView = (TextView) convertView.findViewById(R.id.listuserID) ;
        final TextView descTextView = (TextView) convertView.findViewById(R.id.textView2) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final listViewItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영

        FirebaseStorage sto = FirebaseStorage.getInstance();
        StorageReference storageRef = sto.getReference();

      storageRef.child("IMAGE/"+listViewItem.getUri()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {


                Glide.with(context)
                        .load(uri)
                        .into(iconImageView);
                titleTextView.setText(listViewItem.getTitle());
                descTextView.setText(listViewItem.getDesc());


                iconImageView.setScaleType(ImageView.ScaleType.FIT_XY);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(Context context,String title, String desc, String myUri) {
        listViewItem item = new listViewItem();

        item.setUri(myUri);
        item.setTitle(title);
        item.setDesc(desc);

        listViewItemList.add(item);
    }
}

