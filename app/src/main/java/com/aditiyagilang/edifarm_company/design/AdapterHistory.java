package com.aditiyagilang.edifarm_company.design;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditiyagilang.edifarm_company.R;
import com.aditiyagilang.edifarm_company.SesionManager;
import com.aditiyagilang.edifarm_company.api.ApiClient;
import com.aditiyagilang.edifarm_company.api.ApiInterface;
import com.aditiyagilang.edifarm_company.model.Download.Download;
import com.aditiyagilang.edifarm_company.model.History.HistoryDataItem;

import java.io.File;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.AdapterHolder> {
    private final Context context;
    private final List<HistoryDataItem> dataList;
    HistoryDataItem historyDataItemm;
    SesionManager sesionManager;
    ApiInterface apiInterface;
    private AdapterHistory.OnItemClickListener listener;
    private AdapterHistory.OnItemClickListener editClickListener;

    public AdapterHistory(Context context, List<HistoryDataItem> dataList, AdapterHistory.OnItemClickListener listener) {
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;
        sesionManager = new SesionManager(context);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

    }

    public void setOnItemClickListener(AdapterHistory.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnEditClickListener(AdapterHistory.OnItemClickListener listener) {
        this.editClickListener = listener;
    }

    @NonNull
    @Override
    public AdapterHistory.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_history, parent, false);
        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHistory.AdapterHolder holder, @SuppressLint("RecyclerView") int position) {

        final String User_ID = sesionManager.getUserDetail().get(SesionManager.ID);
        final String id = String.valueOf(dataList.get(position).getId());
        final HistoryDataItem item = dataList.get(position);
        String plant = item.getPlantName();
        String start = item.getSessionCreatedAt();
        String finish = item.getDocumentationCreatedAt();
        String period = start + " hingga " + finish;
        String pdf = item.getPdfFile();
        holder.planName.setText(plant);
        holder.tanggal_perion.setText(period);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(AdapterHistory.this, view, position, item);
                }
            }
        });
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onDownloadsClick(AdapterHistory.this, view, position, item);
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
                            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "report.pdf");
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

        holder.upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "report.pdf");
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


// Method untuk mengirim permintaan HTTP untuk mengunduh file PDF berdasarkan reportId dan pdfFile


// Method untuk mengirim permintaan HTTP untuk mengunduh file PDF berdasarkan reportId

    }

    private void downloadFile(String downloadUrl, String fileName) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);

        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        if (downloadManager != null) {
            downloadManager.enqueue(request);
        } else {
            // Tangani jika DownloadManager tidak tersedia di perangkat
            Toast.makeText(context, "Failed to download file", Toast.LENGTH_SHORT).show();
        }
    }


    private void downloadReport(String reportId, String pdf) {
        // Buat URL endpoint untuk mengunduh laporan
        String baseUrl = "https://82fa-103-160-182-11.ngrok-free.app/api/reports/";
        String downloadUrl = baseUrl + reportId;

        // Buat permintaan pengunduhan menggunakan DownloadManager
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle("PDF Download");
        request.setDescription("Downloading PDF file...");

        // Tentukan direktori penyimpanan untuk file PDF yang diunduh
        String downloadDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(downloadDirectory, pdf);
        request.setDestinationUri(Uri.fromFile(file));

        // Dapatkan sistem DownloadManager dan kirim permintaan pengunduhan
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        if (downloadManager != null) {
            downloadManager.enqueue(request);
        } else {
            // Tangani jika DownloadManager tidak tersedia di perangkat
            Toast.makeText(context, "Failed to download PDF", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(AdapterHistory adapter, View view, int position, HistoryDataItem item);

        void onDownloadsClick(AdapterHistory adapter, View view, int position, HistoryDataItem item);
    }


    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView planName, tanggal_perion;
        ImageButton download, upload;

        SesionManager sesionManager;
        ApiInterface apiInterface;


        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            planName = itemView.findViewById(R.id.plant_name_history);
            download = itemView.findViewById(R.id.downloads);
            sesionManager = new SesionManager(itemView.getContext());
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            tanggal_perion = itemView.findViewById(R.id.tanggal_period);
            upload = itemView.findViewById(R.id.uploads);

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
