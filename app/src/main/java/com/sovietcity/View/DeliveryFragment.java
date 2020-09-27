package com.sovietcity.View;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sovietcity.Model.CashManager;
import com.sovietcity.Model.DeliveryManager;
import com.sovietcity.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveryFragment extends Fragment {
    private CashManager cashManager;
    private DeliveryManager deliveryManager;
    private MainActivity mainActivity;
    public DeliveryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_delivery, container, false);
        Button button = (Button)view.findViewById(R.id.buy_auto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = cashManager.wasteMoney(deliveryManager.getPrice());
                if (b) deliveryManager.increaseMaxDeliveries(1); else Toast.makeText(getActivity().getApplicationContext(),"Не хватает денег!",Toast.LENGTH_LONG).show();
            }
        });
        Button button1 = (Button)view.findViewById(R.id.open_list_auto);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.listDeliveryTransaction();
            }
        });
        TextView allText = (TextView)view.findViewById(R.id.auto_all);
        allText.setText("Всего машин/машин занято: "+ deliveryManager.getDeliveries().size()+"/"+deliveryManager.countNotFreeDeliveries());
        TextView priceText = (TextView)view.findViewById(R.id.auto_price);
        priceText.setText("Цена одной машины: "+ deliveryManager.getPrice());
        TextView  taxPrice = (TextView)view.findViewById(R.id.auto_tax);
        taxPrice.setText("Ежемесячные выплаты за содержание машин: "+ deliveryManager.getTax()*deliveryManager.getDeliveries().size());
        ImageView imageView = (ImageView)view.findViewById(R.id.delivery_icon);
        imageView.setImageBitmap(deliveryManager.getTruckIcon());
        return view;
    }

    public void setCashManager(CashManager cashManager) {
        this.cashManager = cashManager;
    }

    public void setDeliveryManager(DeliveryManager deliveryManager) {
        this.deliveryManager = deliveryManager;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
