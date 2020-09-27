package com.sovietcity.Model;

/**
 * Created by Серёга on 03.07.2016.
 */
public interface ISaveLoader {
    void loadSave(byte[] array);
    byte[] writeSave();
    void closeSaveMenu();
}
