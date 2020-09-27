package com.sovietcity.View;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sovietcity.Adapters.FactoryCard;
import com.sovietcity.Adapters.FactoryCardAdapter;
import com.sovietcity.Model.PopulationDataComponent;
import com.sovietcity.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopulationDataFragment extends Fragment {

    private PopulationDataComponent populationDataComponent;
    private MainActivity mainActivity;

    public PopulationDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //    создание списка, в котором выводят инфу о потребностях населения, уровне жизни и т.д.
        View view = inflater.inflate(R.layout.fragment_population_data, container, false);
        ArrayList<FactoryCard> parameters = new ArrayList<>();
        parameters.add(foodCard());
        parameters.add(educationCard());
        parameters.add(medicineCard());
        RecyclerView factoryView = (RecyclerView) view.findViewById(R.id.population_view);
        factoryView.setHasFixedSize(true);
        factoryView.setLayoutManager(new LinearLayoutManager(mainActivity));
        FactoryCardAdapter factoryCardAdapter = new FactoryCardAdapter(parameters);
        factoryView.setAdapter(factoryCardAdapter);
        TextView textView = (TextView) view.findViewById(R.id.population_back);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.surfaceTransaction();
            }
        });
        TextView specialtyView = (TextView) view.findViewById(R.id.population_specialty);
        specialtyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.specialtyTransaction();
            }
        });
        return view;
    }

    private FactoryCard medicineCard() {
        FactoryCard factoryCard = new FactoryCard();
        factoryCard.setFirstText("Здравоохранение");
        factoryCard.setImage(BitmapFactory.decodeResource(getResources(),R.drawable.medicine_icon));
        if (populationDataComponent.getMedicineK() < 0.5) {
            factoryCard.setSecondText("Низкий уровень медицины");
            return factoryCard;
        }
        if (populationDataComponent.getMedicineK() < 0.9) {
            factoryCard.setSecondText("Средний уровень медицины");
            return factoryCard;
        }
        if (populationDataComponent.getMedicineK() < 1.1) {
            factoryCard.setSecondText("Высокий уровень медицины");
            return factoryCard;
        }
        factoryCard.setSecondText("Очень высокий уровень медицины");
        return factoryCard;
    }

    private FactoryCard educationCard() {
        FactoryCard factoryCard = new FactoryCard();
        factoryCard.setFirstText("Образование");
        factoryCard.setImage(BitmapFactory.decodeResource(getResources(),R.drawable.education_icon));
        if (populationDataComponent.getKnowledgeK() < 0.4) {
            factoryCard.setSecondText("Низкий уровень образования");
            return factoryCard;
        }
        if (populationDataComponent.getKnowledgeK() < 0.6) {
            factoryCard.setSecondText("Средний уровень образования");
            return factoryCard;
        }
        if (populationDataComponent.getKnowledgeK() < 0.8) {
            factoryCard.setSecondText("Высокий уровень образования");
            return factoryCard;
        }
        factoryCard.setSecondText("Очень высокий уровень образования");
        return factoryCard;

    }

    public FactoryCard foodCard() {
        FactoryCard factoryCard = new FactoryCard();
        factoryCard.setImage(BitmapFactory.decodeResource(getResources(),R.drawable.food_pop_icon));
        factoryCard.setFirstText("Обеспечение едой");
        factoryCard.setSecondText("Количество расходуемой еды в этом месяце/требуется: " + populationDataComponent.getNowFood() + "/" + populationDataComponent.getNeedFoodQuantity());
        return factoryCard;
    }

    public void setPopulationDataComponent(PopulationDataComponent populationDataComponent) {
        this.populationDataComponent = populationDataComponent;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
