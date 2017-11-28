package de.adbe.mm.io;

import android.content.Context;

import java.io.File;
import java.nio.file.Path;

/**
 * Created by adbe on 28.11.17.
 */

public class FileWriter {

    Context context;
    File folder;

    public FileWriter(){//request Context form activity ba useing getApplicationContext() !! no i give a shit to Context

        this.context = context;
        this.folder = createRequiredDir();


    }

    private File createRequiredDir(){
        File path = new File("/storage/emulated/0/.mm");
        if(!path.exists()) {
            System.out.println("Folder created: " + path.mkdir());
        }
        return path;
    }
}
