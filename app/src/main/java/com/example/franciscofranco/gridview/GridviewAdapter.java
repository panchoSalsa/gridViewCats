package com.example.franciscofranco.gridview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by FranciscoFranco on 8/12/16.
 */
public class GridviewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> items;
    private ViewHolder viewHolder;
    private LayoutInflater inflater;

    public GridviewAdapter(Context context, ArrayList<String> items, LayoutInflater inflater) {
        super();
        this.context = context;
        this.items = items;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("FRANCO_DEBUG", "inside getView");
        ImageView img = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.single_image, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.your_imageview);
            convertView.setTag(viewHolder);
            //img = new ImageView(context);

        } else {
            //img = (ImageView) convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context)
                .load(items.get(position))
                .placeholder(R.mipmap.ic_launcher)
                .resize(400,400)
                .into(viewHolder.imageView,  new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d("FRANCO_DEBUG", "success");
                    }

                    @Override
                    public void onError() {
                        Log.d("FRANCO_DEBUG", "failed");
                    }
                });

        return convertView;
    }
}
