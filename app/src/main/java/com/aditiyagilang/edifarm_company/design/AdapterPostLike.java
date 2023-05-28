package com.aditiyagilang.edifarm_company.design;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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
import com.aditiyagilang.edifarm_company.model.GetPostLike.GetPostLikeDataItem;
import com.aditiyagilang.edifarm_company.model.Like.Like;
import com.aditiyagilang.edifarm_company.model.Stalking.StalkingAcount;
import com.aditiyagilang.edifarm_company.model.Stalking.StalkingAcountDataItem;
import com.aditiyagilang.edifarm_company.model.getLike.GetLike;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterPostLike extends RecyclerView.Adapter<AdapterPostLike.AdapterHolder> {
    private final OnItemClickListener listener;
    private final Context context;
    private final List<GetPostLikeDataItem> dataList;
    private final SesionManager sesionManager;
    private final ApiInterface apiInterface;

    public AdapterPostLike(Context context, List<GetPostLikeDataItem> dataList, OnItemClickListener listener) {
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;
        sesionManager = new SesionManager(context);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }


    @NonNull
    @Override
    public AdapterPostLike.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_posting_user, parent, false);
        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPostLike.AdapterHolder holder, @SuppressLint("RecyclerView") int position) {
        final GetPostLikeDataItem item = dataList.get(position);
        String textProfil = String.valueOf(item.getUserId());
        String fotoProfil = item.getPhoto();
        String imageUrl = "http://edifarm.yoganova.my.id/" + fotoProfil;
        String token = item.getFcmToken().toString();
        String tanggal = String.valueOf(item.getCreatedAt());
        String gambarpost = String.valueOf(item.getImage());
        String imageUrlP = "http://edifarm.yoganova.my.id/" + gambarpost;
        String textCaption = String.valueOf(item.getCaption());
        String akunname = item.getUsername();
        String tanggals = item.getCreatedAt().substring(0, 10);
        String cap = item.getCaption();
        String user_id = sesionManager.getUserDetail().get(SesionManager.ID);
        String post_id = String.valueOf(dataList.get(position).getId());
        String url = "http://edifarm.yoganova.my.id/";
        String email = item.getEmail();
        String name = item.getName();
        String latitude = item.getLatitude();
        String longitude = item.getLongitude();

        Picasso.get().load(imageUrl).into(holder.fotoProfil);
        Picasso.get().load(imageUrlP).into(holder.gambarPosting);
        holder.namaAkun.setText(akunname);
        holder.tanggalPost.setText(tanggals);
        holder.caption.setText(item.getCaption());

        Call<CountComment> countCommentCall = apiInterface.countUserByPostResponse(post_id);


        countCommentCall.enqueue(new Callback<CountComment>() {
            @Override
            public void onResponse(Call<CountComment> call, Response<CountComment> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    String count = String.valueOf(response.body().getData());
                    holder.jumlahcomen.setText(count);
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
                    ImageButton likeButton = holder.like.findViewById(R.id.like_buttonu);
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
                    ImageButton likeButton = holder.like.findViewById(R.id.like_buttonu);
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
                ImageView foto = dialog.findViewById(R.id.listImagePostComment);
                ImageView profil = dialog.findViewById(R.id.imageProfilComment);
                String imagesUrl = url + gambarpost;
                String imageUrl = url + fotoProfil;
                Picasso.get().load(imageUrl).into(profil);
                Picasso.get().load(imagesUrl).into(foto);
                TextView caption = dialog.findViewById(R.id.comment_captionu);
                SesionManager sesionManager;
                sesionManager = new SesionManager(dialog.getContext());
                ImageButton sendComment = dialog.findViewById(R.id.send_coment);
                ImageButton like = dialog.findViewById(R.id.like_button_comment);
                TextView likes = dialog.findViewById(R.id.jumlah_like_comment);


                caption.setText(cap);
                TextView username = dialog.findViewById(R.id.textProfilComment);
                username.setText(akunname);

                TextView tanggal = dialog.findViewById(R.id.text_tanggal_post_comment);
                tanggal.setText(tanggals);
                TextView jumlahComment = dialog.findViewById(R.id.jumlahh_commentu);
                Call<CountComment> countCommentCall = apiInterface.countUserByPostResponse(post_id);

                Toast.makeText(context, post_id, Toast.LENGTH_SHORT).show();
                countCommentCall.enqueue(new Callback<CountComment>() {
                    @Override
                    public void onResponse(Call<CountComment> call, Response<CountComment> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                            String count = String.valueOf(response.body().getData());
                            jumlahComment.setText(count);
                        }
                    }

                    @Override
                    public void onFailure(Call<CountComment> call, Throwable t) {

                    }
                });

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


                countLikeCall.enqueue(new Callback<CountLike>() {
                    @Override
                    public void onResponse(Call<CountLike> call, Response<CountLike> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                            String count = String.valueOf(response.body().getData());
                            likes.setText(count);
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
                            like.setBackgroundResource(R.drawable.like_red);

                            like.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Call<DeleteLike> deleteLikeCall = apiInterface.DeleteLikeResponse(post_id, user_id);
                                    deleteLikeCall.enqueue(new Callback<DeleteLike>() {
                                        @Override
                                        public void onResponse(Call<DeleteLike> call, Response<DeleteLike> response) {
                                            like.setBackgroundResource(R.drawable.like_white);
                                            like.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Call<Like> deleteLikeCall = apiInterface.LikeResponse(user_id, post_id, token);
                                                    deleteLikeCall.enqueue(new Callback<Like>() {
                                                        @Override
                                                        public void onResponse(Call<Like> call, Response<Like> response) {
                                                            like.setBackgroundResource(R.drawable.like_red);

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
                                                        likes.setText(count);
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

                            like.setBackgroundResource(R.drawable.like_white);
                            like.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Call<Like> deleteLikeCall = apiInterface.LikeResponse(user_id, post_id, token);
                                    deleteLikeCall.enqueue(new Callback<Like>() {
                                        @Override
                                        public void onResponse(Call<Like> call, Response<Like> response) {
                                            like.setBackgroundResource(R.drawable.like_red);
                                            Call<CountLike> countLikeCall = apiInterface.CountLikeResponse(post_id);
                                            countLikeCall.enqueue(new Callback<CountLike>() {
                                                @Override
                                                public void onResponse(Call<CountLike> call, Response<CountLike> response) {
                                                    if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                                                        String count = String.valueOf(response.body().getData());
                                                        likes.setText(count);
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

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(AdapterPostLike.this, view, position, item);
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

        holder.fotoProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.fragment_stalking_mantan);
                RecyclerView recyclerView;
                LinearLayoutManager linearLayoutManager;
                recyclerView = dialog.findViewById(R.id.stalking);
                linearLayoutManager = new LinearLayoutManager(dialog.getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                ImageView foto = dialog.findViewById(R.id.imageprofileStalkes);
                String imageUrl = url + fotoProfil;
                String lat = latitude;
                String longi = longitude;
                ImageButton cancel = dialog.findViewById(R.id.cancel_stalking);
                ImageButton loc = dialog.findViewById(R.id.location_stalking);

                Picasso.get().load(imageUrl).into(foto);
                TextView user = dialog.findViewById(R.id.usernameStalk);
                TextView emails = dialog.findViewById(R.id.emailStalk);
                TextView names = dialog.findViewById(R.id.nameStalk);
                String nama = item.getName();
                String emailk = item.getEmail();
                String namek = item.getName();
                user.setText(akunname);
                emails.setText(email);
                names.setText(name);


                final StalkingAcountDataItem[] stalkingAcountDataItem = new StalkingAcountDataItem[1];


                Call<StalkingAcount> dashCall = apiInterface.getpoststalkResponse(user_id);
                dashCall.enqueue(new Callback<StalkingAcount>() {
                    @Override
                    public void onResponse(Call<StalkingAcount> call, Response<StalkingAcount> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {


                            List<StalkingAcountDataItem> stalkingAcountDataItemsList = response.body().getData();
                            AdapterStalking adapterStalking = new AdapterStalking(dialog.getContext(), stalkingAcountDataItemsList, new AdapterStalking.OnItemClickListener() {


                                @Override
                                public void onItemClick(AdapterStalking adapter, View view, int position, StalkingAcountDataItem item) {

                                }

                            });
                            recyclerView.setAdapter(adapterStalking);
                            stalkingAcountDataItem[0] = stalkingAcountDataItemsList.get(0);


                        }
                    }

                    @Override
                    public void onFailure(Call<StalkingAcount> call, Throwable t) {

                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                loc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String latitude = item.getLatitude();
                        String longitude = item.getLongitude();

                        // Buat URI dengan data longitude dan latitude
                        String uri = "geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude;

                        // Buat Intent untuk membuka aplikasi peta atau aplikasi navigasi
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        mapIntent.setPackage("com.google.android.apps.maps");
                        Toast.makeText(context, uri, Toast.LENGTH_SHORT).show();
                        context.startActivity(mapIntent);

                    }
                });


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


        void onItemClick(AdapterPostLike adapterPostLike, View view, int position, GetPostLikeDataItem item);
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        private final boolean mIsLiked = false;
        ImageButton fotoProfil, coment, reason;
        TextView namaAkun;
        ImageView gambarPosting;
        TextView tanggalPost;
        TextView caption, jumlahLike, jumlahcomen;
        ImageButton like;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            reason = itemView.findViewById(R.id.button_repostu);
            fotoProfil = itemView.findViewById(R.id.imageProfilPostu);
            namaAkun = itemView.findViewById(R.id.textProfilUsers);
            gambarPosting = itemView.findViewById(R.id.listImagePostUser);
            tanggalPost = itemView.findViewById(R.id.text_tanggal_postu);
            caption = itemView.findViewById(R.id.text_captionu);
            coment = itemView.findViewById(R.id.comment_buttonu);
            jumlahcomen = itemView.findViewById(R.id.jumlahh_commentu);
            like = itemView.findViewById(R.id.like_buttonu);
            jumlahLike = itemView.findViewById(R.id.jumlah_likeu);
        }
    }
}
