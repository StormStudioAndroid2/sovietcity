package com.sovietcity.Model;

import com.sovietcity.Presenter.World;
import com.sovietcity.View.MainActivity;

import java.io.Serializable;

// функциональный компонент
public abstract class FunctionComponent implements Serializable {
    public abstract void function();

    public abstract void startFunction(BuildModel buildModel);

    public abstract void about(MainActivity mainActivity, int y, int x);

    public abstract IChainDestination getChainDestination();

    public abstract IChainSender getChainSender();

    public abstract void deserialization(World world);

    public abstract boolean cloneable();

    public abstract void setId(int id);

    public abstract int getId();

    public abstract FunctionComponent clone();
    public abstract void setBlocks(int blocks);
}
