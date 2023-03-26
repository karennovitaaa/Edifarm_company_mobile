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
//
//import com.aditiyagilang.edifarm_company.model.user;
//import com.aditiyagilang.edifarm_company.R;
//
//import java.util.List;
//
//public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
//    private Context context;
//    private List<user> userList;
//
//    public UserAdapter(Context context, List<user> userList) {
//        this.context = context;
//        this.userList = userList;
//    }
//
//    @NonNull
//    @Override
//    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
//        return new UserViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
//        user user = userList.get(position);
//        holder.usernameTextView.setText(user.getUsername());
//        holder.namaTextView.setText(user.getNama());
//        holder.phoneTextView.setText(user.getPhone());
//
//        // Tambahkan kode untuk menampilkan data lainnya (photo, address, born_date, dll.) jika diperlukan
//    }
//
//    @Override
//    public int getItemCount() {
//        return userList.size();
//    }
//
//    public class UserViewHolder extends RecyclerView.ViewHolder {
//        private TextView usernameTextView, namaTextView, phoneTextView;
//
//        public UserViewHolder(@NonNull View itemView) {
//            super(itemView);
//            usernameTextView = itemView.findViewById(R.id.username_field);
//            namaTextView = itemView.findViewById(R.id.nama_text_view);
//            phoneTextView = itemView.findViewById(R.id.phone_text_view);
//        }
//    }
//}
