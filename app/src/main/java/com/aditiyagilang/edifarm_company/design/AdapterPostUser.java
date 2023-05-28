package com.aditiyagilang.edifarm_company.design;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
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
import com.aditiyagilang.edifarm_company.model.CountComment.CountComment;
import com.aditiyagilang.edifarm_company.model.CountLike.CountLike;
import com.aditiyagilang.edifarm_company.model.DeleteLike.DeleteLike;
import com.aditiyagilang.edifarm_company.model.DeletePost.DeletePost;
import com.aditiyagilang.edifarm_company.model.GetComment.GetComment;
import com.aditiyagilang.edifarm_company.model.GetComment.GetCommentDataItem;
import com.aditiyagilang.edifarm_company.model.GetPostUser.GetPostUserDataItem;
import com.aditiyagilang.edifarm_company.model.Like.Like;
import com.aditiyagilang.edifarm_company.model.getLike.GetLike;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterPostUser extends RecyclerView.Adapter<AdapterPostUser.AdapterHolder> {

    private final Context context;
    private final List<GetPostUserDataItem> dataList;
    private final SesionManager sesionManager;
    private final ApiInterface apiInterface;
    private final OnItemClickListener listener;

    public AdapterPostUser(Context context, List<GetPostUserDataItem> dataList, AdapterPostUser.OnItemClickListener listener) {
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;
        sesionManager = new SesionManager(context);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }


    @NonNull
    @Override
    public AdapterPostUser.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_post_user, parent, false);
        return new AdapterPostUser.AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPostUser.AdapterHolder holder, @SuppressLint("RecyclerView") int position) {
        final GetPostUserDataItem item = dataList.get(position);
        String textProfil = String.valueOf(item.getUserId());
        String fotoProfil = sesionManager.getUserDetail().get(SesionManager.PHOTO);
        String imageUrl = "http://edifarm.yoganova.my.id/" + fotoProfil;
        String url = "http://edifarm.yoganova.my.id/";
        String token = item.getFcmToken().toString();
        String tanggal = String.valueOf(item.getCreatedAt());
        String gambarpost = String.valueOf(item.getImage());
        String imageUrlP = "https://82fa-103-160-182-11.ngrok-free.app/" + gambarpost;
        String textCaption = String.valueOf(item.getCaption());
        String akunname = sesionManager.getUserDetail().get(SesionManager.USERNAME);
        String tanggals = item.getCreatedAt();
        String cap = item.getCaption();
        String user_id = sesionManager.getUserDetail().get(SesionManager.ID);
        String post_id = String.valueOf(dataList.get(position).getId());


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
                    holder.jumlahcomment.setText(count);
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
                    holder.julmahlikes.setText(count);
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
                    ImageButton likeButton = holder.like.findViewById(R.id.loves);
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
                                                holder.julmahlikes.setText(count);
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
                    ImageButton likeButton = holder.like.findViewById(R.id.loves);
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
                                                holder.julmahlikes.setText(count);
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
                    listener.onItemClick(AdapterPostUser.this, view, position, item);
                }
            }
        });


        holder.comen.setOnClickListener(new View.OnClickListener() {
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

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.pop_up_reason);
                Button reason = dialog.findViewById(R.id.reason);
                Button cancel = dialog.findViewById(R.id.cancel_reason);
                TextView urgent = dialog.findViewById(R.id.urgent);
                urgent.setText("Yakin DiHapus Dek \n Nanti Nyesel Kalu ilang ;(");

                reason.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        reloadData();
                        Call<DeletePost> countLikeCall = apiInterface.deletePostByPostIdkResponse(post_id);
                        countLikeCall.enqueue(new Callback<DeletePost>() {
                            @Override
                            public void onResponse(Call<DeletePost> call, Response<DeletePost> response) {
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
                                            reloadData();
                                            notifyDataSetChanged();
                                        }
                                    });

                                    dialog.show();
                                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSettPop;
                                    dialog.getWindow().setGravity(Gravity.CENTER);
                                }
                            }

                            @Override
                            public void onFailure(Call<DeletePost> call, Throwable t) {

                            }
                        });
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
    }

    private void reloadData() {
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface OnItemClickListener {


        void onItemClick(AdapterPostUser adapter, View view, int position, GetPostUserDataItem item);
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        private final boolean mIsLiked = false;
        ImageView fotoProfil;
        TextView namaAkun, julmahlikes;
        ImageView gambarPosting;
        ImageButton comen, delete;
        TextView tanggalPost;
        TextView caption, jumlahcomment;
        ImageButton like;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            jumlahcomment = itemView.findViewById(R.id.jumlahh_commentus);
            delete = itemView.findViewById(R.id.button_deleted);
            comen = itemView.findViewById(R.id.comment_buttonus);
            fotoProfil = itemView.findViewById(R.id.imageProfilPostus);
            namaAkun = itemView.findViewById(R.id.textProfilUserss);
            gambarPosting = itemView.findViewById(R.id.listImagePostUserss);
            tanggalPost = itemView.findViewById(R.id.text_tanggal_postuu);
            caption = itemView.findViewById(R.id.text_captionuu);
            like = itemView.findViewById(R.id.loves);
            julmahlikes = itemView.findViewById(R.id.jumlah_likes);
        }
    }
}
