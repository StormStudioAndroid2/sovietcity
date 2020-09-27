package com.sovietcity.Model;

/**
 * Created by Серёга on 25.05.2016.
 */
public class Politic {
    private String name;
    private String describe;
    private boolean selected;
    private PoliticInterface politicInterface;

    public PoliticInterface getPoliticInterface() {
        return politicInterface;
    }

    public String getDescribe() {
        return describe;
    }

    public String getName() {
        return name;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoliticInterface(PoliticInterface politicInterface) {
        this.politicInterface = politicInterface;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
