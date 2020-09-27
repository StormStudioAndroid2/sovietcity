package com.sovietcity.Presenter;

import android.content.Context;

import com.sovietcity.Model.AuthorisationWorker;
import com.sovietcity.Model.BuildManager;
import com.sovietcity.Model.CashManager;
import com.sovietcity.Model.Delivery;
import com.sovietcity.Model.DeliveryCreator;
import com.sovietcity.Model.DeliveryManager;
import com.sovietcity.Model.EventManager;
import com.sovietcity.Model.GameMap;
import com.sovietcity.Model.LessonMissionLoader;
import com.sovietcity.Model.MapManager;
import com.sovietcity.Model.PopulationEvent;
import com.sovietcity.Model.PopulationEventListener;
import com.sovietcity.Model.PopulationManager;
import com.sovietcity.Model.ResourceManager;
import com.sovietcity.Model.Save;
import com.sovietcity.Model.SaveDelivery;
import com.sovietcity.Model.SpecialtyManager;
import com.sovietcity.Model.State;
import com.sovietcity.Model.TaskManager;
import com.sovietcity.Model.TerrainModel;
import com.sovietcity.Model.ThreadManager;
import com.sovietcity.Model.TimeManager;


import java.util.ArrayList;
import java.util.Calendar;

//фабрика, содержащая все менеджеры
public class World {

    private PopulationManager populationManager;
    private ResourceManager resourceManager;
    private TerrainModel grassTerrain;
    private TerrainModel treeTerrain;
    private TerrainModel waterTerrain;
    private TerrainModel buildTerrain;
    private BuildManager buildManager;
    private TimeManager timeManager;
    private CashManager cashManager;
    private ThreadManager threadManager;
    private boolean isLose;
    private boolean isWin;
    private DeliveryManager deliveryManager;
    private transient EventManager eventManager;
    private DeliveryCreator deliveryCreator;
    private MapManager mapManager;
    private TaskManager taskManager;
    private SpecialtyManager specialtyManager;
    private DrawerManager drawerManager;
    private LessonMissionLoader lessonMissionLoader;
    private Save currentSave;
    private AuthorisationWorker authorisationWorker;

    public World(Context context) {
        //объявление и инициализация менеджеров
        this.specialtyManager = new SpecialtyManager(context);
        this.timeManager = new TimeManager(1, 0, 1959);
        this.lessonMissionLoader = new LessonMissionLoader();
        this.lessonMissionLoader.loadResources(context);
        this.drawerManager = new DrawerManager(context);
        this.drawerManager.startSynchronizeCamera();
        this.resourceManager = new ResourceManager(context, drawerManager);
        this.mapManager = new MapManager(this.resourceManager, drawerManager, context);
        this.cashManager = new CashManager(100000);

        this.deliveryManager = new DeliveryManager();
        deliveryManager.setCashManager(cashManager);

        this.deliveryCreator = new DeliveryCreator(this.deliveryManager);
        deliveryManager.setDeliveryCreator(deliveryCreator);
        this.deliveryManager.setTimeManager(this.timeManager);

        this.populationManager = new PopulationManager(specialtyManager, 100);
        this.populationManager.addPopulationEventListener(new PopulationEventListener() {
            @Override
            public void changePopulation(PopulationEvent e, boolean isIncrease) {
                int pop = e.getChangePopulation();
                if (isIncrease) {
                    populationManager.increaseMaxPopulation(pop);
                } else populationManager.decreaseMaxPopulation(pop);
            }
        });
        this.buildManager = new BuildManager(this, context);
        buildManager.setMapManager(this.mapManager);
        buildManager.setObserver(drawerManager);

        buildManager.startBuildStock(this, context);
        specialtyManager.distributeWorkers(populationManager.getPopulation().getAdultGroup().getPopulation());
        this.eventManager = new EventManager(this, context);
        this.taskManager = new TaskManager(context, timeManager.getDate().get(Calendar.YEAR));
        this.populationManager.setTaskListener(taskManager.getTaskListener());
        this.resourceManager.setTaskListener(taskManager.getTaskListener());
        this.buildManager.setTaskListener(taskManager.getTaskListener());
        taskManager.setCashManager(cashManager);
        this.threadManager = new ThreadManager(this, true);
        this.deliveryManager.increaseMaxDeliveries(10);
        this.deliveryManager.setTruckIcon(context, drawerManager);
        threadManager.setState(State.RUN_ONLY_SURFACE);
    }

    public World(Context context, Save save) {
        this.currentSave = save;

        this.drawerManager = new DrawerManager(context, save.getDrawer());
        this.lessonMissionLoader = new LessonMissionLoader();
        this.lessonMissionLoader.loadResources(context);
        this.specialtyManager = new SpecialtyManager(save.getSpecialtyWorker().loadSpecialtyWorker(context));
        this.timeManager = new TimeManager(save.getSaveTime().getDay(), save.getSaveTime().getMonth(), save.getSaveTime().getYear());
        this.populationManager = new PopulationManager(specialtyManager, save.getPopulation());
        this.populationManager.addPopulationEventListener(new PopulationEventListener() {
            @Override
            public void changePopulation(PopulationEvent e, boolean isIncrease) {
                int pop = e.getChangePopulation();
                if (isIncrease) {
                    populationManager.increaseMaxPopulation(pop);
                } else populationManager.decreaseMaxPopulation(pop);
            }
        });
        this.drawerManager = new DrawerManager(context, save.getDrawer());
        this.drawerManager.setCamera(save.getCamera());
        this.drawerManager.startSynchronizeCamera();
        this.mapManager = new MapManager(drawerManager, context);
        this.resourceManager = new ResourceManager(context, drawerManager);

        this.deliveryManager = new DeliveryManager();
        ArrayList<SaveDelivery> saveDeliveries = save.getDeliveries();
        ArrayList<Delivery> deliveries = new ArrayList<>();
        if (saveDeliveries.size() > 0) {
            for (int i = 0; i < saveDeliveries.size(); i++) {
                deliveries.add(new Delivery(this, saveDeliveries.get(i)));
            }
        }
        this.deliveryManager.setDeliveries(deliveries);
        this.deliveryCreator = new DeliveryCreator(deliveryManager);
        deliveryManager.setDeliveryCreator(deliveryCreator);
        deliveryManager.setTimeManager(timeManager);
        this.eventManager = new EventManager(this, context);
        this.cashManager = new CashManager(0);
        this.cashManager.setCash(save.getCash());
        deliveryManager.setCashManager(cashManager);
        this.taskManager = new TaskManager(context);
        this.taskManager.deserialize(save.getTaskGetter(), save.getTaskWorker(), context,save.getSaveTime().getYear());
        taskManager.setCashManager(cashManager);
        this.buildManager = new BuildManager(this, context);
        buildManager.setMapManager(this.mapManager);
        buildManager.setObserver(drawerManager);
        buildManager.getFunctionComponentBuilder().setId(save.getFunctionId());
        this.populationManager.setTaskListener(taskManager.getTaskListener());
        this.populationManager.getPopulation().setSpecialtyManager(specialtyManager);
        this.resourceManager.setTaskListener(taskManager.getTaskListener());
        this.buildManager.setTaskListener(taskManager.getTaskListener());
        GameMap.loadMap(this, save.getGameMap());
        this.threadManager = new ThreadManager(this, false);
        this.threadManager.setState(State.RUN_ALL);
        this.deliveryManager.setTruckIcon(context, drawerManager);

    }

    public LessonMissionLoader getLessonMissionLoader() {
        return lessonMissionLoader;
    }

    public EventManager getEventManager() {
        return eventManager;
    }


    public DeliveryCreator getDeliveryCreator() {
        return deliveryCreator;
    }

    public DeliveryManager getDeliveryManager() {
        return deliveryManager;
    }

    public ThreadManager getThreadManager() {
        return this.threadManager;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }

    public CashManager getCashManager() {
        return this.cashManager;
    }

    public TimeManager getTimeManager() {
        return this.timeManager;
    }

    public TerrainModel getBuildTerrain() {
        return this.buildTerrain;
    }

    public PopulationManager getPopulationManager() {
        return this.populationManager;
    }

    public ResourceManager getResourceManager() {
        return this.resourceManager;
    }


    public BuildManager getBuildManager() {
        return this.buildManager;
    }

    public SpecialtyManager getSpecialtyManager() {
        return specialtyManager;
    }


    public DrawerManager getDrawerManager() {
        return drawerManager;
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    public boolean isLose() {
        return isLose;
    }

    public boolean isWin() {
        return isWin;
    }

    public void setLose(boolean lose) {
        isLose = lose;
    }

    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public void setAuthorisationWorker(AuthorisationWorker authorisationWorker) {
        this.authorisationWorker = authorisationWorker;
    }

    public AuthorisationWorker getAuthorisationWorker() {
        return authorisationWorker;
    }


}

