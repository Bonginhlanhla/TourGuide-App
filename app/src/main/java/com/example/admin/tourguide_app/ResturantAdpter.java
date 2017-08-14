package com.example.admin.tourguide_app;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Admin on 8/3/2017.
 */

public class ResturantAdpter extends ArrayAdapter<ResturantUpload> {
    private Activity context;
    private int resourece;
    private List<ResturantUpload> listImage;

    public ResturantAdpter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<ResturantUpload> objects) {
        super(context, resource, objects);
        this.context = context;
        listImage = objects;

    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = context.getLayoutInflater();

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.image_item, parent, false);
        }

        TextView textView = (TextView) listItemView.findViewById(R.id.txt_name);
        TextView txtDescrp = (TextView) listItemView.findViewById(R.id.txt_descrip);
        ImageView img = (ImageView) listItemView.findViewById(R.id.imgView);

        textView.setText(listImage.get(position).getRestName());
        txtDescrp.setText(listImage.get(position).getRestDescrp());
        Glide.with(context).load(listImage.get(position).getRestUrl()).into(img);

        return listItemView;
    }

}
