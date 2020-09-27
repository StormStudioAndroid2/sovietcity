package com.sovietcity.Model;

import com.google.android.gms.games.Game;
import com.sovietcity.Presenter.Camera;
import com.sovietcity.Presenter.Drawer;
import com.sovietcity.Presenter.World;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Серёга on 22.05.2016.
 */
public class Save implements Serializable {
    private SaveCell[][] gameMap;
    private Cash cash;
    private TaskWorker taskWorker;
    private TaskGetter taskGetter;
    private SaveTime saveTime;
    private SpecialtyWorker specialtyWorker;
    private Drawer drawer;
    private Population population;
    private Camera camera;
    private static final long serialVersionUID = 5139506746591478068L;
    private ArrayList<SaveDelivery> deliveries;
    private int functionId;

    public byte[] serialize() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
        oos.writeObject(this);

        oos.flush();
        oos.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static Save deserialize(byte[] array) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(array);
        ObjectInputStream is = new ObjectInputStream(in);
        return (Save) is.readObject();
    }

    public SaveCell[][] getGameMap() {
        return gameMap;
    }

    private void setGameMap(Cell[][] gameMap) {
        this.gameMap = new SaveCell[GameMap.sizeY][GameMap.sizeX];
        for (int i = 0; i < GameMap.sizeY; i++) {
            for (int j = 0; j < GameMap.sizeX; j++) {
                this.gameMap[i][j] = new SaveCell(GameMap.getGameMapCell(i, j));

            }
        }
    }

    public void setValues(World world) {
        setGameMap(GameMap.getGameMap());
        this.taskGetter = world.getTaskManager().getTaskGetter();
        this.taskWorker = world.getTaskManager().getTaskWorker();
        this.cash = world.getCashManager().getSaveCash();
        this.drawer = world.getDrawerManager().getDrawer();
        this.specialtyWorker = world.getSpecialtyManager().getSpecialityWorker();
        this.population = world.getPopulationManager().getPopulation();
        ArrayList<Delivery> deliveries = world.getDeliveryManager().getDeliveries();
        this.deliveries = new ArrayList<>();
        if (deliveries.size() > 0) {
            for (int i = 0; i < deliveries.size(); i++) {
                this.deliveries.add(new SaveDelivery(deliveries.get(i)));
            }
        }
        this.saveTime = new SaveTime();
        this.saveTime.setDay(world.getTimeManager().getDate().get(Calendar.DAY_OF_MONTH));
        this.saveTime.setMonth(world.getTimeManager().getDate().get(Calendar.MONTH));
        this.saveTime.setYear(world.getTimeManager().getDate().get(Calendar.YEAR));
        this.camera = world.getDrawerManager().getCamera();
        this.functionId = world.getBuildManager().getFunctionComponentBuilder().getId();
    }

    public TaskWorker getTaskWorker() {
        return taskWorker;
    }

    public Drawer getDrawer() {
        return drawer;
    }

    public TaskGetter getTaskGetter() {
        return taskGetter;
    }

    public Cash getCash() {
        return cash;
    }

    public SaveTime getSaveTime() {
        return saveTime;
    }

    public SpecialtyWorker getSpecialtyWorker() {
        return specialtyWorker;
    }

    public Population getPopulation() {
        return population;
    }

    public ArrayList<SaveDelivery> getDeliveries() {
        return deliveries;
    }

    public Camera getCamera() {
        return camera;
    }

    public int getFunctionId() {
        return functionId;
    }
}
