package com.sovietcity.View;

import android.content.Context;
import android.content.ContextWrapper;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;

import com.sovietcity.Model.Save;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class Saver {

    private ArrayList<File> saves;
    private File directory;
    public void writeSaveInFile(Context context, byte[] arr, String name) {
         name = name + ".sc";
        File file = new File(directory.getAbsolutePath()+"/"+name);
        Log.i("FILE_PATH",file.getAbsolutePath());
        FileOutputStream fos = null;
        try {
            file.createNewFile();
            fos = new FileOutputStream(file);

            fos.write(arr);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        saves.add(file);
    }

    public byte[] loadSaveFromFile(int id) {

       File file = saves.get(id);
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bytes;
    }

    public void deleteSave(Context context,int id) {
        saves.get(id).delete();
        saves.remove(id);
    }
    public void setSaves(Context context) {
        saves = new ArrayList<>();
        File dir = context.getDir("SovietSaves",Context.MODE_PRIVATE);
       Boolean b = dir.isDirectory();
        Log.i("CHECK",b.toString());
        Log.i("SOVIET_PATH",dir.toString());
        File[] files = dir.listFiles();
        ArrayList<File> inFiles = new ArrayList<File>(Arrays.asList(files));
        directory = dir;
        this.saves = inFiles;
    }
    public int getSize() {
        return this.saves.size();
    }
    public ArrayList<File> getSaves() {
        return this.saves;
    }
}
