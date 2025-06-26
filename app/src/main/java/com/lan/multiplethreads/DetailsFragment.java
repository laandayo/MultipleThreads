package com.lan.multiplethreads;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DetailsFragment extends Fragment {
    TextView name, location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_info, container, false);
        name = (TextView) view.findViewById(R.id.Name);
        location = (TextView) view.findViewById(R.id.Location);
        return view;
    }

    public void change(String uname, String ulocation) {
        if (name != null && location != null) {
            name.setText(uname);
            location.setText(ulocation);
        }
    }
}