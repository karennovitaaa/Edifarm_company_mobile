package com.aditiyagilang.edifarm_company.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.model.model_comment;
import com.google.android.material.transition.Hold;

import java.util.ArrayList;

public class comment_adapter extends BaseAdapter {
    private final Context context_comment;
    private final ArrayList<model_comment> listcomment;

    public comment_adapter(Context context_comment, ArrayList<model_comment> listcomment) {
        this.context_comment = context_comment;
        this.listcomment = listcomment;

    }

    @Override
    public int getCount() {
        return listcomment.size();
    }

    @Override
    public Object getItem(int position) {
        return listcomment.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        HolderView holderView;
        if (convertview == null) {
            convertview = LayoutInflater.from(context_comment).inflate(R.layout.card_comment, parent, false);

            holderView = new HolderView(convertview);
            convertview.setTag(holderView);
        } else {
            holderView = (HolderView) convertview.getTag();
        }
        model_comment list = listcomment.get(position);
        holderView.username_comment.setText(list.getUsername_comment());
        holderView.isi_comment.setText(list.getIsi_comment());
        return convertview;
    }

    private static class HolderView {
        private final TextView username_comment;
        private final TextView isi_comment;

        public HolderView(View view) {
            username_comment = view.findViewById(R.id.username_comment);
            isi_comment = view.findViewById(R.id.isi_comment);
        }

    }


}
