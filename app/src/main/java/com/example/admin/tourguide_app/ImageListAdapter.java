package com.example.admin.tourguide_app;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Admin on 8/2/2017.
 */

public class ImageListAdapter  extends ArrayAdapter<ImageUpload> {

    private Activity context;
    private int resourece;
    private List<ImageUpload> listImage;

    public ImageListAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<ImageUpload> objects) {
        super(context, resource, objects);
        this.context = context;
        listImage = objects;

    }

    @NonNull
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

        textView.setText(listImage.get(position).getName());
        txtDescrp.setText(listImage.get(position).getDescription());
        Glide.with(context).load(listImage.get(position).getUrl()).into(img);

        return listItemView;
    }

}
