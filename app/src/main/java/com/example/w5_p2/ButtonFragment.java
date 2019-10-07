package com.example.w5_p2;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ButtonFragment extends Fragment {
    private Button btnLeft;
    private Button btnRight;

    public ButtonFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_button, container, false);

        btnLeft = (Button) v.findViewById(R.id.bLeft);
        btnRight = (Button) v.findViewById(R.id.bRight);

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = (FragmentManager) getActivity().getSupportFragmentManager();
                DrawableFragment fragment = (DrawableFragment) manager.findFragmentById(R.id.fDrawable);
                fragment.onPressLeft();
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = (FragmentManager) getActivity().getSupportFragmentManager();
                DrawableFragment fragment = (DrawableFragment) manager.findFragmentById(R.id.fDrawable);
                fragment.onPressRight();
            }
        });

        return v;
    }
}
