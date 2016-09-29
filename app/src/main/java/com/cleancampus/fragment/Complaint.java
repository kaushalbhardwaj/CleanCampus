package com.cleancampus.fragment;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cleancampus.R;
import com.cleancampus.adapter.ComplaintAdapter;
import com.cleancampus.adapter.Data;
import com.cleancampus.adapter.Dbhelper;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Complaint extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Data> list = new ArrayList<>();
    private FloatingActionButton fab;
    private TextView title;
    private TextView description;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View complaintView = inflater.inflate(R.layout.fragment_complaint, container, false);

        final Dbhelper dbhelper = new Dbhelper(getContext());
        list = dbhelper.getData();

        recyclerView = (RecyclerView) complaintView.findViewById(R.id.recyler_complaint);
        title = (TextView) complaintView.findViewById(R.id.complaint);
        description = (TextView) complaintView.findViewById(R.id.description);

        adapter = new ComplaintAdapter(getContext(), list);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        fab = (FloatingActionButton) complaintView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final LayoutInflater inflater = getActivity().getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.dialog, null)).setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Dialog f = (Dialog) dialog;
                        EditText complaint = (EditText) f.findViewById(R.id.complaint);
                        EditText description = (EditText) f.findViewById(R.id.description);

                        Data data = new Data("", "", "", complaint.getText().toString(), description.getText().toString(), 0, "");
                        dbhelper.add(data);
                        list.add(0, data);
                        adapter.notifyItemInserted(0);
                        recyclerView.scrollToPosition(0);
                    }
                }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });


                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return complaintView;
    }

}
