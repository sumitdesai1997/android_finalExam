package com.example.android_finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // creating objects for the designing elements
    Spinner spCountry;
    TextView tvCapital,tvVisitor,tvTotal, tvCurrentPOI;
    ImageView imgFlag;
    ListView lvPOI;
    SeekBar sbVisitor;
    Button btnTotal;

    // creating global public static variable that can be accessible for all activities
    public static ArrayList<String> countryList = new ArrayList<>();
    public static ArrayList<POI> poiList = new ArrayList<>();
    public static ArrayList<POI> currentPOIList = new ArrayList<>();
    public static POI selectedPOI;
    public static double total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assigning value to the objects by finding the view by id
        spCountry = findViewById(R.id.spCountry);
        tvCapital = findViewById(R.id.tvCapital);
        tvVisitor = findViewById(R.id.tvVisitor);
        tvTotal = findViewById(R.id.tvTotal);
        tvCurrentPOI = findViewById(R.id.tvCurrentPOI);
        imgFlag = findViewById(R.id.imgFlag);
        lvPOI = findViewById(R.id.lvPOI);
        sbVisitor = findViewById(R.id.sbVisitor);
        tvCurrentPOI.setText("Niagara falls");

        // filling data for country and POI
        fillData();

        tvTotal.setText(String.format("%.2f", total));
        tvVisitor.setText("0");
        sbVisitor.setProgress(0);

        // setting value for countries in spinner
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, countryList);
        spCountry.setAdapter(aa);

        // item selected listener event for the spinner
        spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentPOIList.clear();
                getCurrentPOIList(countryList.get(position));

                // updating value for the list view as per the current poi list
                POIAdapter pa = new POIAdapter(getBaseContext(), currentPOIList);
                lvPOI.setAdapter(pa);

                int imgFlagid;
                // updating capital city and image as per the country
                if(countryList.get(position).equals("Canada")){
                    tvCapital.setText("Ottawa");
                    imgFlagid = getResources().getIdentifier("canada","mipmap",getPackageName());
                } else if(countryList.get(position).equals("USA")){
                    tvCapital.setText("Washington");
                    imgFlagid = getResources().getIdentifier("usa","mipmap",getPackageName());
                } else{
                    tvCapital.setText("London");
                    imgFlagid = getResources().getIdentifier("england","mipmap",getPackageName());
                }
                imgFlag.setImageResource(imgFlagid);

                // by default selecting the first poi from the selected country as current poi
                selectedPOI = new POI(currentPOIList.get(0).getName(),currentPOIList.get(0).getCountry(),currentPOIList.get(0).getImage(),currentPOIList.get(0).getPrice());

                // updating all the details as per the selected poi
                tvCurrentPOI.setText(selectedPOI.getName());
                sbVisitor.setProgress(1);
                tvVisitor.setText("1");
                total = selectedPOI.getPrice();
                tvTotal.setText(String.format("%.2f", total));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                currentPOIList.clear();
                getCurrentPOIList(countryList.get(0));

                // setting value for list view while nothing is selected from the spinner
                POIAdapter pa = new POIAdapter(getBaseContext(), currentPOIList);
                lvPOI.setAdapter(pa);

                // by default selecting first poi of fist country as selected poi
                selectedPOI = new POI(currentPOIList.get(0).getName(),currentPOIList.get(0).getCountry(),currentPOIList.get(0).getImage(),currentPOIList.get(0).getPrice());
                tvCapital.setText("Ottawa");
                int imgFlagid = getResources().getIdentifier("canada","mipmap",getPackageName());
                imgFlag.setImageResource(imgFlagid);
            }
        });

        // item click listner event fo the list view
        lvPOI.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPOI = new POI(currentPOIList.get(position).getName(),currentPOIList.get(position).getCountry(),currentPOIList.get(position).getImage(),currentPOIList.get(position).getPrice());

                // updating all other fields on the selection of poi from the list view
                tvCurrentPOI.setText(selectedPOI.getName());
                sbVisitor.setProgress(1);
                tvVisitor.setText("1");
                total = selectedPOI.getPrice();
                tvTotal.setText(String.format("%.2f", total));
            }
        });

        // change listener event for the seek bar
        sbVisitor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvVisitor.setText(String.valueOf(progress));

                // if visitors are greater than 15 then providing 5% discount from the total
                if(progress > 15){
                    total = (selectedPOI.getPrice() * progress) * 0.95;
                    tvTotal.setText(String.format("%.2f", total));
                } else {
                    total = (selectedPOI.getPrice() * progress);
                    tvTotal.setText(String.format("%.2f", total));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    // method to fill the array list for country and all poi
    public static void fillData(){
        // filling country list after clearing all the data
        countryList.clear();
        countryList.add("Canada");
        countryList.add("USA");
        countryList.add("England");

        // filling poilist after clearing all the data
        poiList.clear();
        poiList.add(new POI("Niagara falls","Canada","niagara", 100));
        poiList.add(new POI("CN Tower","Canada","cntower", 30));
        poiList.add(new POI("The Butchart Gardens","Canada","butchart", 30));
        poiList.add(new POI("Notre-Dame Basilica","Canada","basilica", 50));
        poiList.add(new POI("The Statue of Liberty","USA","liberty", 90));
        poiList.add(new POI("The White House","USA","whitehouse", 60));
        poiList.add(new POI("Time Square","USA","timesquare", 60));
        poiList.add(new POI("Big Ben","England","bigben", 30));
        poiList.add(new POI("Westminster Abbey","England","westminster", 25));
        poiList.add(new POI("Hyde Park","England","hydepark", 15));
    }

    // method to fetch the current poi list as per the selected country
    public static void getCurrentPOIList(String country){
        for(POI poi:poiList){
            if(poi.getCountry().equals(country)){
                currentPOIList.add(poi);
            }
        }
    }
}