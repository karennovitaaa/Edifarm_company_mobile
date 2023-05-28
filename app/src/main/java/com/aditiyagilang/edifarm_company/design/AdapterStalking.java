package com.aditiyagilang.edifarm_company.design;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.model.AddComment.AddComment;
import com.aditiyagilang.edifarm_company.model.AddReason.AddReason;
import com.aditiyagilang.edifarm_company.model.CountComment.CountComment;
import com.aditiyagilang.edifarm_company.model.CountLike.CountLike;
import com.aditiyagilang.edifarm_company.model.DeleteLike.DeleteLike;
import com.aditiyagilang.edifarm_company.model.GetComment.GetComment;
import com.aditiyagilang.edifarm_company.model.GetComment.GetCommentDataItem;
import com.aditiyagilang.edifarm_company.model.Like.Like;
import com.aditiyagilang.edifarm_company.model.Stalking.StalkingAcountDataItem;
import com.aditiyagilang.edifarm_company.model.getLike.GetLike;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterStalking extends RecyclerView.Adapter<AdapterStalking.AdapterHolder> {

    private final OnItemClickListener listener;
    private final Context context;
    private final List<StalkingAcountDataItem> dataList;
    private final SesionManager sesionManager;
    private final ApiInterface apiInterface;

    public AdapterStalking(Context context, List<StalkingAcountDataItem> dataList, OnItemClickListener listener) {
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;
        sesionManager = new SesionManager(context);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    @NonNull
    @Override
    public AdapterStalking.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_stalking, parent, false);
        return new AdapterHolder(view);

    }


    public void onBindViewHolder(@NonNull AdapterStalking.AdapterHolder holder, @SuppressLint("RecyclerView") int position) {
        final StalkingAcountDataItem item = dataList.get(position);
        String url = "http://edifarm.yoganova.my.id/";
        String textProfil = String.valueOf(item.getId());
        String fotoProfil = String.valueOf(item.getPhoto());
        String imageUrl = url + fotoProfil;

        String tanggal = String.valueOf(item.getCreatedAt());
        String gambarpost = String.valueOf(item.getImage());
        String imageUrlP = url + gambarpost;
        String textCaption = String.valueOf(item.getCaption());
        String akunname = item.getUsername();
        String tanggals = item.getCreatedAt().split(" ")[0];
        String cap = item.getCaption();
        String token = item.getFcmToken().toString();
        Picasso.get().load(imageUrl).into(holder.fotoProfil);
        Picasso.get().load(imageUrlP).into(holder.gambarPosting);
        holder.namaAkun.setText(akunname);
        holder.tanggalPost.setText(tanggals);
        holder.caption.setText(item.getCaption());
        String user_id = sesionManager.getUserDetail().get(SesionManager.ID);
        String post_id = String.valueOf(dataList.get(position).getId());


        Call<CountComment> countCommentCall = apiInterface.countUserByPostResponse(post_id);


        countCommentCall.enqueue(new Callback<CountComment>() {
            @Override
            public void onResponse(Call<CountComment> call, Response<CountComment> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    String count = String.valueOf(response.body().getData());
                    holder.jumlahComment.setText(count);
                }
            }

            @Override
            public void onFailure(Call<CountComment> call, Throwable t) {

            }
        });
        Call<CountLike> countLikeCall = apiInterface.CountLikeResponse(post_id);


        countLikeCall.enqueue(new Callback<CountLike>() {
            @Override
            public void onResponse(Call<CountLike> call, Response<CountLike> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    String count = String.valueOf(response.body().getData());
                    holder.jumlahLike.setText(count);
                }
            }

            @Override
            public void onFailure(Call<CountLike> call, Throwable t) {

            }
        });
        Call<GetLike> UpActCall = apiInterface.GetLikeResponse(user_id, post_id);
        UpActCall.enqueue(new Callback<GetLike>() {
            @Override
            public void onResponse(Call<GetLike> call, Response<GetLike> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    // update the activity status locally
                    ImageButton likeButton = holder.like.findViewById(R.id.like_buttonStalk);
                    likeButton.setBackgroundResource(R.drawable.like_red);

                    holder.like.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Call<DeleteLike> deleteLikeCall = apiInterface.DeleteLikeResponse(post_id, user_id);
                            deleteLikeCall.enqueue(new Callback<DeleteLike>() {
                                @Override
                                public void onResponse(Call<DeleteLike> call, Response<DeleteLike> response) {
                                    likeButton.setBackgroundResource(R.drawable.like_white);
                                    holder.like.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Call<Like> deleteLikeCall = apiInterface.LikeResponse(user_id, post_id, token);
                                            deleteLikeCall.enqueue(new Callback<Like>() {
                                                @Override
                                                public void onResponse(Call<Like> call, Response<Like> response) {
                                                    likeButton.setBackgroundResource(R.drawable.like_red);

                                                    reloadData();
                                                }

                                                @Override
                                                public void onFailure(Call<Like> call, Throwable t) {

                                                }
                                            });
                                            reloadData();
                                        }
                                    });
                                    Call<CountLike> countLikeCall = apiInterface.CountLikeResponse(post_id);
                                    countLikeCall.enqueue(new Callback<CountLike>() {
                                        @Override
                                        public void onResponse(Call<CountLike> call, Response<CountLike> response) {
                                            if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                                                String count = String.valueOf(response.body().getData());
                                                holder.jumlahLike.setText(count);
                                                reloadData();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<CountLike> call, Throwable t) {

                                        }
                                    });

                                }

                                @Override
                                public void onFailure(Call<DeleteLike> call, Throwable t) {

                                }
                            });
                        }
                    });
                } else {

                    ImageButton likeButton = holder.like.findViewById(R.id.like_buttonStalk);
                    likeButton.setBackgroundResource(R.drawable.like_white);

                    holder.like.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Call<Like> deleteLikeCall = apiInterface.LikeResponse(user_id, post_id, token);
                            deleteLikeCall.enqueue(new Callback<Like>() {
                                @Override
                                public void onResponse(Call<Like> call, Response<Like> response) {
                                    likeButton.setBackgroundResource(R.drawable.like_red);
                                    Call<CountLike> countLikeCall = apiInterface.CountLikeResponse(post_id);
                                    countLikeCall.enqueue(new Callback<CountLike>() {
                                        @Override
                                        public void onResponse(Call<CountLike> call, Response<CountLike> response) {
                                            if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                                                String count = String.valueOf(response.body().getData());
                                                holder.jumlahLike.setText(count);
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<CountLike> call, Throwable t) {

                                        }
                                    });
                                    reloadData();
                                }

                                @Override
                                public void onFailure(Call<Like> call, Throwable t) {

                                }
                            });


                        }
                    });
                }


            }


            @Override
            public void onFailure(Call<GetLike> call, Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(AdapterStalking.this, view, position, item);
                }
            }
        });
        holder.reason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.pop_up_reason);
                Button reason = dialog.findViewById(R.id.reason);
                Button cancel = dialog.findViewById(R.id.cancel_reason);

                reason.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Dialog innerDialog = new Dialog(context);
                        innerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        innerDialog.setContentView(R.layout.fragment_reason);
                        innerDialog.show();
                        ImageButton cancel = innerDialog.findViewById(R.id.cancel_reason_lose);
                        CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBoxLainnya;
                        EditText editTextReason;
                        Button buttonSubmit;
                        final StringBuilder reason = new StringBuilder();

                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                innerDialog.dismiss();
                            }
                        });
                        checkBox1 = innerDialog.findViewById(R.id.checkBox1);
                        checkBox2 = innerDialog.findViewById(R.id.checkBox2);
                        checkBox3 = innerDialog.findViewById(R.id.checkBox3);
                        checkBox4 = innerDialog.findViewById(R.id.checkBox4);
                        checkBox5 = innerDialog.findViewById(R.id.checkBox5);
                        checkBoxLainnya = innerDialog.findViewById(R.id.checkBoxLainnya);
                        editTextReason = innerDialog.findViewById(R.id.editTextReason);
                        buttonSubmit = innerDialog.findViewById(R.id.buttonSubmit);
                        dialog.dismiss();
                        checkBoxLainnya.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                editTextReason.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                            }
                        });

                        buttonSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                List<String> selectedReasons = new ArrayList<>();

                                if (checkBox1.isChecked()) {
                                    selectedReasons.add(checkBox1.getText().toString());
                                }
                                if (checkBox2.isChecked()) {
                                    selectedReasons.add(checkBox2.getText().toString());
                                }
                                if (checkBox3.isChecked()) {
                                    selectedReasons.add(checkBox3.getText().toString());
                                }
                                if (checkBox4.isChecked()) {
                                    selectedReasons.add(checkBox4.getText().toString());
                                }
                                if (checkBox5.isChecked()) {
                                    selectedReasons.add(checkBox5.getText().toString());
                                }

                                if (checkBoxLainnya.isChecked()) {
                                    String otherReason = editTextReason.getText().toString().trim();
                                    if (!TextUtils.isEmpty(otherReason)) {
                                        selectedReasons.add(otherReason);
                                    }
                                }

                                reason.append(TextUtils.join(", ", selectedReasons));

                                // Lakukan operasi lain dengan variabel 'reason' di sini, seperti mengirimkan laporan

                                // Contoh: Tampilkan 'reason' di logcat
                                System.out.println("Reason: " + reason);
                                Toast.makeText(innerDialog.getContext(), reason.toString(), Toast.LENGTH_SHORT).show();
                                Call<AddReason> dashCall = apiInterface.addReasonResponse(post_id, reason.toString(), user_id);
                                if (reason.toString() == "") {
                                    final Dialog dialog = new Dialog(context);
                                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    dialog.setContentView(R.layout.pop_up_failed);
                                    Button failed = dialog.findViewById(R.id.failed);
                                    TextView massage = dialog.findViewById(R.id.massegedone);
                                    massage.setText("Masukan Laporan");
                                    failed.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialog.dismiss();
                                        }
                                    });
                                    dialog.show();
                                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSettPop;
                                    dialog.getWindow().setGravity(Gravity.CENTER);
                                }
                                dashCall.enqueue(new Callback<AddReason>() {

                                    @Override
                                    public void onResponse(Call<AddReason> call, Response<AddReason> response) {

                                        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                                            final Dialog dialog = new Dialog(context);
                                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                            dialog.setContentView(R.layout.pop_up_done);
                                            Button done = dialog.findViewById(R.id.done);
                                            TextView massage = dialog.findViewById(R.id.massegedone);
                                            massage.setText(response.body().getMessage());
                                            done.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    dialog.dismiss();
                                                }
                                            });
                                            dialog.show();
                                            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSettPop;
                                            dialog.getWindow().setGravity(Gravity.CENTER);

                                        } else {
//
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<AddReason> call, Throwable t) {

                                    }
                                });
                                innerDialog.dismiss();
                                dialog.dismiss();
                            }
                        });


                        innerDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        innerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        innerDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                        innerDialog.getWindow().setGravity(Gravity.BOTTOM);
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSettPop;
                dialog.getWindow().setGravity(Gravity.CENTER);
            }
        });

        holder.coment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.fragment_coment);
                RecyclerView recyclerView;
                LinearLayoutManager linearLayoutManager;
                recyclerView = dialog.findViewById(R.id.comentar);
                linearLayoutManager = new LinearLayoutManager(dialog.getContext());
                recyclerView.setLayoutManager(linearLayoutManager);

                String imagesUrl = url + gambarpost;
                String imageUrl = url + fotoProfil;

                SesionManager sesionManager;
                sesionManager = new SesionManager(dialog.getContext());
                ImageButton sendComment = dialog.findViewById(R.id.send_coment);


                Call<CountComment> countCommentCall = apiInterface.countUserByPostResponse(post_id);

                Toast.makeText(context, post_id, Toast.LENGTH_SHORT).show();

                GetCommentDataItem getCommentDataItem;
                Call<GetComment> dashCall = apiInterface.getCommentsByPostIdResponse(post_id);
                dashCall.enqueue(new Callback<GetComment>() {
                    @Override
                    public void onResponse(Call<GetComment> call, Response<GetComment> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                            List<GetCommentDataItem> activityDataItemList = response.body().getData();
                            Toast.makeText(context, post_id + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            AdapterComen activityAdapter = new AdapterComen(dialog.getContext(), activityDataItemList, new AdapterComen.OnItemClickListener() {

                                @Override
                                public void onItemClick(View view, int position, GetCommentDataItem item) {

                                }
                            });
                            recyclerView.setAdapter(activityAdapter);
                            activityAdapter.setCommentDataList(activityDataItemList);
                        }
                    }

                    @Override
                    public void onFailure(Call<GetComment> call, Throwable t) {
                        // Handle failure
                    }
                });

                sendComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText comments = dialog.findViewById(R.id.text_coment);
                        String comment = comments.getText().toString();
                        Call<AddComment> countCommentCall = apiInterface.addCommentResponse(post_id, comment, user_id, token);

                        Toast.makeText(context, post_id, Toast.LENGTH_SHORT).show();
                        countCommentCall.enqueue(new Callback<AddComment>() {
                            @Override
                            public void onResponse(Call<AddComment> call, Response<AddComment> response) {
                                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                                    reloadData();
                                    comments.setText(null);
                                    GetCommentDataItem getCommentDataItem;
                                    Call<GetComment> dashCall = apiInterface.getCommentsByPostIdResponse(post_id);
                                    dashCall.enqueue(new Callback<GetComment>() {
                                        @Override
                                        public void onResponse(Call<GetComment> call, Response<GetComment> response) {
                                            if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                                                List<GetCommentDataItem> activityDataItemList = response.body().getData();
                                                Toast.makeText(context, post_id + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                                AdapterComen activityAdapter = new AdapterComen(dialog.getContext(), activityDataItemList, new AdapterComen.OnItemClickListener() {

                                                    @Override
                                                    public void onItemClick(View view, int position, GetCommentDataItem item) {

                                                    }
                                                });
                                                recyclerView.setAdapter(activityAdapter);
                                                activityAdapter.setCommentDataList(activityDataItemList);
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<GetComment> call, Throwable t) {
                                            // Handle failure
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onFailure(Call<AddComment> call, Throwable t) {

                            }
                        });
                    }
                });
                Call<CountLike> countLikeCall = apiInterface.CountLikeResponse(post_id);


                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);
            }
        });


    }


    private void reloadData() {
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface OnItemClickListener {


        void onItemClick(AdapterStalking adapter, View view, int position, StalkingAcountDataItem item);
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        private final boolean mIsLiked = false;
        ImageView fotoProfil;
        TextView namaAkun;
        ImageView gambarPosting;
        TextView tanggalPost;
        ImageButton likeButton;
        TextView caption, jumlahLike, jumlahComment;
        ImageButton like, coment, reason;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            fotoProfil = itemView.findViewById(R.id.imageProfilStalk);
            namaAkun = itemView.findViewById(R.id.textProfilStalk);
            gambarPosting = itemView.findViewById(R.id.listImagePostStalk);
            tanggalPost = itemView.findViewById(R.id.text_tanggal_postStalk);
            caption = itemView.findViewById(R.id.text_captionStalk);
            like = itemView.findViewById(R.id.like_buttonStalk);
            jumlahLike = itemView.findViewById(R.id.jumlah_likeStalk);
            coment = itemView.findViewById(R.id.comment_buttonStalk);
            jumlahComment = itemView.findViewById(R.id.jumlahh_commentStalk);
            reason = itemView.findViewById(R.id.button_reasonStalk);

        }
    }
}
