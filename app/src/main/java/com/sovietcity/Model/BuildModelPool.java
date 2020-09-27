package com.sovietcity.Model;

import android.content.Context;

import com.sovietcity.Presenter.World;
import com.sovietcity.R;

import java.io.Serializable;
import java.util.ArrayList;
//    все здания игры
public class BuildModelPool {

    private ArrayList<BuildModel> buildModels;
    private BuildModelCreator buildModelCreator;

    public BuildModelPool(World world, Context context, BuildManager buildManager) {
        this.buildModels = new ArrayList<>();
        this.buildModelCreator = new BuildModelCreator();
        addStalinka(world,context,buildManager);
        addXrushevka(world, context, buildManager);
        addVoyluch(world, context, buildManager);
        addConcreteBuild(world, context, buildManager);
        addIronBuild(world, context, buildManager);
        addICP(world, context, buildManager);
        addMeatPlant(world, context, buildManager);
        addMechBakery(world, context, buildManager);
        addSchool(world, context, buildManager);
        addHospital(world, context, buildManager);
        addShop(world, context, buildManager);
        addStock(world, context, buildManager);
        addTownHall(world, context, buildManager);
        addRoad(world, context, buildManager);


    }

    public BuildModel getStockBuildModel() {
        for (int i = 0; i < buildModels.size(); i++) {
            if (buildModels.get(i).getFunctionTranslator().translateStockFunctionComponent() != null) {
                return buildModels.get(i);
            }

        }
        return null;
    }
    //    методы, добавляющие здания в список
    public void addStalinka(World world, Context context,BuildManager buildManager) {
        BuildModel buildModel = buildModelCreator.createBuildModel("Сталинка","«Сталинка»  — типовой дом, сооружавшихся в СССР с конца 1930-х годов до середины 1950-х годов в стиле неоклассицизм. Существовало несколько типов домов - для партийной элиты и для рабочих. Строились из кирпича и покрывались штукатуркой, многие сталинки также имели балкон. Строительство сталинских домов резко сократилось в 1956 году, когда были приняты ориентиры на индустриальное массовое домостроение и отказ от «излишеств» в архитектуре, что привело к появлению массивов из дешёвых типовых «хрущёвок».",R.drawable.stalinka_icon,context,R.array.build_stalinka,true,1000,world.getDrawerManager());
        buildModel.setPopulationFunctionComponent(30,world.getPopulationManager());
        buildModel.setConstructionFunctionComponent(buildManager.getFunctionComponentBuilder(), 1, new Material(world.getResourceManager().getResource("ЖБИ")), 8);
        buildModel.setIsFactory(false);
        this.buildModels.add(buildModel);
    }
    public void addXrushevka(World world, Context context, BuildManager buildManager) {
        BuildModel buildModel = buildModelCreator.createBuildModel("Хрущевка", "Типовой жилой дом, появился в 1958 году, был спроектирован К.Лагутенко на основе французской пятиэтажки. 250 рублей за клетку, каждая клетка увеличивает население на 20 человек.", R.drawable.xrushevka_icon, context, R.array.build_xrushevka, true, 250, world.getDrawerManager());
        buildModel.setPopulationFunctionComponent(20, world.getPopulationManager());
        buildModel.setConstructionFunctionComponent(buildManager.getFunctionComponentBuilder(), 1, new Material(world.getResourceManager().getResource("ЖБИ")), 2);
        buildModel.setIsFactory(false);
        this.buildModels.add(buildModel);
    }

    public void addConcreteBuild(World world, Context context, BuildManager buildManager) {
        BuildModel buildModel = buildModelCreator.createBuildModel("Цементный завод", "Производит 4 цемента за месяц. Может стоять только на клетке с песком.", R.drawable.concrete_fab_icon, context, R.array.concrete_build, false, 1000, 2, 2, world.getResourceManager().getResource("Песок"), world.getDrawerManager());
        buildModel.setFactoryFunctionComponent(world.getPopulationManager().getPopulation().getPopulationDataComponent(), world.getSpecialtyManager().getSpeciality("Ресурсодобывающий комплекс"), world.getDeliveryCreator(), 1000, 4, world.getResourceManager().getResource("Цемент"));
        buildModel.setIsFactory(true);
        buildModel.setConstructionFunctionComponent(buildManager.getFunctionComponentBuilder(), 5, new Material(world.getResourceManager().getResource("ЖБИ")), 10);
        this.buildModels.add(buildModel);
    }

    public void addIronBuild(World world, Context context, BuildManager buildManager) {
        BuildModel buildModel = buildModelCreator.createBuildModel("Металлургический завод", "Производит 4 металла за месяц. Размещается только на клетках с железной рудой", R.drawable.iron_fab_icon, context, R.array.iron_build, false, 1000, 3, 3, world.getResourceManager().getResource("Железная руда"), world.getDrawerManager());
        buildModel.setFactoryFunctionComponent(world.getPopulationManager().getPopulation().getPopulationDataComponent(), world.getSpecialtyManager().getSpeciality("Ресурсодобывающий комплекс"), world.getDeliveryCreator(), 5000, 4, world.getResourceManager().getResource("Сталь"));
        buildModel.setConstructionFunctionComponent(buildManager.getFunctionComponentBuilder(), 25, new Material(world.getResourceManager().getResource("ЖБИ")), 50);
        buildModel.setIsFactory(true);
        this.buildModels.add(buildModel);
    }

    public void addRoad(World world, Context context, BuildManager buildManager) {
        BuildModel buildModel = buildModelCreator.createRoadBuildModel("Двухполосная дорога", "Двухполосная простая дорога, ускоряет перемещение товаров по клеткам, где она расположена", R.drawable.road_icon, context, R.array.build_road, 100, 3, 50, world.getDrawerManager());
        buildModel.setRoadFunctionComponent(50, 3);
        buildModel.setIsFactory(false);
        this.buildModels.add(buildModel);
    }

    public void addICP(World world, Context context, BuildManager buildManager) {
        BuildModel buildModel = buildModelCreator.createBuildModel("Завод ЖБИ", "Значительная часть существующих заводов железобетонных изделий имеет общее назначение и выпускает изделия для жилищно-гражданского строительства (панели перекрытий, стен, перегородок и покрытий).  На заводах железобетонных изделий наряду с цехами, расположенными в закрытых помещениях, могут быть открытые цехи-полигоны с самостоятельными камерами тепловой обработки.  Данный тип конвейерного завода производит 4 единицы ЖБИ в месяц из металла и песка. Стоимость строительства - 4 тыс. рублей.", R.drawable.icp_icon, context, R.array.build_icp, false, 500, 2, 2, world.getDrawerManager());
        buildModel.setFactoryFunctionComponent(world.getPopulationManager().getPopulation().getPopulationDataComponent(), world.getSpecialtyManager().getSpeciality("Промышленный комплекс"), world.getDeliveryCreator(), 1000, 12, world.getResourceManager().getResource("ЖБИ"), world.getResourceManager().getResource("Цемент"), 0, 4, world.getResourceManager().getResource("Сталь"), 0, 4);
        buildModel.setIsFactory(true);
        buildModel.setConstructionFunctionComponent(buildManager.getFunctionComponentBuilder(), 7, new Material(world.getResourceManager().getResource("ЖБИ")), 10);
        buildModels.add(buildModel);
    }

    public void addMeatPlant(World world, Context context, BuildManager buildManager) {
        BuildModel buildModel = buildModelCreator.createBuildModel("Мясокомбинат", "Производит еду для населения из мяса. Мясо можно получить только экспортом!", R.drawable.meat_plant_icon, context, R.array.build_meat_plant, false, 1000, 2, 2, world.getDrawerManager());
        buildModel.setFactoryFunctionComponent(world.getPopulationManager().getPopulation().getPopulationDataComponent(), world.getSpecialtyManager().getSpeciality("Агропромышленный комплекс"), world.getDeliveryCreator(), 300, 4, world.getResourceManager().getResource("Еда"), world.getResourceManager().getResource("Мясо"), 0, 3);
        buildModel.setIsFactory(true);
        buildModel.setConstructionFunctionComponent(buildManager.getFunctionComponentBuilder(), 9, new Material(world.getResourceManager().getResource("ЖБИ")), 15);
        this.buildModels.add(buildModel);

    }

    public void addMechBakery(World world, Context context, BuildManager buildManager) {
        BuildModel buildModel = buildModelCreator.createBuildModel("Хлебозавод", "Производит еду для населения из пшеницы. В Сибири пшеница не растет, поэтому ее можно получить только экспортом", R.drawable.mech_bakery_icon, context, R.array.build_mech_bakery, false, 1200, 2, 2, world.getDrawerManager());
        buildModel.setFactoryFunctionComponent(world.getPopulationManager().getPopulation().getPopulationDataComponent(), world.getSpecialtyManager().getSpeciality("Агропромышленный комплекс"), world.getDeliveryCreator(), 300, 4, world.getResourceManager().getResource("Еда"), world.getResourceManager().getResource("Пшеница"), 0, 3);
        this.buildModels.add(buildModel);
        buildModel.setIsFactory(true);
        buildModel.setConstructionFunctionComponent(buildManager.getFunctionComponentBuilder(), 9, new Material(world.getResourceManager().getResource("ЖБИ")), 15);
    }

    public void addTownHall(World world, Context context, BuildManager buildManager) {
        BuildModel buildModel = buildModelCreator.createBuildModel("Горком", "Здание, управляющее городом. Также выдает игроку задания от Партии. Невыполнение заданий приводит к концу игры.", R.drawable.gorkom_icon, context, R.array.build_town_hall, false, 1500, 2, 3, world.getDrawerManager());
        buildModel.setConstructionFunctionComponent(buildManager.getFunctionComponentBuilder(), 2, new Material(world.getResourceManager().getResource("ЖБИ")), 20);
        buildModel.setTaskFunctionComponent(world.getPopulationManager().getPopulation().getPopulationDataComponent());
        buildModel.setIsFactory(false);
        this.buildModels.add(buildModel);
    }

    public void addStock(World world, Context context, BuildManager buildManager) {
        BuildModel buildModel = buildModelCreator.createStockBuildModel("Склад", "Склад, позволяющий хранить ресурсы, а также продавать и покупать ресурсы у других регионов СССР", R.drawable.stock_icon, context, R.array.build_stock, false, 350, 1, 2, world.getDrawerManager());
        buildModel.setStockFunctionComponent(world.getSpecialtyManager().getSpeciality("Обслуживающий комплекс"), world.getDeliveryCreator(), world.getCashManager());
        buildModel.setConstructionFunctionComponent(buildManager.getFunctionComponentBuilder(), 2, new Material(world.getResourceManager().getResource("ЖБИ")), 5);
        this.buildModels.add(buildModel);
    }

    public void addShop(World world, Context context, BuildManager buildManager) {
        BuildModel buildModel = buildModelCreator.createBuildModel("Гастроном", "Продовольственный магазин. Продает еду населению, удовлетворяя потребности в пище ", R.drawable.shop_icon, context, R.array.build_shop, false, 1200, 1, 2, world.getDrawerManager());
        buildModel.setShopFunctionComponent(world.getSpecialtyManager().getSpeciality("Обслуживающий комплекс"), world.getPopulationManager(), new Material(world.getResourceManager().getResource("Еда")));
        buildModel.setConstructionFunctionComponent(buildManager.getFunctionComponentBuilder(), 3, new Material(world.getResourceManager().getResource("ЖБИ")), 6);
        this.buildModels.add(buildModel);

    }

    public void addVoyluch(World world, Context context, BuildManager buildManager) {
        BuildModel buildModel = buildModelCreator.createBuildModel("Башня Вулыха", "Жилой дом, названный в честь  известного советского архитектора Вулыха Ефима Петровича. Типовая серия 12-этажных каркасно-кирпичных домов Башня Вулыха относится к «брежневкам». Квартиры в большинстве домов этой серии предоставлялись очередникам предприятий и организаций, сотрудникам силовых структур.", R.drawable.voyluch_icon, context, R.array.build_voyluch, false, 375, 4, 2, world.getDrawerManager());
        buildModel.setPopulationFunctionComponent(240, world.getPopulationManager());
        buildModel.setConstructionFunctionComponent(buildManager.getFunctionComponentBuilder(), 8, new Material(world.getResourceManager().getResource("ЖБИ")), 20);
        this.buildModels.add(buildModel);

    }

    public void addSchool(World world, Context context, BuildManager buildManager) {
        BuildModel buildModel = buildModelCreator.createBuildModel("Школа", "Типовое здание средней общеобразовательной школы.Наиболее массовыми в России являются здания общеобразовательных школ типов МЮ, 65-426/1, V-76, И-1605А, И-1577А. В довоенное время разработкой типовых проектов школьных зданий занимался архитектор К. И. Джус-Даниленко.", R.drawable.school_icon, context, R.array.build_school, false, 850, 2, 3, world.getDrawerManager());
        buildModel.setSchoolFunctionComponent(world.getPopulationManager().getPopulation().getPopulationDataComponent(), world.getCashManager(), 0.05);
        buildModel.setConstructionFunctionComponent(buildManager.getFunctionComponentBuilder(), 5, new Material(world.getResourceManager().getResource("ЖБИ")), 15);
        this.buildModels.add(buildModel);
    }

    public void addHospital(World world, Context context, BuildManager buildManager) {
        BuildModel buildModel = buildModelCreator.createBuildModel("Больница", "Здание больницы смешанной системы застройки с выделением поликлиники в отдельное здание, соединенное с главным корпусом блоком лечебновспомогательных отделений, которые обслуживают и поликлинику, и стационар.  Больница в СССР — государственное лечебно-профилактическое учреждение, оказывающее населению бесплатную квалифицированную и специализированную помощь. Обеспечивает стационарную, поликлиническую, врачебную помощь на дому и проводят профилактические и противоэпидемические мероприятия в своем районе обслуживания населения.", R.drawable.hospital_icon, context, R.array.build_hospital, false, 1800, 1, 3, world.getDrawerManager());
        buildModel.setHospitalFunctionComponent(world.getPopulationManager().getPopulation().getPopulationDataComponent(), world.getCashManager(), 0.05);
        buildModel.setConstructionFunctionComponent(buildManager.getFunctionComponentBuilder(), 5, new Material(world.getResourceManager().getResource("ЖБИ")), 15);
        this.buildModels.add(buildModel);
    }

    public ArrayList<BuildModel> getBuildModels() {
        return this.buildModels;
    }

}