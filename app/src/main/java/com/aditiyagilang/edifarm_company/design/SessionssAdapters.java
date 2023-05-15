//package com.aditiyagilang.edifarm_company.design;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.aditiyagilang.edifarm_company.R;
//import com.aditiyagilang.edifarm_company.model.getSession.GetSessionDataItem;
//
//import java.util.List;
//
//public class SessionssAdapters extends RecyclerView.Adapter<SessionssAdapters.ViewHolder> {
//    private final List<GetSessionDataItem> items;
//    private final LayoutInflater inflater;
//
//    public SessionssAdapters(Context context, List<GetSessionDataItem> items) {
//        this.items = items;
//        inflater = LayoutInflater.from(context);
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = inflater.inflate(R.layout.list_item_session, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        GetSessionDataItem item = items.get(position);
//        holder.textView.setText(item.getPlantName());
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView textView;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            textView = itemView.findViewById(android.R.id.text11);
//        }
//    }
//}
