package com.sovietcity.Model;

import android.util.Log;

import com.google.android.gms.games.Game;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Серёга on 02.08.2016.
 */

public class DeliveryMover {
    private CashManager cashManager;
    private TimeManager timeManager;
    private DeliveryCreator deliveryCreator;
    public void move(Delivery delivery) {
        Cell lastCell = delivery.getLastCell();
        Cell nowCell = GameMap.getGameMapCell(delivery.getY(),delivery.getX());
        Resource resource = delivery.getResource();
        int x = delivery.getX();
        int y = delivery.getY();
        double production = delivery.getQuantity();

        delivery.setFree(true);
        ArrayList<Cell> arrayList = getCellForSender(delivery,lastCell, x,y);

        if (arrayList.size()>0) {
            production/=arrayList.size();
            for (int i = 0; i<arrayList.size();i++) {
                moveTo(arrayList.get(i),deliveryCreator.getDelivery(),nowCell,production,resource);
            }
        }
    }

    public void setDeliveryCreator(DeliveryCreator deliveryCreator) {
        this.deliveryCreator = deliveryCreator;
    }

    public void moveTo(Cell cell, Delivery delivery, Cell lastCell, double quantity,Resource resource) {
        if (delivery!=null) {
            delivery.setX(cell.getX());
            delivery.setY(cell.getY());
            delivery.setFree(false);
            delivery.setLastCell(lastCell);
            delivery.setQuantity(quantity);
            delivery.setResource(resource);
            delivery.getMoveDate().setGregorianChange(timeManager.getDate().getGregorianChange());
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date =  new Date(delivery.getMoveDate().getTimeInMillis());
            String str = format1.format(date);
            Log.i("TIME_CHECK",str);
            delivery.getMoveDate().add(Calendar.DAY_OF_MONTH, cell.getChainDestination().getDayDuration());
            cell.getChainDestination().addDelivery(delivery);
        }
    }

    private ArrayList<Cell> getCellForSender(Delivery delivery, Cell lastCell,int x, int y) {
        ArrayList<Cell> arrayList = new ArrayList<>();
        if (delivery.getLastCell() == null) {
            delivery.setLastCell(GameMap.getGameMapCell(delivery.getY(), delivery.getX()));

        }
        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            if ((validateMove(GameMap.getGameMapCell(y + dy[i], x + dx[i]), delivery,lastCell))) {
                arrayList.add(GameMap.getGameMapCell(y + dy[i], x + dx[i]));
            }

        }

        return arrayList;
    }

    public boolean validateMove(Cell cell, Delivery delivery, Cell lastCell) {
        if (cell==null) Log.i("NULL_DEBUG","cell - null");
        if (delivery==null) Log.i("NULL_DEBUG","delivery - null");
        if ((lastCell==null)||(!lastCell.equals(cell))) {
            if ((cell.getChainDestination() != null) && (cell.getChainDestination().canAddDelivery(delivery))  && (cashManager.wasteMoney(cell.getChainDestination().getDeliveryPrice()))) {
                return true;
            }
        }
        return false;

    }

    public void setCashManager(CashManager cashManager) {
        this.cashManager = cashManager;
    }

    public void setTimeManager(TimeManager timeManager) {
        this.timeManager = timeManager;
    }
}
