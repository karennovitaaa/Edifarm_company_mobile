package com.aditiyagilang.edifarm_company.design;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.model.listDataPost;
import com.aditiyagilang.edifarm_company.post;

import java.util.ArrayList;

public class listDataPostAdapter extends BaseAdapter {

    public static Context context;
    private static ArrayList<listDataPost> listData;

    public listDataPostAdapter(Context context, ArrayList<listDataPost> listData) {
        this.context = context;
        this.listData = listData;
    }

    public listDataPostAdapter(post post, ArrayList<listDataPost> listData) {
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.posts_list_item, parent, false);
        holder = new Holder(convertView);
        convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        listDataPost list = listData.get(position);
        holder.nama.setText(list.getNama());
        holder.time.setText(list.getTime());
        holder.caption.setText(list.getCaption());
        holder.image.setImageResource(list.getImage());

        return convertView;
    }
    private static class Holder{
        private final TextView nama, time, caption;
        private final ImageView image;
        public Holder (View view) {
            nama = view.findViewById(R.id.textProfil);
            time = view.findViewById(R.id.textPost2);
            caption = view.findViewById(R.id.textPost1);
            image = view.findViewById(R.id.imageProfilPost);


        }
    }
}
