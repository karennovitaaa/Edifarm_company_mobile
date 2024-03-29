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
import android.widget.Filter;
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
import com.aditiyagilang.edifarm_company.model.Stalking.StalkingAcount;
import com.aditiyagilang.edifarm_company.model.Stalking.StalkingAcountDataItem;
import com.aditiyagilang.edifarm_company.model.dashboard_model.DashboardDataItem;
import com.aditiyagilang.edifarm_company.model.getLike.GetLike;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFixAdapter extends RecyclerView.Adapter<DashboardFixAdapter.AdapterHolder> {

    private final OnItemClickListener listener;
    private final Context context;
    private final List<DashboardDataItem> dataList;

    private final SesionManager sesionManager;
    private final ApiInterface apiInterface;

    private final List<DashboardDataItem> filteredDataList;

    public DashboardFixAdapter(Context context, List<DashboardDataItem> dataList, OnItemClickListener listener) {
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;
        sesionManager = new SesionManager(context);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        filteredDataList = dataList;

    }

    public Filter getfilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String keyword = charSequence.toString().toLowerCase().trim();
                List<DashboardDataItem> filteredDataList = new ArrayList<>();

                if (keyword.isEmpty()) {
                    filteredDataList.addAll(dataList);
                } else {
                    for (DashboardDataItem data : dataList) {
                        if (data.getUsername().toLowerCase().contains(keyword) || data.getCaption().toLowerCase().contains(keyword)) {
                            filteredDataList.add(data);
                        }
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredDataList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredDataList.clear();
                filteredDataList.addAll((List<DashboardDataItem>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }


    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.posts_list_item, parent, false);
        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, @SuppressLint("RecyclerView") int position) {
        final DashboardDataItem item = dataList.get(position);
        String url = "http://edifarm.yoganova.my.id/";
        String textProfil = String.valueOf(item.getId());
        String fotoProfil = String.valueOf(item.getPhoto());
        String imageUrl = url + fotoProfil;

        String tanggal = String.valueOf(item.getCreatedAt());
        String gambarpost = String.valueOf(item.getImage());
        String imageUrlP = url + gambarpost;
        String textCaption = String.valueOf(item.getCaption());
        String akunname = item.getUsername();

        String tanggals = item.getCreatedAt().substring(0, 10);
        String cap = item.getCaption();
        String email = item.getEmail();
        String name = item.getName();
        String latitude = String.valueOf(item.getLatitude());
        String longitude = String.valueOf(item.getLongitude());
        String token = String.valueOf(item.getFcmToken());


        Picasso.get().load(imageUrl).resize(50, 50)
                .centerInside().into(holder.fotoProfil);
        Picasso.get().load(imageUrlP).resize(900, 600)
                .centerInside().into(holder.gambarPosting);
        holder.namaAkun.setText(akunname);
        holder.tanggalPost.setText(tanggals);
        holder.caption.setText(item.getCaption());
        String user_id = sesionManager.getUserDetail().get(SesionManager.ID);
        String User_id = String.valueOf(item.getUserId());
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
                    ImageButton likeButton = holder.like.findViewById(R.id.like_button);
                    likeButton.setBackgroundResource(R.drawable.heart7);

                    holder.like.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Call<DeleteLike> deleteLikeCall = apiInterface.DeleteLikeResponse(post_id, user_id);
                            deleteLikeCall.enqueue(new Callback<DeleteLike>() {
                                @Override
                                public void onResponse(Call<DeleteLike> call, Response<DeleteLike> response) {
                                    likeButton.setBackgroundResource(R.drawable.heart77);
                                    holder.like.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Call<Like> deleteLikeCall = apiInterface.LikeResponse(user_id, post_id, token);
                                            deleteLikeCall.enqueue(new Callback<Like>() {
                                                @Override
                                                public void onResponse(Call<Like> call, Response<Like> response) {
                                                    likeButton.setBackgroundResource(R.drawable.heart7);

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

                    ImageButton likeButton = holder.like.findViewById(R.id.like_button);
                    likeButton.setBackgroundResource(R.drawable.heart77);

                    holder.like.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Call<Like> deleteLikeCall = apiInterface.LikeResponse(user_id, post_id, token);
                            deleteLikeCall.enqueue(new Callback<Like>() {
                                @Override
                                public void onResponse(Call<Like> call, Response<Like> response) {
                                    likeButton.setBackgroundResource(R.drawable.heart7);
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
                    listener.onItemClick(DashboardFixAdapter.this, view, position, item);
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
//                ImageView foto = dialog.findViewById(R.id.listImagePostComment);
//                ImageView profil = dialog.findViewById(R.id.imageProfilComment);
                String imagesUrl = url + gambarpost;
                String imageUrl = url + fotoProfil;
//                Picasso.get().load(imageUrl).into(profil);
//                Picasso.get().load(imagesUrl).into(foto);
//                TextView caption = dialog.findViewById(R.id.comment_captionu);
                SesionManager sesionManager;
                sesionManager = new SesionManager(dialog.getContext());
                ImageButton sendComment = dialog.findViewById(R.id.send_coment);
//                ImageButton like = dialog.findViewById(R.id.like_button_comment);
//                TextView likes = dialog.findViewById(R.id.jumlah_like_comment);

                sendComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText comments = dialog.findViewById(R.id.text_coment);
                        String comment = comments.getText().toString();
                        Call<AddComment> countCommentCall = apiInterface.addCommentResponse(post_id, comment, user_id, token);


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


//                caption.setText(cap);
//                TextView username = dialog.findViewById(R.id.textProfilComment);
//                username.setText(akunname);
//
//                TextView tanggal = dialog.findViewById(R.id.text_tanggal_post_comment);
//                tanggal.setText(tanggals);
//                TextView jumlahComment = dialog.findViewById(R.id.jumlahh_comment_comment);


                GetCommentDataItem getCommentDataItem;
                Call<GetComment> dashCall = apiInterface.getCommentsByPostIdResponse(post_id);
                dashCall.enqueue(new Callback<GetComment>() {
                    @Override
                    public void onResponse(Call<GetComment> call, Response<GetComment> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                            List<GetCommentDataItem> activityDataItemList = response.body().getData();

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


                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.BOTTOM);
            }
        });
//        holder.share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Call<Share> countCommentCall = apiInterface.createShareableLinkResponse(post_id);
//
//                countCommentCall.enqueue(new Callback<Share>() {
//                    @Override
//                    public void onResponse(Call<Share> call, Response<Share> response) {
//                        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
//                            String count = String.valueOf(response.body().getData());
//                            holder.jumlahComment.setText(count);
//
//                            // Menampilkan pilihan aplikasi untuk membagikan link
//                            String shareLink = response.body().getData().getLink();
//                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
//                            shareIntent.setType("text/plain");
//                            shareIntent.putExtra(Intent.EXTRA_TEXT, shareLink);
//
//                            view.getContext().startActivity(Intent.createChooser(shareIntent, "Share via"));
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Share> call, Throwable t) {
//
//                    }
//                });
//            }
//        });


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


                Call<StalkingAcount> dashCall = apiInterface.getpoststalkResponse(User_id);
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
                        String latitude = String.valueOf(item.getLatitude());
                        String longitude = String.valueOf(item.getLongitude());

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
        return filteredDataList.size();
    }


    public interface OnItemClickListener {


        void onItemClick(DashboardFixAdapter adapter, View view, int position, DashboardDataItem item);
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        private final boolean mIsLiked = false;
        ImageButton fotoProfil, share;
        TextView namaAkun;
        ImageView gambarPosting;
        TextView tanggalPost;
        ImageButton likeButton;

        TextView caption, jumlahLike, jumlahComment;
        ImageButton like, coment, reason;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);

            fotoProfil = itemView.findViewById(R.id.imageProfilPosting);
            namaAkun = itemView.findViewById(R.id.textProfils);
            gambarPosting = itemView.findViewById(R.id.listImagePosting);
            tanggalPost = itemView.findViewById(R.id.text_tanggal_post);
            caption = itemView.findViewById(R.id.text_caption);
            like = itemView.findViewById(R.id.like_button);
            jumlahLike = itemView.findViewById(R.id.jumlah_like);
            coment = itemView.findViewById(R.id.comment_button);
            jumlahComment = itemView.findViewById(R.id.jumlahh_comment);
            reason = itemView.findViewById(R.id.button_reason);


        }
    }
}
