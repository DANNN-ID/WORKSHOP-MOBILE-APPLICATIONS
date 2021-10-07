package com.example.tugaswma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteCountryAdapter extends ArrayAdapter<Country_Item> {
    private List<Country_Item> countryListFull;
    public AutoCompleteCountryAdapter(@NonNull Context context, @NonNull List<Country_Item> countryList) {
        super(context,0,countryList);
        countryListFull=new ArrayList<>(countryList);
    }
    @NonNull
    @Override
    public Filter getFilter(){
        return countryFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Inflate tata letak untuk setiap baris daftar
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(
                R.layout.country_autocomplete_row,parent, false
            );

        }
        // Dapatkan TextView untuk nama item dan gambar item
        TextView textViewName=convertView.findViewById(R.id.text_view_name);
        ImageView imageViewFlag=convertView.findViewById(R.id.image_view_name);
        Country_Item country_item=getItem(position);

        if (country_item != null){
            // Mengatur teks untuk nama item dan gambar item dari objek item saat ini
            textViewName.setText(country_item.getCountryName());
            imageViewFlag.setImageResource(country_item.getFlagImage());
        }
        // Mengembalikan tampilan untuk baris saat ini
        return convertView;
    }

    private Filter countryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results= new FilterResults();
            List<Country_Item> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                suggestions.addAll(countryListFull);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Country_Item item : countryListFull){
                    if (item.getCountryName().toLowerCase().contains(filterPattern)){
                        suggestions.add(item);
                    }
                }
            }
            results.values=suggestions;
            results.count=suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }
        public CharSequence converResultToString(Object resultValue){
            // Mengembalikan nilai item country dengan mengambilkan country name
            return ((Country_Item) resultValue).getCountryName();
        }
    };
}
