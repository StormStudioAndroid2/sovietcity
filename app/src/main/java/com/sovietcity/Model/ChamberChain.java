package com.sovietcity.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.sovietcity.Presenter.DrawerManager;
import com.sovietcity.R;

import java.io.Serializable;

/**
 * Created by Серёга on 16.03.2016.
 */
//    визуальное представление цепочки производства
public enum ChamberChain implements Serializable {
    LEFT {
        private Bitmap texture;

        @Override
        public void setTexture(Context context, DrawerManager drawerManager) {

            this.texture = BitmapFactory.decodeResource(context.getResources(), R.drawable.chain_left);
            this.texture = drawerManager.setSize(this.texture);

        }

        @Override
        public Bitmap getTexture() {
            return this.texture;
        }

        @Override
        public String toString() {
            return "LEFT";
        }
    },
    RIGHT {
        private Bitmap texture;

        @Override
        public void setTexture(Context context, DrawerManager drawerManager) {

            this.texture = BitmapFactory.decodeResource(context.getResources(), R.drawable.chain_right);
            this.texture = drawerManager.setSize(this.texture);
        }

        @Override
        public Bitmap getTexture() {
            return this.texture;
        }

        @Override
        public String toString() {
            return "RIGHT";
        }
    },
    DOWN {
        private Bitmap texture;

        @Override
        public void setTexture(Context context, DrawerManager drawerManager) {

            this.texture = BitmapFactory.decodeResource(context.getResources(), R.drawable.chain_down);
            this.texture = drawerManager.setSize(this.texture);

        }

        @Override
        public Bitmap getTexture() {
            return this.texture;
        }

        @Override
        public String toString() {
            return "DOWN";
        }
    },
    UP {
        private Bitmap texture;

        @Override
        public void setTexture(Context context, DrawerManager drawerManager) {
            this.texture = BitmapFactory.decodeResource(context.getResources(), R.drawable.chain_up);
            this.texture = drawerManager.setSize(this.texture);

        }

        @Override
        public String toString() {
            return "UP";
        }

        @Override
        public Bitmap getTexture() {
            return this.texture;
        }
    };

    public abstract void setTexture(Context context, DrawerManager drawerManager);

    public abstract Bitmap getTexture();


}
