package com.sovietcity.View;


import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sovietcity.Adapters.FactoryCard;
import com.sovietcity.Adapters.FactoryCardAdapter;
import com.sovietcity.Model.Delivery;
import com.sovietcity.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveryListFragment extends Fragment {
    private Bitmap image;
    private ArrayList<Delivery> deliveries;
    public DeliveryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_delivery_list, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_delivery);
        recyclerView.setHasFixedSize(true);
        //    инфа о специальностях в списке
        ArrayList<FactoryCard> factoryCards = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        for (int i = 0; i<deliveries.size();i++) {
            FactoryCard factoryCard = new FactoryCard();
            factoryCard.setFirstText("Грузовик № "+(i+1));
            if (deliveries.get(i).isFree()) {
                factoryCard.setSecondText("Свободен");
            } else {
                factoryCard.setSecondText("Везет материал: " + deliveries.get(i).getResource().getName()+", в количестве "+ Math.round(deliveries.get(i).getQuantity()));
            }
            factoryCard.setImage(image);
            factoryCards.add(factoryCard);
        }
        FactoryCardAdapter factoryCardAdapter = new FactoryCardAdapter(factoryCards);
        recyclerView.setAdapter(factoryCardAdapter);
        return view;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setDeliveries(ArrayList<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

}
