package com.example.cis183_homework02_program1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tv_j_red;
    TextView tv_j_green;
    TextView tv_j_blue;
    TextView tv_j_hexRep;
    SeekBar sb_j_red;
    SeekBar sb_j_green;
    SeekBar sb_j_blue;
    Button btn_j_saveColor;
    ListView lv_j_colorList;

    ArrayList<ColorInfo> listOfColors;
    ColorInfo currentColor;
    ColorInfo selectedColor;

    ColorInfoListAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //setup GUI and Java connection
        tv_j_red        = findViewById(R.id.tv_v_red);
        tv_j_green      = findViewById(R.id.tv_v_green);
        tv_j_blue       = findViewById(R.id.tv_v_blue);
        tv_j_hexRep     = findViewById(R.id.tv_v_hexRep);
        sb_j_red        = findViewById(R.id.sb_v_red);
        sb_j_green      = findViewById(R.id.sb_v_green);
        sb_j_blue       = findViewById(R.id.sb_v_blue);
        btn_j_saveColor = findViewById(R.id.btn_v_saveColor);
        lv_j_colorList  = findViewById(R.id.lv_v_colors);

        //setup and initialize arrayList
        listOfColors = new ArrayList<ColorInfo>();
        currentColor = new ColorInfo();

        resetSetSeekBars();
        setupListViewItemClickListener();
        saveColorButtonClickEvent();
        colorSeekBarKeyEventListener();
        fillListView();

        updateBackgroundColorAndHex();
    }

    private void saveColorButtonClickEvent()
    {
        btn_j_saveColor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                addColorToList();
            }
        });
    }
    private void colorSeekBarKeyEventListener()
    {
        sb_j_red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv_j_red.setText("Red: " + i);
                currentColor.setRed(i);
                updateBackgroundColorAndHex();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sb_j_green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                tv_j_green.setText("Green: " + i);
                currentColor.setGreen(i);
                updateBackgroundColorAndHex();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
        sb_j_blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                tv_j_blue.setText("Blue: " + i);
                currentColor.setBlue(i);
                updateBackgroundColorAndHex();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });
    }

    private void updateBackgroundColorAndHex()
    {
        getWindow().getDecorView().setBackgroundColor(Color.rgb(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue()));

        String hexColor = String.format("%02X%02X%02X", currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue());
        currentColor.setHexRep(hexColor);
        tv_j_hexRep.setText("Hex: " + hexColor);

        double brightness = (0.299 * currentColor.getRed()) + (0.587 * currentColor.getGreen()) + (0.114 * currentColor.getBlue());

        int textColor = (brightness < 128) ? Color.WHITE : Color.BLACK;
        tv_j_red.setTextColor(textColor);
        tv_j_green.setTextColor(textColor);
        tv_j_blue.setTextColor(textColor);
        tv_j_hexRep.setTextColor(textColor);
    }



    private void addColorToList()
    {
        //want to make sure the color isn't already in the list
        for(ColorInfo color : listOfColors)
        {
            if(color.getHexRep().equals(currentColor.getHexRep()))
            {
                return;
            }
        }
        //create a new colorInfo object
        ColorInfo colorToAdd = new ColorInfo(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), currentColor.getHexRep());

        listOfColors.add(colorToAdd);
        adaptor.notifyDataSetChanged();
        resetSetSeekBars();
    }

    private void resetSetSeekBars()
    {
        sb_j_red.setProgress(255);
        sb_j_green.setProgress(255);
        sb_j_blue.setProgress(255);

        tv_j_red.setText("Red: 255");
        tv_j_green.setText("Green: 255");
        tv_j_blue.setText("Blue: 255");

        currentColor.setRed(255);
        currentColor.setGreen(255);
        currentColor.setBlue(255);
        currentColor.setHexRep("FFFFFF");

        tv_j_hexRep.setText("Hex: FFFFFF");

        getWindow().getDecorView().setBackgroundColor(Color.rgb(255,255,255));
    }

    private void fillListView()
    {
        adaptor = new ColorInfoListAdaptor(this, listOfColors);
        lv_j_colorList.setAdapter(adaptor);

    }
    private void setupListViewItemClickListener()
    {
        lv_j_colorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ColorInfo selectedColor = (ColorInfo) adaptor.getItem(i);
                applySelectedColor(selectedColor);
            }
        });
    }


    private void applySelectedColor(ColorInfo color)
    {
        sb_j_red.setProgress(color.getRed());
        sb_j_green.setProgress(color.getGreen());
        sb_j_blue.setProgress(color.getBlue());

        tv_j_red.setText("Red: "+ color.getRed());
        tv_j_green.setText("Green: " + color.getGreen());
        tv_j_blue.setText("Blue: " + color.getBlue());
        tv_j_hexRep.setText("Hex: " + color.getHexRep());

        getWindow().getDecorView().setBackgroundColor(Color.rgb(color.getRed(), color.getGreen(), color.getBlue()));

        currentColor.setRed(color.getRed());
        currentColor.setGreen(color.getGreen());
        currentColor.setBlue(color.getBlue());
        currentColor.setHexRep(color.getHexRep());

    }




}