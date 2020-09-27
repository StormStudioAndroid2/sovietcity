package com.sovietcity.Presenter;

import java.io.Serializable;

/**
 * Created by Серёга on 10.04.2016.
 */
//класс, сообзающий surfaceView, когда нужно перерисовывать поле
public class DrawEvent implements Serializable {
    private boolean isNeedUpdate;
    private boolean isScroll;
    private boolean isDrawCell;
    private int y;
    private int x;

    public boolean isNeedUpdate() {
        return isNeedUpdate;
    }

    public boolean isDrawCell() {
        return isDrawCell;
    }

    public boolean isScroll() {
        return isScroll;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setDrawCell(boolean drawCell) {
        isDrawCell = drawCell;
    }

    public void setNeedUpdate(boolean needUpdate) {
        isNeedUpdate = needUpdate;
    }

    public void setScroll(boolean scroll) {
        isScroll = scroll;
    }
      public void setCoordinate(int y,int x) {
          this.y = y;
          this.x = x;
      }
}
