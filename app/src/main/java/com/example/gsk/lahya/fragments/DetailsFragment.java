package com.example.gsk.lahya.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gsk.lahya.R;
import com.example.gsk.lahya.model.Subscriber;
import com.example.gsk.lahya.model.Subscribers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DetailsFragment extends DialogFragment {
    private Spinner meterStatusSpinner;
    private String[] meter_status;
    private OnFragmentInteractionListener mListener;

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_details, container, false );
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction( uri );
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException( context.toString()
//                    + " must implement OnFragmentInteractionListener" );
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        meter_status = getResources().getStringArray( R.array.meter_status );
        meterStatusSpinner = (Spinner) view.findViewById( R.id.stuts_spinner );
        ArrayAdapter<String> streetsNumbersAdapter = new ArrayAdapter<String>( getActivity(), android.R.layout.simple_list_item_1, meter_status );
        meterStatusSpinner.setAdapter( streetsNumbersAdapter );

        TextView no_tv = view.findViewById( R.id.sub_no_in_dialog );
        TextView name_tv = view.findViewById( R.id.sub_name_in_dialog );
        final String sub_no = ((Subscriber) getArguments().getSerializable( "subscriber" )).getSubNo() + "";
        final String sub_name = ((Subscriber) getArguments().getSerializable( "subscriber" )).getSubName() + "";
        no_tv.setText( sub_no );
        name_tv.setText( sub_name );
        final String[] status = new String[1];
        meterStatusSpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                status[0] = meter_status[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        } );

        Button saveBtn = view.findViewById( R.id.button_save );
        saveBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText currentReadingEditText = DetailsFragment.this.getView().findViewById( R.id.inset_read );
                String currentReading = currentReadingEditText.getText().toString();

                Subscribers.getInstance().get( sub_no ).setStatus( status[0] );
                Subscribers.getInstance().get( sub_no ).setCurrentReading( currentReading );
                String today = new SimpleDateFormat( "yyyy/MM/dd hh:mm a",Locale.CANADA ).format( new Date() );
                Subscribers.getInstance().get( sub_no ).setCurrentReadingDate( today );
                try {
                    DetailsFragment.this.dismiss();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        } );
        Button cancelBtn = view.findViewById( R.id.button_cancel );
        cancelBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsFragment.this.dismiss();

            }
        } );

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }
}
