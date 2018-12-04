package com.example.gsk.lahya.Controllers;

import android.os.Environment;

import com.example.gsk.lahya.model.Subscriber;
import com.example.gsk.lahya.model.Subscribers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import io.realm.RealmList;

public class ExcelController {

    static File output_all = new File( Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_DOWNLOADS ).toString(), "output_all.txt" );
    static File output_error = new File( Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_DOWNLOADS ).toString(), "output_error.txt" );

    static File inputFile = new File( Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_DOWNLOADS ).toString(), "output_all.txt" );


    public static RealmList<Subscriber> readFromExcelFile() {


        return null;
    }

    public static boolean writeToExcelFile(List<Subscriber> subscribers) {
        FileWriter all_fw = null;
        FileWriter error_fw = null;
        try {
            all_fw = new FileWriter( output_all );
            error_fw = new FileWriter( output_error );
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        //all_fw.write( ",,,,,,,,,,,header,,,,,,,,," );
        for (Subscriber s : subscribers) {

            try {
                if (!s.getStatus().equals( "بحالة جيدة" )) {
                    error_fw.write( s.toString() + "\n" );
                }
                all_fw.write( s.toString() + "\n" );
            } catch (IOException e) {
                return false;
            }
        }
        try {
            all_fw.close();
            error_fw.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

}
