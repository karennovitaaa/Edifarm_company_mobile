//package com.aditiyagilang.edifarm_company.design;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.aditiyagilang.edifarm_company.R;
//import com.aditiyagilang.edifarm_company.model.model_dashboard;
//
//import java.util.ArrayList;
//
//public class dasboardAdapter extends BaseAdapter {
//
//    private final Context context;
//    private final ArrayList<model_dashboard> model_dashboards;
//
//    public dasboardAdapter(Context context, ArrayList<model_dashboard> sosmedlist) {
//        this.context = context;
//        this.model_dashboards = sosmedlist;
//    }
//
//    @Override
//    public int getCount() {
//        return model_dashboards.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return model_dashboards.get(position);
//    }
//
//    @Override
//    public long getItemId(int id) {
//        return id;
//    }
//
//    @Override
//    public View getView(int position, View convertview, ViewGroup parent) {
//        HolderView holderView;
//        if(convertview == null){
//            convertview = LayoutInflater.from(context).inflate(R.layout.card_posting,parent,false);
//
//            holderView = new HolderView(convertview);
//            convertview.setTag(holderView);
//        }
//        else {
//            holderView = (HolderView) convertview.getTag();
//        }
//
//        model_dashboard list = model_dashboards.get(position);
//        holderView.medsos.setImageResource(list.getMedsos());
//        holderView.name.setText(list.getName());
//        return convertview;
//    }
//
//    private static class HolderView{
//        private final ImageView medsos;
//        private final TextView name;
//
//        public  HolderView(View view){
//            medsos = view.findViewById(R.id.medsos);
//            name = view.findViewById(R.id.name);
//        }
//    }
//}
