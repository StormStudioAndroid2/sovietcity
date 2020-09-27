package com.sovietcity.Model;


//    компонент, который реализует строительство зданий
public abstract class BuildComponent {
    private int blockPrice;
    public abstract boolean selectCell(MapManager mapManager, BuildManager buildManager,int y, int x, BuildModel buildModel);
    public  boolean setBlockPrice(int blockPrice) {
        this.blockPrice = blockPrice;
        return true;
    }
    public int getBlockPrice() {
        return this.blockPrice;
    }
    public abstract boolean payForBuilding(CashManager cashManager);

}
