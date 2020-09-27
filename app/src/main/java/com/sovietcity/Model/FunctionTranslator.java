package com.sovietcity.Model;

import java.io.Serializable;

/**
 * Created by Серёга on 16.04.2016.
 */
public interface FunctionTranslator extends Serializable {
    //перевод из класса functionComponent в один из его наследников
    FactoryFunctionComponent translateFactoryFunctionComponent();

    PopulationFunctionComponent translatePopulationFunctionComponent();

    RoadFunctionComponent translateRoadFunctionComponent();

    StockFunctionComponent translateStockFunctionComponent();

    TaskFunctionComponent translateTaskFunctionComponent();

    ConstructionFunctionComponent translateConstructionFunctionComponent();

    FunctionComponent translateFunctionComponent();

    ShopFunctionComponent translateShopFunctionComponent();

    SchoolFunctionComponent translateSchoolFunctionComponent();
    HospitalFunctionComponent translateHospitalFunctionComponent();
}
