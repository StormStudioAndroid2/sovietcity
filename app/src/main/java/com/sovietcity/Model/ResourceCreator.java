package com.sovietcity.Model;

import android.graphics.Bitmap;

/**
 * Created by Серёга on 22.04.2016.
 */
public class ResourceCreator {
    public  Resource createResource(String name, Bitmap icon, int price) {
            Resource resource = new Resource(name);
        resource.setImage(icon);
        resource.setPrice(price);
        return resource;
    }
}
