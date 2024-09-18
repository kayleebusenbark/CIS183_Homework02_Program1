package com.example.cis183_homework02_program1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class ColorInfoListAdaptor extends BaseAdapter
{
    Context context;
    ArrayList<ColorInfo> listOfColorInfo;


    public ColorInfoListAdaptor(Context c, ArrayList<ColorInfo> ls)
    {
        context = c;
        listOfColorInfo = ls;
    }

    @Override
    public int getCount()
    {
        return listOfColorInfo.size();
    }

    @Override
    public Object getItem(int i)
    {
        return listOfColorInfo.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if(view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.custom_cell, null);
        }

        //find the GUI elements in my custom_cell
        TextView red = view.findViewById(R.id.tv_v_cc_red);
        TextView green = view.findViewById(R.id.tv_v_cc_green);
        TextView blue = view.findViewById(R.id.tv_v_cc_blue);
        TextView hex = view.findViewById(R.id.tv_v_cc_hex);

        //get data for this particular user from the employee list
        //i can access different elements based off the i value that is passed to this function

        ColorInfo colorInfo = listOfColorInfo.get(i);

        //set the GUI from the custom_cell
        red.setText("Red: " + colorInfo.getRed());
        green.setText("Green: " + colorInfo.getGreen());
        blue.setText("Blue: " + colorInfo.getBlue());
        hex.setText("Hex: " + colorInfo.getHexRep());

        view.setBackgroundColor(Color.rgb(colorInfo.getRed(), colorInfo.getGreen(), colorInfo.getBlue()));

        double brightness = (0.299 * colorInfo.getRed()) + (0.587 * colorInfo.getGreen()) + (0.114 * colorInfo.getBlue());
        int textColor = (brightness < 128) ? Color.WHITE : Color.BLACK;

        red.setTextColor(textColor);
        green.setTextColor(textColor);
        blue.setTextColor(textColor);
        hex.setTextColor(textColor);

        return view;
    }
}
