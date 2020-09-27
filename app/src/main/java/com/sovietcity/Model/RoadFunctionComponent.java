package com.sovietcity.Model;

import com.sovietcity.Presenter.World;
import com.sovietcity.View.MainActivity;

import java.io.Serializable;

//функциональный компонент дорог
public class RoadFunctionComponent extends FunctionComponent implements IRoadFunction, Serializable, IChainDestination  {
    private int x;
    private int y;
    private int moveDay;
    private int moveCost;

    @Override
    public void function() {

    }

    @Override
    public void startFunction(BuildModel buildModel) {

    }

    @Override
    public void about(MainActivity mainActivity, int y, int x) {

    }

    @Override
    public IChainDestination getChainDestination() {
        return null;
    }

    @Override
    public IChainSender getChainSender() {
        return null;
    }

    @Override
    public void deserialization(World world) {

    }

    @Override
    public boolean cloneable() {
        return false;
    }

    @Override
    public void setId(int id) {

    }


    @Override
    public int getId() {
        return 0;
    }

    @Override
    public FunctionComponent clone() {
        return this;
    }

    @Override
    public void setBlocks(int blocks) {

    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setMoveCost(int moveCost) {
        this.moveCost = moveCost;
    }

    public void setMoveDay(int moveDay) {
        this.moveDay = moveDay;
    }

    @Override
    public String toString() {
        return "Ускорение доставки материалов и уменьшение стоимости доставки";
    }

    @Override
    public void addDelivery(Delivery delivery) {

    }

    @Override
    public boolean canAddDelivery(Delivery delivery) {
        return true;
    }

    @Override
    public int getDayDuration() {
        return 5;
    }

    @Override
    public int getDeliveryPrice() {
        return 100;
    }
}
