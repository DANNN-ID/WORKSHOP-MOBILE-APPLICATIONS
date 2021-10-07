package com.example.tugaswma;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter {

    private int[] Icon;
    private String[] Headline;
    private String[] Subhead;
    private Context context;

    ListAdapter(@NonNull Context context, int[] icon, String[] headline, String[] subhead) {
        super(context, R.layout.custom_listview, headline);
        this.Icon = icon;
        this.Headline = headline;
        this.Subhead = subhead;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Mendapatkan inflator di konstruktor
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Inflate xml yang memberikan kami tampilan
        View view = layoutInflater.inflate(R.layout.custom_listview, null, true);
        // Dapatkan widget dengan nama id yang didefinisikan dalam xml baris
        ImageView icon = view.findViewById(R.id.imageList);
        TextView headline = view.findViewById(R.id.text_headline);
        TextView subhead = view.findViewById(R.id.text_subhead);

        // Mengatur gambar ke ImageView
        icon.setImageResource(Icon[position]);
        // Menampikan headline dan subheas
        headline.setText(Headline[position]);
        subhead.setText(Subhead[position]);
        return view;
    }

}
