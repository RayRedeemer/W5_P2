package com.example.w5_p2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.lang.reflect.Field;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrawableFragment extends Fragment {
    ArrayList<Drawable> drawables;
    float[] ratings;
    private int currDrawableIndex;

    private ImageView ivRateMe;
    private RatingBar rtRatings;
    private static final int initRating = 5;


    public DrawableFragment() {
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
        View v = inflater.inflate(R.layout.fragment_drawable, container, false);

        ivRateMe = (ImageView) v.findViewById(R.id.iv);
        rtRatings = (RatingBar) v.findViewById(R.id.rb);

        currDrawableIndex = 0;  //ArrayList Index of Current Drawable.
        getDrawables();         //Retrieves the drawables we want, ie, prefixed with "animal_"
        ivRateMe.setImageDrawable(null);  //Clearing out the default image from design time.
        changePicture();        //Sets the ImageView to the first drawable in the list.
        ratings = new float[drawables.size()];                //set all the initial ratings to 5
        for(int i = 0; i < drawables.size(); i++){
            ratings[i] = initRating;
        }

        return v;
    }

    public void onPressLeft(){
        ratings[currDrawableIndex] = rtRatings.getRating();
        if (currDrawableIndex == 0)
            currDrawableIndex = drawables.size() - 1;
        else
            currDrawableIndex--;
        changePicture();
        rtRatings.setRating(ratings[currDrawableIndex]);
    }

    public void onPressRight(){
        ratings[currDrawableIndex] = rtRatings.getRating();
        if (currDrawableIndex == drawables.size() - 1)
            currDrawableIndex = 0;
        else
            currDrawableIndex++;
        changePicture();
        rtRatings.setRating(ratings[currDrawableIndex]);
    }

//Routine to change the picture in the image view dynamically.
    public void changePicture() {
        ivRateMe.setImageDrawable(drawables.get(currDrawableIndex));  //note, this is the preferred way of changing images, don't worry about parent viewgroup size changes.
    }

//Quick and Dirty way to get drawable resources, we prefix with "animal_" to filter out just the ones we want to display.
//REF: http://stackoverflow.com/questions/31921927/how-to-get-all-drawable-resources
    public void getDrawables() {
        Field[] drawablesFields = com.example.w5_p2.R.drawable.class.getFields();  //getting array of ALL drawables.
        drawables = new ArrayList<>();  //we prefer an ArrayList, to store the drawables we are interested in.  Why ArrayList and not an Array here? A: _________

        String fieldName;
        for (Field field : drawablesFields) {   //1. Looping over the Array of All Drawables...
            try {
                fieldName = field.getName();    //2. Identifying the Drawables Name, eg, "animal_bewildered_monkey"
                Log.i("LOG_TAG", "com.your.project.R.drawable." + fieldName);
                if (fieldName.startsWith("animals_"))  //3. Adding drawable resources that have our prefix, specifically "animal_".
                    drawables.add(getResources().getDrawable(field.getInt(null)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
