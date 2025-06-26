package com.lan.multiplethreads;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

public class ListMenuFragment extends ListFragment {
    String[] users = new String[] { "Laptop","Ram","Monitor","Mobile","Mouse","Keyboard" };
    String[] location = new String[]{"USA","Malaysia","ThaiLand","China","Ukraina","Singapo"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.listitems_info, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, users);
        setListAdapter(adapter);
        return view;
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        DetailsFragment txt = (DetailsFragment) getFragmentManager().findFragmentById(R.id.fragment2);
        if (txt != null) {
            txt.change("Name: " + users[position], "Location : " + location[position]);
            getListView().setSelector(android.R.color.holo_blue_dark);
        } else {
            Log.e("ListMenuFragment", "DetailsFragment not found!");
        }
    }
}
