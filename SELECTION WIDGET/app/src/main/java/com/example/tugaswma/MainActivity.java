package com.example.tugaswma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Membuat array untuk data spinner
    String[] negara={"Semua", "Bahasa", "Budaya", "Agama", "Musik", "Gaya Hidup"};
    int images[] = {R.drawable.ic_baseline_clear_all_24,R.drawable.ic_baseline_beach_access_24,R.drawable.ic_baseline_local_florist_24, R.drawable.ic_baseline_museum_24, R.drawable.ic_baseline_restaurant_24, R.drawable.ic_sharp_store_mall_directory_24};

    // List digunakan Untuk menampung Data negara
    private List<Country_Item> countryList;


    ListView lv;
    // Data-Data yang Akan dimasukan Pada ListView
    int[] iconList = new int[]{
            R.drawable.b1, R.drawable.b7, R.drawable.b9, R.drawable.b12, R.drawable.b8, R.drawable.b2, R.drawable.b3, R.drawable.b4
    };
    // Data data yang Akan dimasukan Pada ListView pada bagian headline
    String[] Headline = {"Sejarah Indonesia", "Sejarah Arab Saudi", "Sejarah Belanda", "Sejarah Argentina","Sejarah Amerika Serikat", "Sejarah India", "Sejarah Irlandia","Sejarah Irak"
    };
    // Data data yang Akan dimasukan Pada ListView pada bagian subhead
    String[] Subhead = {"Lorem ipsum dolor sit amet, consectetur adipisicing elit.", "Lorem ipsum dolor sit amet, consectetur adipisicing elit.", "Lorem ipsum dolor sit amet, consectetur adipisicing elit.", "Lorem ipsum dolor sit amet, consectetur adipisicing elit.","Lorem ipsum dolor sit amet, consectetur adipisicing elit.", "Lorem ipsum dolor sit amet, consectetur adipisicing elit.", "Lorem ipsum dolor sit amet, consectetur adipisicing elit.","Lorem ipsum dolor sit amet, consectetur adipisicing elit."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Mengambil contoh Spinner dan menerapkan OnItemSelectedListener di atasnya yang memberi tahu item spinner mana yang diklik
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            // Melakukan tindakan saat ItemSelected dari spinner
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "You Select Position: "+position+" "+negara[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            // Auto-generated method stub
            }
        });

        CustomSpinnerAdapter customAdapter=new CustomSpinnerAdapter(getApplicationContext(),images,negara);

        // Set ArrayAdapter data pada  yang mengikat data ke spinner
        spin.setAdapter(customAdapter);

        // Memanggil method fillcountrylist
        fillCountryList();
        AutoCompleteTextView autocompleteCountry=findViewById(R.id.autocompleteCountry);
        // Memanggil setAdapter() pada objek Autocomplete
        AutoCompleteCountryAdapter adapter = new AutoCompleteCountryAdapter(this,countryList);
        autocompleteCountry.setAdapter(adapter);

        lv = findViewById(R.id.list);
        // Memanggil setAdapter() pada objek ListView
        ListAdapter listAdapter = new ListAdapter(this, iconList, Headline, Subhead);
        lv.setAdapter(listAdapter);

        // Menambahkan Aksi Klik pada ListView Item
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Toast memberikan masukan singkat tentang operasi dalam pop-up kecil.
        // Metode makeText() menampilkan objek Toast yang diinisialisasi dengan benar.
        // Metode show() untuk menampilkan Toast
                Toast.makeText(getApplicationContext(),
                        "Ini adalah sejarah negara : " + Headline[position],
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method untuk mengisikan data coutrylist
    private void fillCountryList(){
        countryList=new ArrayList<>();
        // Menambahkan data negara ke dalamnya
        countryList.add(new Country_Item("Afghanistan",R.drawable.b6));
        countryList.add(new Country_Item("Amerika",R.drawable.b8));
        countryList.add(new Country_Item("Arab",R.drawable.b7));
        countryList.add(new Country_Item("Argentina",R.drawable.b12));

        countryList.add(new Country_Item("India",R.drawable.b2));
        countryList.add(new Country_Item("Indonesia",R.drawable.b1));
        countryList.add(new Country_Item("Irak",R.drawable.b4));
        countryList.add(new Country_Item("Iran",R.drawable.b5));
        countryList.add(new Country_Item("Irlandia",R.drawable.b3));
    }
}