package com.aditiyagilang.edifarm_company.design;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.model.ActivityPost.ActivityPostDataItem;
import com.aditiyagilang.edifarm_company.model.AddRating.AddRating;
import com.aditiyagilang.edifarm_company.model.CekUserRating.CekUserRating;
import com.aditiyagilang.edifarm_company.model.Download.Download;
import com.aditiyagilang.edifarm_company.model.GetRate.GetRating;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterActivityPost extends RecyclerView.Adapter<AdapterActivityPost.AdapterHolder> {
    private final Context context;
    private final List<ActivityPostDataItem> dataList;
    private final AdapterActivityPost.OnItemClickListener listener;
    ActivityPostDataItem historyDataItemm;
    SesionManager sesionManager;
    ApiInterface apiInterface;

    public AdapterActivityPost(Context context, List<ActivityPostDataItem> dataList, AdapterActivityPost.OnItemClickListener listener) {
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;
        sesionManager = new SesionManager(context);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

    }


    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_activities_post, parent, false);
        return new AdapterHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AdapterActivityPost.AdapterHolder holder, @SuppressLint("RecyclerView") int position) {

        final String user_id = sesionManager.getUserDetail().get(SesionManager.ID);
        final String id = String.valueOf(dataList.get(position).getId());
        final ActivityPostDataItem item = dataList.get(position);
        String post_activity_id = id;
        String plant = item.getPlantName();
        String start = item.getCreatedAt().substring(0, 10);
        String finish = item.getUpdatedAt().substring(0, 10);
        String nama = item.getUsername();

        String periode = start + " hingga " + finish;
        String session_id = String.valueOf(item.getSessionId());

        String pdf = item.getPdfFile();
        holder.planNamePost.setText(plant);
        holder.tanggal_periode.setText(periode);
        holder.username.setText(nama);

        Call<GetRating> countCommentCall = apiInterface.calculateAverageRatingResponse(post_activity_id);


        countCommentCall.enqueue(new Callback<GetRating>() {
            @Override
            public void onResponse(Call<GetRating> call, Response<GetRating> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    holder.vrgrate.setText(response.body().getData().toString());
                    int i = 1;

                    if (response.body().getData() instanceof Double) {
                        double rating = (double) response.body().getData();
                        if (rating == 1.0) {
                            holder.star1.setImageResource(R.drawable.star_fild);
                            holder.star2.setImageResource(R.drawable.star_outline);
                            holder.star3.setImageResource(R.drawable.star_outline);
                            holder.star4.setImageResource(R.drawable.star_outline);
                            holder.star5.setImageResource(R.drawable.star_outline);
                        } else if (rating > 1 && rating < 2) {
                            holder.star1.setImageResource(R.drawable.star_fild);
                            holder.star2.setImageResource(R.drawable.icon_star_notfull);
                            holder.star3.setImageResource(R.drawable.star_outline);
                            holder.star4.setImageResource(R.drawable.star_outline);
                            holder.star5.setImageResource(R.drawable.star_outline);
                        } else if (rating == 2) {
                            holder.star1.setImageResource(R.drawable.star_fild);
                            holder.star2.setImageResource(R.drawable.star_fild);
                            holder.star3.setImageResource(R.drawable.star_outline);
                            holder.star4.setImageResource(R.drawable.star_outline);
                            holder.star5.setImageResource(R.drawable.star_outline);
                        } else if (rating > 2 && rating < 3) {
                            holder.star1.setImageResource(R.drawable.star_fild);
                            holder.star2.setImageResource(R.drawable.star_fild);
                            holder.star3.setImageResource(R.drawable.icon_star_notfull);
                            holder.star4.setImageResource(R.drawable.star_outline);
                            holder.star5.setImageResource(R.drawable.star_outline);
                        } else if (rating == 3) {
                            holder.star1.setImageResource(R.drawable.star_fild);
                            holder.star2.setImageResource(R.drawable.star_fild);
                            holder.star3.setImageResource(R.drawable.star_fild);
                            holder.star4.setImageResource(R.drawable.star_outline);
                            holder.star5.setImageResource(R.drawable.star_outline);
                        } else if (rating > 3 && rating < 4) {
                            holder.star1.setImageResource(R.drawable.star_fild);
                            holder.star2.setImageResource(R.drawable.star_fild);
                            holder.star3.setImageResource(R.drawable.star_fild);
                            holder.star4.setImageResource(R.drawable.icon_star_notfull);
                            holder.star5.setImageResource(R.drawable.star_outline);
                        } else if (rating == 4) {
                            holder.star1.setImageResource(R.drawable.star_fild);
                            holder.star2.setImageResource(R.drawable.star_fild);
                            holder.star3.setImageResource(R.drawable.star_fild);
                            holder.star4.setImageResource(R.drawable.star_fild);
                            holder.star5.setImageResource(R.drawable.star_outline);

                        } else if (rating > 4 && rating < 5) {
                            holder.star1.setImageResource(R.drawable.star_fild);
                            holder.star2.setImageResource(R.drawable.star_fild);
                            holder.star3.setImageResource(R.drawable.star_fild);
                            holder.star4.setImageResource(R.drawable.star_fild);
                            holder.star5.setImageResource(R.drawable.icon_star_notfull);
                        } else if (rating < 1) {
                            holder.star1.setImageResource(R.drawable.star_outline);
                            holder.star2.setImageResource(R.drawable.star_outline);
                            holder.star3.setImageResource(R.drawable.star_outline);
                            holder.star4.setImageResource(R.drawable.star_outline);
                            holder.star5.setImageResource(R.drawable.star_outline);
                        } else if (rating == 5) {
                            holder.star1.setImageResource(R.drawable.star_fild);
                            holder.star2.setImageResource(R.drawable.star_fild);
                            holder.star3.setImageResource(R.drawable.star_fild);
                            holder.star4.setImageResource(R.drawable.star_fild);
                            holder.star5.setImageResource(R.drawable.star_fild);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetRating> call, Throwable t) {

            }
        });

        Call<CekUserRating> cekUserRatingCall = apiInterface.checkUserRatingResponse(user_id, post_activity_id);


        cekUserRatingCall.enqueue(new Callback<CekUserRating>() {
            @Override
            public void onResponse(Call<CekUserRating> call, Response<CekUserRating> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    if (response.body().getData().isHasRated()) {
                        holder.rates.setVisibility(View.GONE);
//                        holder.rates.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                final Dialog dialog = new Dialog(context);
//                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                                dialog.setContentView(R.layout.pop_up_done);
//                                Button done = dialog.findViewById(R.id.done);
//                                TextView massage = dialog.findViewById(R.id.massegedone);
//                                massage.setText(response.body().getMessage());
//                                done.setOnClickListener(new View.OnClickListener() {
//
//                                    @Override
//                                    public void onClick(View view) {
//                                        dialog.dismiss();
//                                    }
//                                });
//                                dialog.show();
//                                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSettPop;
//                                dialog.getWindow().setGravity(Gravity.CENTER);
//                            }
//                        });
//                        reloadData();

                    } else {
                        holder.rates.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final Dialog dialog = new Dialog(context);
                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog.setContentView(R.layout.rate_star);
                                ImageButton star11 = dialog.findViewById(R.id.star11);
                                ImageButton star22 = dialog.findViewById(R.id.star22);
                                ImageButton star33 = dialog.findViewById(R.id.star33);
                                ImageButton star44 = dialog.findViewById(R.id.star44);
                                ImageButton star55 = dialog.findViewById(R.id.star55);
                                Button rating = dialog.findViewById(R.id.rating);

                                final int[] rate = {0};

                                star11.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        rate[0] = 1;
                                        star11.setImageResource(R.drawable.star_fild);
                                        star22.setImageResource(R.drawable.star_outline);
                                        star33.setImageResource(R.drawable.star_outline);
                                        star44.setImageResource(R.drawable.star_outline);
                                        star55.setImageResource(R.drawable.star_outline);
                                    }
                                });

                                star22.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        rate[0] = 2;
                                        star11.setImageResource(R.drawable.star_fild);
                                        star22.setImageResource(R.drawable.star_fild);
                                        star33.setImageResource(R.drawable.star_outline);
                                        star44.setImageResource(R.drawable.star_outline);
                                        star55.setImageResource(R.drawable.star_outline);
                                    }
                                });

                                star33.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        rate[0] = 3;
                                        star11.setImageResource(R.drawable.star_fild);
                                        star22.setImageResource(R.drawable.star_fild);
                                        star33.setImageResource(R.drawable.star_fild);
                                        star44.setImageResource(R.drawable.star_outline);
                                        star55.setImageResource(R.drawable.star_outline);
                                    }
                                });

                                star44.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        rate[0] = 4;
                                        star11.setImageResource(R.drawable.star_fild);
                                        star22.setImageResource(R.drawable.star_fild);
                                        star33.setImageResource(R.drawable.star_fild);
                                        star44.setImageResource(R.drawable.star_fild);
                                        star55.setImageResource(R.drawable.star_outline);
                                    }
                                });

                                star55.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        rate[0] = 5;
                                        star11.setImageResource(R.drawable.star_fild);
                                        star22.setImageResource(R.drawable.star_fild);
                                        star33.setImageResource(R.drawable.star_fild);
                                        star44.setImageResource(R.drawable.star_fild);
                                        star55.setImageResource(R.drawable.star_fild);
                                    }
                                });

                                rating.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (rate[0] == 0) {
                                            Toast.makeText(context, "Pilih rating terlebih dahulu", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Call<AddRating> countCommentCall = apiInterface.addRatingResponse(user_id, post_activity_id, rate[0]);

                                            countCommentCall.enqueue(new Callback<AddRating>() {
                                                @Override
                                                public void onResponse(Call<AddRating> call, Response<AddRating> response) {
                                                    if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                                                        reloadData();
                                                        final Dialog dialog = new Dialog(context);
                                                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                                        dialog.setContentView(R.layout.pop_up_done);
                                                        Button done = dialog.findViewById(R.id.done);
                                                        TextView massage = dialog.findViewById(R.id.massegedone);
                                                        massage.setText(response.body().getMessage() + " " + rate[0]);
                                                        done.setOnClickListener(new View.OnClickListener() {

                                                            @Override
                                                            public void onClick(View view) {
                                                                dialog.dismiss();
                                                                reloadData();
                                                            }
                                                        });
                                                        dialog.show();
                                                        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                                        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSettPop;
                                                        dialog.getWindow().setGravity(Gravity.CENTER);
                                                    }
                                                    dialog.dismiss();
                                                }

                                                @Override
                                                public void onFailure(Call<AddRating> call, Throwable t) {
                                                    // Handle failure
                                                }
                                            });
                                        }
                                    }
                                });

                                dialog.show();
                                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationSettPop;
                                dialog.getWindow().setGravity(Gravity.CENTER);
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<CekUserRating> call, Throwable t) {

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(AdapterActivityPost.this, view, position, item);
                }
            }
        });
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onDownloadsClick(AdapterActivityPost.this, view, position, item);
                }
                Call<Download> downloadCall = apiInterface.downloadPDFResponse(id);
                Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
                downloadCall.enqueue(new Callback<Download>() {
                    @Override
                    public void onResponse(Call<Download> call, Response<Download> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                            // Mendapatkan nama file dari URL
                            String pdfUrl = response.body().getData().getDownloadUrl();

                            // Download file PDF dan simpan dengan nama sesuai pdf_file
                            DownloadManager downloadManager = (DownloadManager) view.getContext().getSystemService(Context.DOWNLOAD_SERVICE);

                            Uri uri = Uri.parse(pdfUrl);
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, pdf);
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            downloadManager.enqueue(request);
                        }
                    }

                    @Override
                    public void onFailure(Call<Download> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });

        holder.open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.web_view_pdf);

                WebView webView = dialog.findViewById(R.id.web);

                Call<Download> downloadCall = apiInterface.downloadPDFResponse(id);
                Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
                downloadCall.enqueue(new Callback<Download>() {
                    @Override
                    public void onResponse(Call<Download> call, Response<Download> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                            // Mendapatkan URL file PDF
                            String pdfUrl = response.body().getData().getDownloadUrl();

                            // Mengatur WebViewClient
                            webView.setWebViewClient(new WebViewClient() {
                                @Override
                                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                    // Menggunakan WebView bawaan untuk membuka URL PDF
                                    view.loadUrl(url);
                                    return true;
                                }
                            });

                            // Mengaktifkan fitur zoom dan JavaScript pada WebView
                            webView.getSettings().setSupportZoom(true);
                            webView.getSettings().setJavaScriptEnabled(true);

                            // Load URL PDF menggunakan Google Docs Viewer
                            webView.loadUrl(pdfUrl);
                        }
                    }

                    @Override
                    public void onFailure(Call<Download> call, Throwable t) {
                        t.printStackTrace();
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
        void onItemClick(AdapterActivityPost adapter, View view, int position, ActivityPostDataItem item);

        void onDownloadsClick(AdapterActivityPost adapter, View view, int position, ActivityPostDataItem item);
    }


    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView planNamePost, tanggal_periode, username, vrgrate;
        ImageView star1, star2, star3, star4, star5;
        Button rates;

        ImageButton download, open;
        SesionManager sesionManager;
        ApiInterface apiInterface;


        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            planNamePost = itemView.findViewById(R.id.plant_name_post);
            download = itemView.findViewById(R.id.downloadsu);
            sesionManager = new SesionManager(itemView.getContext());
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            tanggal_periode = itemView.findViewById(R.id.tanggal_periode);
            username = itemView.findViewById(R.id.nama_user);
            vrgrate = itemView.findViewById(R.id.vrgrate);
            open = itemView.findViewById(R.id.open);
            rates = itemView.findViewById(R.id.rate);
            star1 = itemView.findViewById(R.id.star1);
            star2 = itemView.findViewById(R.id.star2);
            star3 = itemView.findViewById(R.id.star3);
            star4 = itemView.findViewById(R.id.star4);
            star5 = itemView.findViewById(R.id.star5);

            download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {

                        // URL untuk mengunduh file PDF


                    }
                }
            });
        }

    }


}
