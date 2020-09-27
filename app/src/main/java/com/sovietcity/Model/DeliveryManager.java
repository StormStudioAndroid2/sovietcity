package com.sovietcity.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.sovietcity.Presenter.Drawer;
import com.sovietcity.Presenter.DrawerManager;
import com.sovietcity.Presenter.World;
import com.sovietcity.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

//менеджер Доставок
public class DeliveryManager implements Serializable {
    private transient ArrayList<Delivery> deliveries;
    private transient TimeManager timeManager;
    private transient CashManager cashManager;
    private transient Bitmap truckIcon;
    private transient DeliveryMover deliveryMover;
    private int maxDeliveries;
    private double price;
    private double tax;

    public DeliveryManager() {
        this.deliveries = new ArrayList<>();
        this.price = 1000;
        this.tax = 100;
        this.deliveryMover = new DeliveryMover();
    }
    public void setDeliveryCreator(DeliveryCreator deliveryCreator) {
        this.deliveryMover.setDeliveryCreator(deliveryCreator);
    }
    public void setTruckIcon(Context context, DrawerManager drawerManager) {
        this.truckIcon = drawerManager.setSize(BitmapFactory.decodeResource(context.getResources(), R.drawable.truck_icon));
    }

    public Bitmap getTruckIcon() {
        return truckIcon;
    }

    public void addDelivery(Delivery delivery) {
        //    добавка доставки в упорядоченный по дате прибытия список
        int index = Math.abs(Collections.binarySearch(this.deliveries, delivery));
        if (index > -this.deliveries.size()) {
            this.deliveries.add(delivery);
        } else {
            this.deliveries.add(index, delivery);

        }
    }

    public void payForDeliveries(CashManager cashManager) {
        cashManager.wasteMoney(tax * this.deliveries.size());

    }

    public double getPrice() {
        return price;
    }

    public double getTax() {
        return tax;
    }

    public void deleteDelivery(Delivery delivery) {
        this.deliveries.remove(delivery);
    }

    public void updateDeliveries(World world, GregorianCalendar date) {
        if (this.deliveries.size() != 0) {
            boolean flag = true;
            for (int i = 0; ((i < deliveries.size())) && (flag) && (i >= 0); i++) {
                flag = false;

                if ((!this.deliveries.get(i).isFree()) && (this.deliveries.get(i).getMoveDate().after(date))) {
                    flag = true;
                    this.deliveryMover.move(deliveries.get(i));
                }

            }
        }
    }

    public void increaseMaxDeliveries(int maxDeliveries) {
        this.maxDeliveries += maxDeliveries;
        addDeliveries();

    }

    private void addDeliveries() {
        if (this.maxDeliveries > this.deliveries.size()) {

            while (this.maxDeliveries > this.deliveries.size()) {
                Delivery delivery = new Delivery();
                delivery.setFree(true);
                this.deliveries.add(delivery);
            }
        }
    }

    public void setTimeManager(TimeManager timeManager) {
        this.timeManager = timeManager;
        this.deliveryMover.setTimeManager(timeManager);
    }

    public TimeManager getTimeManager() {
        return timeManager;
    }

    public ArrayList<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(ArrayList<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public void deleteDeliveries(int x, int y) {
        for (int i = 0; ((i < deliveries.size()) && (i >= 0)); i++) {
            if ((deliveries.get(i).getX() == x) && (deliveries.get(i).getY() == y)) {
                deliveries.remove(i);
                i--;
            }
        }
    }

    public Delivery getDelivery() {
        for (int i = 0; i < deliveries.size(); i++) {
            if (deliveries.get(i).isFree()) {
                return deliveries.get(i);
            }
        }

        return null;
    }

    public int countNotFreeDeliveries() {
        int count = 0;
        for (int i = 0; i < deliveries.size(); i++) {
            if (!deliveries.get(i).isFree()) count++;
        }
        return count;
    }

    public void setCashManager(CashManager cashManager) {
        this.cashManager = cashManager;
        deliveryMover.setCashManager(cashManager);
    }
}
