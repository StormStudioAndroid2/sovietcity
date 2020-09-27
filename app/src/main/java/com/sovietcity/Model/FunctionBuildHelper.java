package com.sovietcity.Model;

/**
 * Created by Серёга on 24.05.2016.
 */
public class FunctionBuildHelper {
    public void setPopulationFunctionComponent(BuildModel buildModel, int blockPeople, IPopulationIncrease iPopulationIncrease) {
        final PopulationFunctionComponent populationFunctionComponent = new PopulationFunctionComponent();
        populationFunctionComponent.setBlockPeople(blockPeople);
        populationFunctionComponent.setiPopulationIncrease(iPopulationIncrease);
        buildModel.setFunctionComponent(populationFunctionComponent);

        buildModel.setFunctionTranslator(new FunctionTranslator() {

            @Override
            public FactoryFunctionComponent translateFactoryFunctionComponent() {
                return null;
            }

            @Override
            public PopulationFunctionComponent translatePopulationFunctionComponent() {
                return populationFunctionComponent;
            }

            @Override
            public RoadFunctionComponent translateRoadFunctionComponent() {
                return null;
            }

            @Override
            public StockFunctionComponent translateStockFunctionComponent() {
                return null;
            }

            @Override
            public TaskFunctionComponent translateTaskFunctionComponent() {
                return null;
            }

            @Override
            public ConstructionFunctionComponent translateConstructionFunctionComponent() {
                return null;
            }

            @Override
            public FunctionComponent translateFunctionComponent() {
                return populationFunctionComponent;
            }

            @Override
            public ShopFunctionComponent translateShopFunctionComponent() {
                return null;
            }

            @Override
            public SchoolFunctionComponent translateSchoolFunctionComponent() {
                return null;
            }

            @Override
            public HospitalFunctionComponent translateHospitalFunctionComponent() {
                return null;
            }
        });
    }

    public void setFactoryFunctionComponent(BuildModel buildModel, PopulationDataComponent populationDataComponent, Specialty specialty, DeliveryCreator deliveryCreator, int workspaces, int production, Resource resource, Resource material, int quantity, int wasteResource) {
        Material material1 = new Material();
        material1.setResource(resource);
        material1.setQuantity(0);
        Material m = new Material();
        m.setQuantity(quantity);
        m.setResource(material);
        m.setWasteQuantity(wasteResource);
        final FactoryFunctionComponent factoryFunctionComponent = new FactoryFunctionComponent();
        factoryFunctionComponent.setFactorySpecialty(specialty);
        factoryFunctionComponent.setPopulationDataComponent(populationDataComponent);
        factoryFunctionComponent.setFirstMaterial(m);
        factoryFunctionComponent.setWorkplaces(workspaces);
        factoryFunctionComponent.setProduction(production);
        factoryFunctionComponent.setManufactureMaterial(material1);
        factoryFunctionComponent.setDeliveryCreator(deliveryCreator);
        buildModel.setiChainDestination(factoryFunctionComponent);
        buildModel.setiChainSender(factoryFunctionComponent);
        buildModel.setFunctionComponent(factoryFunctionComponent);

        buildModel.setFunctionTranslator(new FunctionTranslator() {
            @Override
            public FactoryFunctionComponent translateFactoryFunctionComponent() {
                return factoryFunctionComponent;
            }

            @Override
            public PopulationFunctionComponent translatePopulationFunctionComponent() {
                return null;
            }

            @Override
            public RoadFunctionComponent translateRoadFunctionComponent() {
                return null;
            }

            @Override
            public StockFunctionComponent translateStockFunctionComponent() {
                return null;
            }

            @Override
            public TaskFunctionComponent translateTaskFunctionComponent() {
                return null;
            }

            @Override
            public ConstructionFunctionComponent translateConstructionFunctionComponent() {
                return null;
            }

            @Override
            public FunctionComponent translateFunctionComponent() {
                return factoryFunctionComponent;
            }

            @Override
            public ShopFunctionComponent translateShopFunctionComponent() {
                return null;
            }

            @Override
            public SchoolFunctionComponent translateSchoolFunctionComponent() {
                return null;
            }

            @Override
            public HospitalFunctionComponent translateHospitalFunctionComponent() {
                return null;
            }
        });
    }

    public void setFactoryFunctionComponent(BuildModel buildModel, PopulationDataComponent populationDataComponent, Specialty specialty, DeliveryCreator deliveryCreator, int workspaces, int production, Resource resource, Resource material, int quantity, int wasteResource, Resource material2, int quantity2, int wasteResource2) {
        Material material1 = new Material();
        material1.setResource(resource);
        material1.setQuantity(0);
        Material m = new Material();
        m.setQuantity(quantity);

        m.setResource(material);
        m.setWasteQuantity(wasteResource);
        Material m2 = new Material();
        m2.setQuantity(quantity2);
        m2.setResource(material2);
        m2.setWasteQuantity(wasteResource2);
        final FactoryFunctionComponent factoryFunctionComponent = new FactoryFunctionComponent();
        factoryFunctionComponent.setFactorySpecialty(specialty);
        factoryFunctionComponent.setPopulationDataComponent(populationDataComponent);
        factoryFunctionComponent.setFirstMaterial(m);
        factoryFunctionComponent.setSecondMaterial(m2);
        factoryFunctionComponent.setWorkplaces(workspaces);
        factoryFunctionComponent.setManufactureMaterial(material1);
        factoryFunctionComponent.setProduction(production);
        factoryFunctionComponent.setDeliveryCreator(deliveryCreator);
        buildModel.setiChainDestination(factoryFunctionComponent);
        buildModel.setiChainSender(factoryFunctionComponent);
        buildModel.setFunctionComponent(factoryFunctionComponent);

        buildModel.setFunctionTranslator(new FunctionTranslator() {
            @Override
            public FactoryFunctionComponent translateFactoryFunctionComponent() {
                return factoryFunctionComponent;
            }

            @Override
            public PopulationFunctionComponent translatePopulationFunctionComponent() {
                return null;
            }

            @Override
            public RoadFunctionComponent translateRoadFunctionComponent() {
                return null;
            }

            @Override
            public StockFunctionComponent translateStockFunctionComponent() {
                return null;
            }

            @Override
            public TaskFunctionComponent translateTaskFunctionComponent() {
                return null;
            }

            @Override
            public ConstructionFunctionComponent translateConstructionFunctionComponent() {
                return null;
            }

            @Override
            public FunctionComponent translateFunctionComponent() {
                return factoryFunctionComponent;
            }

            @Override
            public ShopFunctionComponent translateShopFunctionComponent() {
                return null;
            }

            @Override
            public SchoolFunctionComponent translateSchoolFunctionComponent() {
                return null;
            }

            @Override
            public HospitalFunctionComponent translateHospitalFunctionComponent() {
                return null;
            }
        });
    }

    public void setFactoryFunctionComponent(BuildModel buildModel, PopulationDataComponent populationDataComponent, Specialty specialty, DeliveryCreator deliveryCreator, int workspaces, int production, Resource resource) {
        Material material1 = new Material();
        material1.setResource(resource);
        material1.setQuantity(0);
        final FactoryFunctionComponent factoryFunctionComponent = new FactoryFunctionComponent();
        factoryFunctionComponent.setFactorySpecialty(specialty);
        factoryFunctionComponent.setManufactureMaterial(material1);
        factoryFunctionComponent.setWorkplaces(workspaces);
        factoryFunctionComponent.setProduction(production);
        factoryFunctionComponent.setDeliveryCreator(deliveryCreator);
        buildModel.setiChainDestination(factoryFunctionComponent);
        buildModel.setiChainSender(factoryFunctionComponent);
        buildModel.setFunctionComponent(factoryFunctionComponent);
        factoryFunctionComponent.setPopulationDataComponent(populationDataComponent);
        buildModel.setFunctionTranslator(new FunctionTranslator() {
            @Override
            public FactoryFunctionComponent translateFactoryFunctionComponent() {
                return factoryFunctionComponent;
            }

            @Override
            public PopulationFunctionComponent translatePopulationFunctionComponent() {
                return null;
            }

            @Override
            public RoadFunctionComponent translateRoadFunctionComponent() {
                return null;
            }

            @Override
            public StockFunctionComponent translateStockFunctionComponent() {
                return null;
            }

            @Override
            public TaskFunctionComponent translateTaskFunctionComponent() {
                return null;
            }

            @Override
            public ConstructionFunctionComponent translateConstructionFunctionComponent() {
                return null;
            }

            @Override
            public FunctionComponent translateFunctionComponent() {
                return factoryFunctionComponent;
            }

            @Override
            public ShopFunctionComponent translateShopFunctionComponent() {
                return null;
            }

            @Override
            public SchoolFunctionComponent translateSchoolFunctionComponent() {
                return null;
            }

            @Override
            public HospitalFunctionComponent translateHospitalFunctionComponent() {
                return null;
            }
        });
    }

    public void setTaskFunctionComponent(BuildModel buildModel, PopulationDataComponent populationDataComponent) {
        final TaskFunctionComponent taskFunctionComponent = new TaskFunctionComponent();
        taskFunctionComponent.setPopulationDataComponent(populationDataComponent);
        buildModel.setFunctionComponent(taskFunctionComponent);
        buildModel.setFunctionTranslator(new FunctionTranslator() {
            @Override
            public FactoryFunctionComponent translateFactoryFunctionComponent() {
                return null;
            }

            @Override
            public PopulationFunctionComponent translatePopulationFunctionComponent() {
                return null;
            }

            @Override
            public RoadFunctionComponent translateRoadFunctionComponent() {
                return null;
            }

            @Override
            public StockFunctionComponent translateStockFunctionComponent() {
                return null;
            }

            @Override
            public TaskFunctionComponent translateTaskFunctionComponent() {
                return taskFunctionComponent;
            }

            @Override
            public ConstructionFunctionComponent translateConstructionFunctionComponent() {
                return null;
            }

            @Override
            public FunctionComponent translateFunctionComponent() {
                return taskFunctionComponent;
            }

            @Override
            public ShopFunctionComponent translateShopFunctionComponent() {
                return null;
            }

            @Override
            public SchoolFunctionComponent translateSchoolFunctionComponent() {
                return null;
            }

            @Override
            public HospitalFunctionComponent translateHospitalFunctionComponent() {
                return null;
            }
        });
    }

    public ConstructionFunctionComponent cloneConstructionFunctionComponent(BuildModel buildModel) {
        if (buildModel.getConstructionFunctionComponent() != null) {
            ConstructionFunctionComponent constructionFunctionComponent = buildModel.getConstructionFunctionComponent().clone();
            return constructionFunctionComponent;
        }
        return null;
    }

    public FunctionComponent getBuildFunctionComponent(BuildModel buildModel) {

        return buildModel.getFunctionTranslator().translateFunctionComponent().clone();
    }

    public void setShopFunctionComponent(BuildModel buildModel, Specialty specialty, PopulationManager populationManager, Material material) {
        final ShopFunctionComponent shopFunctionComponent = new ShopFunctionComponent();
        shopFunctionComponent.setPopulationManager(populationManager);
        shopFunctionComponent.setSpecialty(specialty);
        shopFunctionComponent.setFood(material);
        buildModel.setFunctionTranslator(new FunctionTranslator() {
            @Override
            public FactoryFunctionComponent translateFactoryFunctionComponent() {
                return null;
            }

            @Override
            public PopulationFunctionComponent translatePopulationFunctionComponent() {
                return null;
            }

            @Override
            public RoadFunctionComponent translateRoadFunctionComponent() {
                return null;
            }

            @Override
            public StockFunctionComponent translateStockFunctionComponent() {
                return null;
            }

            @Override
            public TaskFunctionComponent translateTaskFunctionComponent() {
                return null;
            }

            @Override
            public ConstructionFunctionComponent translateConstructionFunctionComponent() {
                return null;
            }

            @Override
            public FunctionComponent translateFunctionComponent() {
                return shopFunctionComponent;
            }

            @Override
            public ShopFunctionComponent translateShopFunctionComponent() {
                return shopFunctionComponent;
            }

            @Override
            public SchoolFunctionComponent translateSchoolFunctionComponent() {
                return null;
            }

            @Override
            public HospitalFunctionComponent translateHospitalFunctionComponent() {
                return null;
            }
        });
        buildModel.setFunctionComponent(shopFunctionComponent);

    }

    public void setSchoolFunctionComponent(BuildModel buildModel, PopulationDataComponent populationDataComponent, CashManager cashManager, double factor) {
        final SchoolFunctionComponent schoolFunctionComponent = new SchoolFunctionComponent();
        schoolFunctionComponent.setPopulationDataComponent(populationDataComponent);
        schoolFunctionComponent.setFactor(factor);
        schoolFunctionComponent.setCashManager(cashManager);
        buildModel.setFunctionTranslator(new FunctionTranslator() {
            @Override
            public FactoryFunctionComponent translateFactoryFunctionComponent() {
                return null;
            }

            @Override
            public PopulationFunctionComponent translatePopulationFunctionComponent() {
                return null;
            }

            @Override
            public RoadFunctionComponent translateRoadFunctionComponent() {
                return null;
            }

            @Override
            public StockFunctionComponent translateStockFunctionComponent() {
                return null;
            }

            @Override
            public TaskFunctionComponent translateTaskFunctionComponent() {
                return null;
            }

            @Override
            public ConstructionFunctionComponent translateConstructionFunctionComponent() {
                return null;
            }

            @Override
            public FunctionComponent translateFunctionComponent() {
                return schoolFunctionComponent;
            }

            @Override
            public ShopFunctionComponent translateShopFunctionComponent() {
                return null;
            }

            @Override
            public SchoolFunctionComponent translateSchoolFunctionComponent() {
                return schoolFunctionComponent;
            }

            @Override
            public HospitalFunctionComponent translateHospitalFunctionComponent() {
                return null;
            }
        });
        buildModel.setFunctionComponent(schoolFunctionComponent);
    }

    public void setHospitalFunctionComponent(BuildModel buildModel, PopulationDataComponent populationDataComponent, CashManager cashManager, double factor) {
        final HospitalFunctionComponent hospitalFunctionComponent = new HospitalFunctionComponent();
        hospitalFunctionComponent.setPopulationDataComponent(populationDataComponent);
        hospitalFunctionComponent.setFactor(factor);
        hospitalFunctionComponent.setCashManager(cashManager);
        buildModel.setFunctionTranslator(new FunctionTranslator() {
            @Override
            public FactoryFunctionComponent translateFactoryFunctionComponent() {
                return null;
            }

            @Override
            public PopulationFunctionComponent translatePopulationFunctionComponent() {
                return null;
            }

            @Override
            public RoadFunctionComponent translateRoadFunctionComponent() {
                return null;
            }

            @Override
            public StockFunctionComponent translateStockFunctionComponent() {
                return null;
            }

            @Override
            public TaskFunctionComponent translateTaskFunctionComponent() {
                return null;
            }

            @Override
            public ConstructionFunctionComponent translateConstructionFunctionComponent() {
                return null;
            }

            @Override
            public FunctionComponent translateFunctionComponent() {
                return hospitalFunctionComponent;
            }

            @Override
            public ShopFunctionComponent translateShopFunctionComponent() {
                return null;
            }

            @Override
            public SchoolFunctionComponent translateSchoolFunctionComponent() {
                return null;
            }

            @Override
            public HospitalFunctionComponent translateHospitalFunctionComponent() {
                return hospitalFunctionComponent;
            }
        });
        buildModel.setFunctionComponent(hospitalFunctionComponent);
    }

    public void setRoadFunctionComponent(BuildModel buildModel, int moveCost, int moveDay) {
        final RoadFunctionComponent roadFunctionComponent = new RoadFunctionComponent();
        roadFunctionComponent.setMoveDay(moveDay);
        roadFunctionComponent.setMoveCost(moveCost);
        buildModel.setFunctionTranslator(new FunctionTranslator() {
            @Override
            public FactoryFunctionComponent translateFactoryFunctionComponent() {
                return null;
            }

            @Override
            public PopulationFunctionComponent translatePopulationFunctionComponent() {
                return null;
            }

            @Override
            public RoadFunctionComponent translateRoadFunctionComponent() {
                return roadFunctionComponent;
            }

            @Override
            public StockFunctionComponent translateStockFunctionComponent() {
                return null;
            }

            @Override
            public TaskFunctionComponent translateTaskFunctionComponent() {
                return null;
            }

            @Override
            public ConstructionFunctionComponent translateConstructionFunctionComponent() {
                return null;
            }

            @Override
            public FunctionComponent translateFunctionComponent() {
                return roadFunctionComponent;
            }

            @Override
            public ShopFunctionComponent translateShopFunctionComponent() {
                return null;
            }

            @Override
            public SchoolFunctionComponent translateSchoolFunctionComponent() {
                return null;
            }

            @Override
            public HospitalFunctionComponent translateHospitalFunctionComponent() {
                return null;
            }
        });
    }

    public void setRoadFunctionComponent(BuildModel buildModel, final RoadFunctionComponent r) {
        buildModel.setFunctionComponent(r);
        buildModel.setiRoadFunction(r);
        buildModel.setFunctionTranslator(new FunctionTranslator() {
            @Override
            public FactoryFunctionComponent translateFactoryFunctionComponent() {
                return null;
            }

            @Override
            public PopulationFunctionComponent translatePopulationFunctionComponent() {
                return null;
            }

            @Override
            public RoadFunctionComponent translateRoadFunctionComponent() {
                return r;
            }

            @Override
            public StockFunctionComponent translateStockFunctionComponent() {
                return null;
            }

            @Override
            public TaskFunctionComponent translateTaskFunctionComponent() {
                return null;
            }

            @Override
            public ConstructionFunctionComponent translateConstructionFunctionComponent() {
                return null;
            }

            @Override
            public FunctionComponent translateFunctionComponent() {
                return r;
            }

            @Override
            public ShopFunctionComponent translateShopFunctionComponent() {
                return null;
            }

            @Override
            public SchoolFunctionComponent translateSchoolFunctionComponent() {
                return null;
            }

            @Override
            public HospitalFunctionComponent translateHospitalFunctionComponent() {
                return null;
            }
        });
    }

    public void setStockFunctionComponent(BuildModel buildModel, Specialty specialty, DeliveryCreator deliveryCreator, CashManager cashManager) {
        final StockFunctionComponent stockFunctionComponent = new StockFunctionComponent();
        stockFunctionComponent.setWorkplaces(100);
        stockFunctionComponent.setSpecialty(specialty);
        stockFunctionComponent.setDeliveryCreator(deliveryCreator);
        stockFunctionComponent.setCashManager(cashManager);
        buildModel.setFunctionComponent(stockFunctionComponent);
        buildModel.setiChainDestination(stockFunctionComponent);
        buildModel.setiChainSender(stockFunctionComponent);
        buildModel.setFunctionTranslator(new FunctionTranslator() {
            @Override
            public FactoryFunctionComponent translateFactoryFunctionComponent() {
                return null;
            }

            @Override
            public PopulationFunctionComponent translatePopulationFunctionComponent() {
                return null;
            }

            @Override
            public RoadFunctionComponent translateRoadFunctionComponent() {
                return null;
            }

            @Override
            public StockFunctionComponent translateStockFunctionComponent() {
                return stockFunctionComponent;
            }

            @Override
            public TaskFunctionComponent translateTaskFunctionComponent() {
                return null;
            }

            @Override
            public ConstructionFunctionComponent translateConstructionFunctionComponent() {
                return null;
            }

            @Override
            public FunctionComponent translateFunctionComponent() {
                return stockFunctionComponent;
            }

            @Override
            public ShopFunctionComponent translateShopFunctionComponent() {
                return null;
            }

            @Override
            public SchoolFunctionComponent translateSchoolFunctionComponent() {
                return null;
            }

            @Override
            public HospitalFunctionComponent translateHospitalFunctionComponent() {
                return null;
            }
        });
    }

}
