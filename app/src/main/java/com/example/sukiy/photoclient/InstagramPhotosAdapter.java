package com.example.sukiy.photoclient;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by suki.y on 12/6/15.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {
    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    private String getDate(long time) {
        long now = System.currentTimeMillis()/1000;
        String result = (String) DateUtils.getRelativeTimeSpanString(time, now, 0);
        return result;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramPhoto photo = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.photo, parent, false);
        }
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        TextView tvUser = (TextView) convertView.findViewById(R.id.tvUser);
        TextView tvLikes = (TextView) convertView.findViewById(R.id.tvLikes);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
        ImageView ivUser = (ImageView) convertView.findViewById(R.id.ivUser);
        String likeStr = photo.likesCount + " likes";
        tvUser.setText(photo.username);
        tvCaption.setText(photo.caption);
        tvLikes.setText(likeStr);
        tvTime.setText(getDate(photo.createTime));
        ivUser.setImageResource(0); //clear out imageview
        ivPhoto.setImageResource(0); //clear out imageview
        ivPhoto.getLayoutParams().height = photo.imageHeight;
        Picasso.with(getContext()).load(photo.userprofileUrl).resize(50, 50).into(ivUser); //insert the image using Picasso
        Picasso.with(getContext()).load(photo.imageUrl).into(ivPhoto); //insert the image using Picasso
        return convertView;
    }
}
