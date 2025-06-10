package com.example.communihelp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OfferProduct extends AppCompatActivity {

    RecyclerView recyclerViewOffers;
    OfferProductAdapter adapter;
    List<OfferProuductModule> offerList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offerproduct);

        recyclerViewOffers = findViewById(R.id.recyclerViewOffers);
        recyclerViewOffers.setLayoutManager(new LinearLayoutManager(this));

        // Dummy data
        offerList = new ArrayList<>();
        offerList.add(new OfferProuductModule("Shalini", "Clothes for kids", "Boys and girls",5));
        offerList.add(new OfferProuductModule("Tejesh", "Electrical Appliances", "Earphones",5));
        offerList.add(new OfferProuductModule("Chakri", "Food Materials", "Rice bags",5));

        adapter = new OfferProductAdapter(offerList);
        recyclerViewOffers.setAdapter(adapter);
    }
}
