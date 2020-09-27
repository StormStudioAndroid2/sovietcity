package com.sovietcity.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sovietcity.Model.GameMap;
import com.sovietcity.Model.ResourceManager;
import com.sovietcity.Model.ShopFunctionComponent;
import com.sovietcity.Model.TerrainModelFabric;
import com.sovietcity.R;


public class ShopFragment extends Fragment {
    private ShopFunctionComponent shopFunctionComponent;
    private MainActivity mainActivity;
    private ResourceManager resourceManager;
    private TerrainModelFabric terrainModelFabric;

    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        //    инфа о магазине
        TextView quantityText = (TextView) view.findViewById(R.id.mat_shop_quantity);
        quantityText.setText("Продается: " + shopFunctionComponent.getFood().getQuantity() + " единиц");
        ImageView imageView = (ImageView) view.findViewById(R.id.mat_shop);
        imageView.setImageBitmap(shopFunctionComponent.getFood().getResource().getImage());
        TextView workText = (TextView) view.findViewById(R.id.shop_working);
        if (shopFunctionComponent.isWorking()) {
            workText.setText("Работает");
        } else {
            workText.setText("Не работает");
        }
        Button okButton = (Button) view.findViewById(R.id.shop_ok);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.surfaceTransaction();
            }
        });
        Button deleteButton = (Button) view.findViewById(R.id.shop_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resourceManager.deleteFunctionComponent(shopFunctionComponent);
                for (int i = 0; i < GameMap.sizeY; i++) {
                    for (int j = 0; j < GameMap.sizeX; j++) {
                        GameMap.getGameMapCell(i, j).deleteFunctionComponent(shopFunctionComponent, terrainModelFabric);
                    }
                }
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void setShopFunctionComponent(ShopFunctionComponent shopFunctionComponent) {
        this.shopFunctionComponent = shopFunctionComponent;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

}
