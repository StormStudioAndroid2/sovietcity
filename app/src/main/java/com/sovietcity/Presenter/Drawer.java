package com.sovietcity.Presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.sovietcity.Model.Cell;
import com.sovietcity.Model.GameMap;
import com.sovietcity.R;

import java.io.Serializable;

/**
 * Created by Серёга on 09.04.2016.
 */
public class Drawer implements Serializable {
    private transient Bitmap focus;
    private transient Bitmap noChain;
    private transient Bitmap exportCell;
    private transient Bitmap importCell;
    private transient Bitmap buildingCell;
    private transient Bitmap exportImportCell;
    private transient Paint paint;
    private float kx;
    private float ky;

    public Drawer(Context context, int width, int height) {
        this.focus = BitmapFactory.decodeResource(context.getResources(), R.drawable.grassforbuild);
        this.focus = Bitmap.createScaledBitmap(this.focus,width,height,false);
        this.paint = new Paint();
        this.noChain = BitmapFactory.decodeResource(context.getResources(), R.drawable.nochain);
        this.noChain = Bitmap.createScaledBitmap(this.noChain,width,height,false);
        this.exportCell = BitmapFactory.decodeResource(context.getResources(), R.drawable.export_cell);
        this.exportCell = Bitmap.createScaledBitmap(this.exportCell,width,height,false);
        this.importCell = BitmapFactory.decodeResource(context.getResources(), R.drawable.import_cell);
        this.importCell = Bitmap.createScaledBitmap(this.importCell,width,height,false);
        this.exportImportCell = BitmapFactory.decodeResource(context.getResources(), R.drawable.export_import_cell);
        this.exportImportCell = Bitmap.createScaledBitmap(this.exportImportCell,width,height,false);
        this.buildingCell = BitmapFactory.decodeResource(context.getResources(), R.drawable.build_cell);
        this.buildingCell = Bitmap.createScaledBitmap(this.buildingCell,width,height,false);
        this.ky = 0;
        this.kx = 0;

    }

    public void drawPhysicMap(Canvas canvas, int width, int height) {
        float x1 = kx;
        float y1 = ky;
        for (int i = 0; i < GameMap.sizeY; i++) {
            for (int j = 0; j < GameMap.sizeX; j++) {
                if (GameMap.getGameMapCell(i, j).isFocused()) {
                    canvas.drawBitmap(this.focus, x1, y1, paint);

                } else {

                    canvas.drawBitmap(GameMap.getGameMapCell(i, j).draw(), x1, y1, paint);
                }
                x1 += width;

            }
            x1 = kx;
            y1 += height;
        }
    }

    public void drawPowerMap(Canvas canvas, int width, int height) {
        float x1 = kx;
        float y1 = ky;
        for (int i = 0; i < GameMap.sizeY; i++) {
            for (int j = 0; j < GameMap.sizeX; j++) {

                    canvas.drawBitmap(this.getNoChain(GameMap.getGameMapCell(i, j)), x1, y1, paint);
                x1 += width;

                }
            x1 = kx;
            y1 += height;
            }

        }



    public Bitmap getNoChain(Cell cell) {
        if (cell.hasBuildModel()) {
            return getBuildCell(cell);
        }
        if (cell.getResource() != null) {
            return cell.getResource().getImage();
        }
        return this.noChain;

    }
    public Bitmap getBuildCell(Cell cell) {
        if (cell.hasChainSender()&&cell.hasChainDestination()) {
            return this.exportImportCell;
        }
        if (cell.hasChainDestination()) {
            return this.importCell;
        }
        if (cell.hasChainSender()) {
            return this.exportCell;
        }
        return this.buildingCell;
    }
    public void drawPhysicCell(Canvas canvas, int y, int x, int width, int height) {
        if (GameMap.getGameMapCell(y, x).isFocused()) {
            canvas.drawBitmap(this.focus, y*height,width*x, paint);

        } else {

            canvas.drawBitmap(GameMap.getGameMapCell(y, x).draw(), y*height,width*x, paint);
        }
    }

    public void drawPowerCell(Canvas canvas, int y, int x, int width, int height) {

            canvas.drawBitmap(this.getNoChain(GameMap.getGameMapCell(y, x)), y*height,width*x, paint);

    }

    public void translate(float x, float y) {
        this.kx = x;
        this.ky = y;
        Log.i("tranlate", "i'm tranlate!");
        Log.i("x ", this.kx+"");
        Log.i("y ", this.ky+"");

    }
    public Drawer(Context context, int width, int height,Drawer drawer) {
        this.focus = BitmapFactory.decodeResource(context.getResources(), R.drawable.grassforbuild);
        this.focus = Bitmap.createScaledBitmap(this.focus,width,height,false);
        this.paint = new Paint();
        this.noChain = BitmapFactory.decodeResource(context.getResources(), R.drawable.nochain);
        this.noChain = Bitmap.createScaledBitmap(this.noChain,width,height,false);
        this.exportCell = BitmapFactory.decodeResource(context.getResources(), R.drawable.export_cell);
        this.exportCell = Bitmap.createScaledBitmap(this.exportCell,width,height,false);
        this.importCell = BitmapFactory.decodeResource(context.getResources(), R.drawable.import_cell);
        this.importCell = Bitmap.createScaledBitmap(this.importCell,width,height,false);
        this.exportImportCell = BitmapFactory.decodeResource(context.getResources(), R.drawable.export_import_cell);
        this.exportImportCell = Bitmap.createScaledBitmap(this.exportImportCell,width,height,false);
        this.buildingCell = BitmapFactory.decodeResource(context.getResources(), R.drawable.build_cell);
        this.buildingCell = Bitmap.createScaledBitmap(this.buildingCell,width,height,false);
        this.kx = drawer.kx;
        this.ky = drawer.ky;
    }
}
