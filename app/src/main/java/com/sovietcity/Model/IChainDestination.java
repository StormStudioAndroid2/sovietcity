package com.sovietcity.Model;

import java.io.Serializable;

// приемщик доставки
public interface IChainDestination extends Serializable  {
    void addDelivery(Delivery delivery);
    boolean canAddDelivery(Delivery delivery);
    int getDayDuration();
    int getDeliveryPrice();
}
