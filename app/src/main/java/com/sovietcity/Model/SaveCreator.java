package com.sovietcity.Model;

import com.sovietcity.Presenter.World;

import java.io.IOException;

/**
 * Created by Серёга on 22.05.2016.
 */
public class SaveCreator {
    public byte[] createSave(World world) throws IOException {
        Save save = new Save();
        save.setValues(world);
        return save.serialize();
    }
}
