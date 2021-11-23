package com.example.belajarretrofit.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.belajarretrofit.API.APIRequestData;
import com.example.belajarretrofit.API.RetroServer;
import com.example.belajarretrofit.Activity.MainActivity;
import com.example.belajarretrofit.Activity.UbahActivity;
import com.example.belajarretrofit.Model.DataModel;
import com.example.belajarretrofit.Model.ResponseModel;
import com.example.belajarretrofit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listMahasiswa;
    private List<DataModel> listData;
    private String nim;

    public AdapterData(Context ctx, List<DataModel> listMahasiswa){
        this.ctx = ctx;
        this.listMahasiswa = listMahasiswa;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listMahasiswa.get(position);
        holder.tvNim.setText(dm.getNim());
        holder.tvNama.setText(dm.getNama());
        holder.tvProdi.setText(dm.getProdi());
        holder.tvFakultas.setText(dm.getFakultas());
    }

    @Override
    public int getItemCount() {
        return listMahasiswa.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvNim, tvNama, tvProdi, tvFakultas;
        public HolderData(@NonNull View itemView){
            super(itemView);
            tvNim = itemView.findViewById(R.id.tv_nim);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvProdi = itemView.findViewById(R.id.tv_prodi);
            tvFakultas = itemView.findViewById(R.id.tv_fakultas);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                    dialogPesan.setMessage("Pilih operasi yang akan dilakukan");
                    dialogPesan.setCancelable(true);
                    nim = tvNim.getText().toString();
                    dialogPesan.setTitle("Perhatian");
                    dialogPesan.setIcon(R.mipmap.ic_launcher);

                    dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            deleteData();
                            dialog.dismiss();
                            Handler handler = new android.os.Handler();
                            handler.postDelayed(new Runnable(){
                                @Override
                                public void run() {
                                    ((MainActivity) ctx).viewData();
                                }
                            },250);

                        }
                    });

                    dialogPesan.setNegativeButton("ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getData();
                            dialog.dismiss();
                        }
                    });

                    dialogPesan.show();
                    return false;
                }
            });
        }

        private void deleteData(){
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> hapusData = ardData.ardDeleteData(nim);

            hapusData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    Toast.makeText(ctx, "Kode : " + kode+"| Pesan : " +pesan, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void getData(){
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> ambilData = ardData.ardgetData(nim);

            ambilData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    listData = response.body().getData();

                    String varNim = listData.get(0).getNim();
                    String varNama = listData.get(0).getNama();
                    String varProdi = listData.get(0).getProdi();
                    String varFakultas = listData.get(0).getFakultas();

                    Intent intent = new Intent(ctx, UbahActivity.class);
                    intent.putExtra("xNim", varNim);
                    intent.putExtra("xNama",varNama);
                    intent.putExtra("xProdi", varProdi);
                    intent.putExtra("xFakultas", varFakultas);
                    ctx.startActivity(intent);
//                    Toast.makeText(ctx, "Kode : " + kode+"| Pesan : " +pesan, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
