package com.example.belajarretrofit.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.belajarretrofit.API.APIRequestData;
import com.example.belajarretrofit.API.RetroServer;
import com.example.belajarretrofit.Model.ResponseModel;
import com.example.belajarretrofit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahActivity extends AppCompatActivity {

    private String xNim, xNama, xProdi, xFakultas;
    private EditText edtNama, edtNim, edtProdi, edtFakultas;
    private Button btnSimpan;
    private String nim, nama, prodi, fakultas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        Intent terima = getIntent();
        xNim = terima.getStringExtra("xNim");
        xNama = terima.getStringExtra("xNama");
        xProdi = terima.getStringExtra("xProdi");
        xFakultas = terima.getStringExtra("xFakultas");

        edtNama = findViewById(R.id.edt_nama);
        edtNim = findViewById(R.id.edt_nim);
        edtProdi = findViewById(R.id.edt_prodi);
        edtFakultas = findViewById(R.id.edt_fakultas);
        btnSimpan = findViewById(R.id.btn_simpan);

        edtNim.setText(xNim);
        edtNama.setText(xNama);
        edtProdi.setText(xProdi);
        edtFakultas.setText(xFakultas);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nim = edtNim.getText().toString();
                nama = edtNama.getText().toString();
                prodi = edtProdi.getText().toString();
                fakultas = edtFakultas.getText().toString();
                updateData();
            }
        });

    }

    private void updateData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> ubahdata = ardData.ardUpdateData(nim,nama,prodi,fakultas);

        ubahdata.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(UbahActivity.this, "Kode : " + kode + "| Pesan : " +pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}