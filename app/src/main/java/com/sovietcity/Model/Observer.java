package com.sovietcity.Model;

import java.io.Serializable;

/**
 * Created by Серёга on 11.05.2016.
 */
public interface Observer extends Serializable {
    public void needUpdate();
}
