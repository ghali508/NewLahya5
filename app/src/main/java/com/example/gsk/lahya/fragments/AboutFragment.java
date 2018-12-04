package com.example.gsk.lahya.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gsk.lahya.Controllers.MySaxParser;
import com.example.gsk.lahya.R;

import java.io.IOException;

public class AboutFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            MySaxParser parser = new MySaxParser( getContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inflater.inflate(R.layout.fragment_about,container,false);
    }
}
