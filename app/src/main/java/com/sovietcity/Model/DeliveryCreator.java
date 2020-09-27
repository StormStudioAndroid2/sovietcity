package com.sovietcity.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

//    создает Доставки

public class DeliveryCreator implements Serializable {
    private DeliveryManager deliveryManager;

    public DeliveryCreator(DeliveryManager deliveryManager) {
        this.deliveryManager = deliveryManager;
    }

    public void setParameters(Delivery delivery, int x, int y, Resource resource, double quantity, int year, int month, int day, boolean free) {
        if (delivery != null) {
            delivery.setFree(free);
            delivery.setDate(year, month, day);
            delivery.setQuantity(quantity);
            delivery.setResource(resource);
            delivery.setX(x);
            delivery.setY(y);
        }
    }

    public Delivery createDelivery(int y, int x, double quantity, Resource resource, int year, int month, int day) {
        Delivery delivery = deliveryManager.getDelivery();
        setParameters(delivery,x,y,resource,quantity,year,month,day,false);
        return delivery;
    }
    public Delivery getDelivery() {
      return  deliveryManager.getDelivery();
    }
    public void deleteDelivery(Delivery delivery) {
        deliveryManager.deleteDelivery(delivery);
    }

    public Delivery copy(Delivery delivery) {

        Delivery delivery1 = deliveryManager.getDelivery();
        if (delivery1 != null) {
            delivery1.setFree(false);
            delivery1.setQuantity(delivery.getQuantity());
            delivery1.setResource(delivery.getResource());
            delivery1.setX(delivery.getX());
            delivery1.setY(delivery.getY());
        }
        return delivery1;
    }

    public void startDelivery(ArrayList<Cell> arrayList, Material material) {
        checkCellList(arrayList);
        if ((arrayList.size() > 0) && (material.getQuantity() >= material.getWasteQuantity() / arrayList.size())) {

            for (int i = 0; i < arrayList.size(); i++) {

                    Delivery delivery = deliveryManager.getDelivery();
                    setParameters(delivery,arrayList.get(i).getX(),arrayList.get(i).getY(),material.getResource(),material.getWasteQuantity() / arrayList.size(),this.deliveryManager.getTimeManager().getDate().get(Calendar.YEAR), this.deliveryManager.getTimeManager().getDate().get(Calendar.MONTH), this.deliveryManager.getTimeManager().getDate().get(Calendar.DAY_OF_MONTH) + arrayList.get(i).getChainDestination().getDayDuration(),true);
                    if (delivery != null) {
                        delivery.setFree(false);
                        delivery.setQuantity(material.getWasteQuantity() / arrayList.size());
                        material.decreaseQuantity((material.getWasteQuantity() / arrayList.size()));
                        delivery.setResource(material.getResource());
                        delivery.setDate(this.deliveryManager.getTimeManager().getDate().get(Calendar.YEAR), this.deliveryManager.getTimeManager().getDate().get(Calendar.MONTH), this.deliveryManager.getTimeManager().getDate().get(Calendar.DAY_OF_MONTH) + arrayList.get(i).getChainDestination().getDayDuration());
                        delivery.setX(arrayList.get(i).getX());
                        delivery.setY(arrayList.get(i).getY());

                }
            }
        }
    }

    public void checkCellList(ArrayList<Cell> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (!GameMap.getGameMapCell(arrayList.get(i).getY(), arrayList.get(i).getX()).hasChainDestination()) {
                arrayList.remove(arrayList.get(i));
                i -= 2;
                if (i < 0) {
                    i = 0;
                }
            }
        }
    }
}
