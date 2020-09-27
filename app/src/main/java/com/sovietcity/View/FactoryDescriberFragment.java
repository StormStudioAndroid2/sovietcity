package com.sovietcity.View;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sovietcity.Adapters.FactoryCard;
import com.sovietcity.Adapters.FactoryCardAdapter;
import com.sovietcity.Model.BuildModel;
import com.sovietcity.Model.FactoryFunctionComponent;
import com.sovietcity.Model.GameMap;
import com.sovietcity.Model.Material;
import com.sovietcity.Model.ResourceManager;
import com.sovietcity.Model.TerrainModelFabric;
import com.sovietcity.R;

import java.util.ArrayList;

public class FactoryDescriberFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private BuildModel buildModel;
    private FactoryFunctionComponent factoryFunctionComponent;
    private MainActivity mainActivity;
    private ResourceManager resourceManager;
    private TerrainModelFabric terrainModelFabric;

    public FactoryDescriberFragment() {
        // Required empty public constructor
    }

    public void setBuildModel(BuildModel buildModel) {
        this.buildModel = buildModel;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FactoryDescriber.
     */
    // TODO: Rename and change types and number of parameters
    public static FactoryDescriberFragment newInstance(String param1, String param2) {
        FactoryDescriberFragment fragment = new FactoryDescriberFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fab_describe, container, false);
        ArrayList<FactoryCard> arrayList = new ArrayList<>();
//описание фабрики. Создание списка со всеми материалами
        arrayList.add(setDesignFactory(view, buildModel));
        arrayList.add(setManufactureMaterial(view, factoryFunctionComponent.getManufactureMaterial()));
        arrayList.add(setDesignMaterial(view, factoryFunctionComponent.getFirstMaterial()));
        arrayList.add(setDesignMaterial(view, factoryFunctionComponent.getSecondMaterial()));
        RecyclerView factoryView = (RecyclerView) view.findViewById(R.id.describe_recycler_view);
        factoryView.setHasFixedSize(true);
        factoryView.setLayoutManager(new LinearLayoutManager(mainActivity));
        FactoryCardAdapter factoryCardAdapter = new FactoryCardAdapter(arrayList);
        factoryView.setAdapter(factoryCardAdapter);
        TextView textView = (TextView) view.findViewById(R.id.fac_name);
        textView.setText(buildModel.getName());
        Button button = (Button) view.findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.surfaceTransaction();
            }
        });
        Button button1 = (Button) view.findViewById(R.id.delete_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resourceManager.deleteFunctionComponent(factoryFunctionComponent);
                for (int i = 0; i < GameMap.sizeY; i++) {
                    for (int j = 0; j < GameMap.sizeX; j++) {
                        GameMap.getGameMapCell(i, j).deleteFunctionComponent(factoryFunctionComponent, terrainModelFabric);
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

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
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


    private FactoryCard setDesignMaterial(View view, Material material) {
        FactoryCard factoryCard = new FactoryCard();
        if (material != null) {
            factoryCard.setFirstText("Нужен ресурс: " + material.getResource().getName());
            factoryCard.setImage(material.getResource().getImage());
            factoryCard.setSecondText("Кол-во на заводе: " + Math.round(material.getQuantity()) + "");
        } else {
            factoryCard.setFirstText("Материал не требуется");

        }
        return factoryCard;
    }

    private FactoryCard setManufactureMaterial(View view, Material material) {
        FactoryCard factoryCard = new FactoryCard();
        factoryCard.setFirstText("Производится ресурс: " + material.getResource().getName());
        factoryCard.setSecondText("Кол-во на заводе: " + Math.round(material.getQuantity()) + "");
        factoryCard.setImage(material.getResource().getImage());
        return factoryCard;
    }

    private FactoryCard setDesignFactory(View view, BuildModel buildModel) {
        FactoryCard factoryCard = new FactoryCard();
        factoryCard.setFirstText(buildModel.getName());
        factoryCard.setSecondText("Рабочие места: " + factoryFunctionComponent.getBusyWorkplaces() + "/" + factoryFunctionComponent.getWorkplaces());
        factoryCard.setImage(BitmapFactory.decodeResource(getResources(), buildModel.getIcon()));
        return factoryCard;
    }

    public void setFactoryFunctionComponent(FactoryFunctionComponent factoryFunctionComponent) {
        this.factoryFunctionComponent = factoryFunctionComponent;
    }

    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public void setTerrainModelFabric(TerrainModelFabric terrainModelFabric) {
        this.terrainModelFabric = terrainModelFabric;
    }
}
