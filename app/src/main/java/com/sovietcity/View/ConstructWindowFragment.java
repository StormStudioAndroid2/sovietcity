package com.sovietcity.View;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sovietcity.Adapters.FactoryCard;
import com.sovietcity.Adapters.FactoryCardAdapter;
import com.sovietcity.Model.BuildManager;
import com.sovietcity.Model.BuildModel;
import com.sovietcity.Model.ConstructionFunctionComponent;
import com.sovietcity.Model.GameMap;
import com.sovietcity.Model.TerrainModelFabric;
import com.sovietcity.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConstructWindowFragment extends Fragment {
    private BuildManager buildManager;
    private TerrainModelFabric terrainModelFabric;
    private ConstructionFunctionComponent constructionFunctionComponent;
    private BuildModel buildModel;
    private MainActivity mainActivity;

    public ConstructWindowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        окно строительства. Отображает необходимые материалы и зание, которое строится
        View view = inflater.inflate(R.layout.fragment_construct_window, container, false);
        ArrayList<FactoryCard> arrayList = new ArrayList<>();
        arrayList.add(setBuildInfo());
        arrayList.add(setFirstMaterial());
        arrayList.add(setSecondMaterial());
        RecyclerView factoryView = (RecyclerView) view.findViewById(R.id.construct_recycler_view);
        factoryView.setHasFixedSize(true);
        factoryView.setLayoutManager(new LinearLayoutManager(mainActivity));
        FactoryCardAdapter factoryCardAdapter = new FactoryCardAdapter(arrayList);
        factoryView.setAdapter(factoryCardAdapter);
        Button button = (Button) view.findViewById(R.id.back_construct);
        Button button1 = (Button)view.findViewById(R.id.delete_construct);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.surfaceTransaction();
                buildManager.deleteConstruct(constructionFunctionComponent);
                for (int i = 0; i < GameMap.sizeY; i++) {
                    for (int j = 0; j < GameMap.sizeX; j++) {
                        GameMap.getGameMapCell(i, j).deleteFunctionComponent(constructionFunctionComponent, terrainModelFabric);
                    }
                }
                mainActivity.surfaceTransaction();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.surfaceTransaction();
            }
        });
        return view;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setConstructionFunctionComponent(ConstructionFunctionComponent constructionFunctionComponent) {
        this.constructionFunctionComponent = constructionFunctionComponent;
    }

    public void setBuildModel(BuildModel buildModel) {
        this.buildModel = buildModel;
    }

    public FactoryCard setBuildInfo() {
        FactoryCard factoryCard = new FactoryCard();
        factoryCard.setImage(BitmapFactory.decodeResource(getResources(), buildModel.getIcon()));
        factoryCard.setFirstText("Строится: " + buildModel.getName());
        factoryCard.setSecondText("Осталось месяцев: " + constructionFunctionComponent.getMonths());
        return factoryCard;
    }

    public FactoryCard setFirstMaterial() {
        FactoryCard factoryCard = new FactoryCard();
        if (constructionFunctionComponent.getFirstMaterial() != null) {
            factoryCard.setImage(constructionFunctionComponent.getFirstMaterial().getResource().getImage());
            factoryCard.setFirstText(constructionFunctionComponent.getFirstMaterial().getResource().getName());
            factoryCard.setSecondText("Сейчас на стройке/Нужно для строительства: " + (int) Math.round(constructionFunctionComponent.getFirstMaterial().getQuantity()) + "/" + constructionFunctionComponent.getNeedQuantity1());

        } else factoryCard.setFirstText("Материал не требуется");
        return factoryCard;
    }

    public FactoryCard setSecondMaterial() {
        FactoryCard factoryCard = new FactoryCard();
        if (constructionFunctionComponent.getSecondMaterial() != null) {
            factoryCard.setImage(constructionFunctionComponent.getSecondMaterial().getResource().getImage());
            factoryCard.setFirstText(constructionFunctionComponent.getSecondMaterial().getResource().getName());
            factoryCard.setSecondText("Сейчас на стройке/Нужно для строительства: " + (int) Math.round(constructionFunctionComponent.getSecondMaterial().getQuantity()) + "/" + constructionFunctionComponent.getNeedQuantity2());

        } else factoryCard.setFirstText("Материал не требуется");
        return factoryCard;
    }

    public void setTerrainModelFabric(TerrainModelFabric terrainModelFabric) {
        this.terrainModelFabric = terrainModelFabric;
    }

    public void setBuildManager(BuildManager buildManager) {
        this.buildManager = buildManager;
    }
}
