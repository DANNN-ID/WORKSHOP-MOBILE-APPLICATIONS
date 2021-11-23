package com.example.belajarretrofit.Activity;

import androidx.appcompat.app.AppCompatActivity;

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

public class TambahActivity extends AppCompatActivity {

    private EditText edtnim, edtnama, edtprodi, edtfakultas;
    private Button btnsimpan;
    private String nim,nama,prodi,fakultas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        edtnim = findViewById(R.id.edt_nim);
        edtnama = findViewById(R.id.edt_nama);
        edtprodi = findViewById(R.id.edt_prodi);
        edtfakultas = findViewById(R.id.edt_fakultas);
        btnsimpan = findViewById(R.id.btn_simpan);

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nim = edtnim.getText().toString();
                nama = edtnama.getText().toString();
                prodi = edtprodi.getText().toString();
                fakultas = edtfakultas.getText().toString();

                if (nim.trim().equals("")){
                    edtnim.setError("Nim harus diisi!");
                }else if(nama.trim().equals("")){
                    edtnama.setError("Nama harus diisi!");
                }else if ((prodi.trim().equals(""))){
                    edtprodi.setError("Prodi harus diisi!");
                }else if(fakultas.trim().equals("")){
                    edtfakultas.setError("Fakultas harus diisi");
                }else {
                    createData();
                }
            }
        });
    }

    private void createData(){
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpandata = ardData.ardCreateData(nim,nama,prodi,fakultas);

        simpandata.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode : " + kode + "| Pesan : " +pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}