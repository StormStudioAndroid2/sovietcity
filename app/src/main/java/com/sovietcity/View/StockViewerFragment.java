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
import com.sovietcity.Model.Material;
import com.sovietcity.Model.ResourceManager;
import com.sovietcity.Model.StockFunctionComponent;
import com.sovietcity.Model.TerrainModelFabric;
import com.sovietcity.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StockViewerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StockViewerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StockViewerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private StockFunctionComponent stockFunctionComponent;
    private MainActivity mainActivity;
    private ResourceManager resourceManager;
    private TerrainModelFabric terrainModelFabric;

    public StockViewerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StockViewerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StockViewerFragment newInstance(String param1, String param2) {
        StockViewerFragment fragment = new StockViewerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stock_viewer, container, false);
        this.showMaterial(stockFunctionComponent.getMaterial(), view);
        Button button = (Button) view.findViewById(R.id.stock_ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.surfaceTransaction();
            }
        });
        Button deleteButton = (Button) view.findViewById(R.id.stock_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resourceManager.deleteFunctionComponent(stockFunctionComponent);
                for (int i = 0; i < GameMap.sizeY; i++) {
                    for (int j = 0; j < GameMap.sizeX; j++) {
                        GameMap.getGameMapCell(i, j).deleteFunctionComponent(stockFunctionComponent, terrainModelFabric);
                    }
                }
                mainActivity.surfaceTransaction();
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

    private void showMaterial(Material material, View view) {
        //    обзор склада. Задаются View
        ImageView imageView = (ImageView) view.findViewById(R.id.mat_stock);
        imageView.setImageBitmap(material.getResource().getImage());
        TextView textPrice = (TextView) view.findViewById(R.id.mat_stock_price);
        textPrice.setText("Цена ресурса: " + material.getResource().getPrice());
        TextView textQuantity = (TextView) view.findViewById(R.id.mat_stock_quantity);
        textQuantity.setText("Количество на складе: " + Math.round(material.getQuantity()));
        TextView textAction = (TextView) view.findViewById(R.id.mat_stock_action);
        TextView textWork = (TextView) view.findViewById(R.id.stock_working);
        if (stockFunctionComponent.isWorking()) {
            textWork.setText("Работает");
        } else {
            textWork.setText("Не работает");
        }
        if (this.stockFunctionComponent.isImport()) {
            textAction.setText("Импортируется");
        } else {
            textAction.setText("Экспортируется");
        }
    }

    public void setStockFunctionComponent(StockFunctionComponent stockFunctionComponent) {
        this.stockFunctionComponent = stockFunctionComponent;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public void setTerrainModelFabric(TerrainModelFabric terrainModelFabric) {
        this.terrainModelFabric = terrainModelFabric;
    }
}
