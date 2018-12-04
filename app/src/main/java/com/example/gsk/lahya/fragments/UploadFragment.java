package com.example.gsk.lahya.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gsk.lahya.Controllers.ExcelController;
import com.example.gsk.lahya.Controllers.RealmController;
import com.example.gsk.lahya.R;
import com.example.gsk.lahya.model.Subscriber;
import com.example.gsk.lahya.model.Subscribers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class UploadFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate( R.layout.fragment_uplad, container, false );
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        getActivity().requestPermissions( new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 111 );
        ProgressBar progressBar = getView().findViewById( R.id.uploadProgressBar );
        TextView uploadTextView = getView().findViewById( R.id.upload_percentage_tv );

        boolean readingCompleted = true;
        for (Subscriber s : Subscribers.getInstance().values()) {
            if (s.getCurrentReading().equals( "" )) {
                readingCompleted = false;
            }
        }

        if (readingCompleted) {

            ExcelController.writeToExcelFile(RealmController.getAllSubscribers() );
            progressBar.setVisibility( View.INVISIBLE );
            uploadTextView.setText( "تم رفع البيانات" );

        } else {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder( getContext() );
            alertDialogBuilder.setPositiveButton( "موافق", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    getFragmentManager().beginTransaction().replace( R.id.fragment_container, new InsertFragment() ).commit();
                }
            } );
            alertDialogBuilder.setTitle( "الرجاء استكمال القراءات قبل الرفع" );
            alertDialogBuilder.create();
            alertDialogBuilder.show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        Log.d( "mmmm", "nnnn" );

        if (requestCode == 111) {
            Log.d( "mmmm", getContext().getPackageName() );

        }
    }
}
