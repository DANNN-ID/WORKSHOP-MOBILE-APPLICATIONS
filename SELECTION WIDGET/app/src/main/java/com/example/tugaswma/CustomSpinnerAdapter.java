package com.example.tugaswma;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class CustomSpinnerAdapter extends BaseAdapter {
    Context context;
    int images[];
    String[] kategori;
    LayoutInflater inflter;
// File java adapter
    // Public constructor
    public CustomSpinnerAdapter(Context applicationContext, int[] flags, String[] fruit) {
        this.context = applicationContext;
        this.images = flags;
        this.kategori = fruit;
        inflter = (LayoutInflater.from(applicationContext));
    }
    // Fungsi untuk mengembalikan jumlah data
    @Override
    public int getCount() {
        return images.length;// Mengembalikan panjang gambar
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.spinner_custom, null);
        // Memilih komponen yang ada di layout untuk dijadikan objek pada Java
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
        // Memampilkan gambar dan nama dari kategori
        icon.setImageResource(images[i]);
        names.setText(kategori[i]);
        return view;
    }
}
