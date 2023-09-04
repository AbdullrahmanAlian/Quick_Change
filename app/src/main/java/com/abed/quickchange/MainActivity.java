package com.abed.quickchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.abed.quickchange.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Context context=MainActivity.this;
    ArrayList<String> countries;
    private final String egyptianPound="Egyptian pound";
    private final String americanDolor="American dolor";
    private final String AED="AED";
    private final String GBP="GBP";
    String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        countries=new ArrayList<>(Arrays.asList(egyptianPound,americanDolor,AED,GBP));
        populateDropDownMenu();

        binding.btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String etAmount=binding.etAmount.getText().toString();
               if(!etAmount.equals("")) {
                   String toMenu=binding.toCurrencyMenu.getText().toString();
                   double amount =Double.parseDouble(etAmount);
                   double toValue=0.0;
                   switch (toMenu){
                       case egyptianPound:
                           toValue=15.37;
                           break;
                       case americanDolor:
                           toValue=1;
                           break;
                       case AED:
                           toValue=3.67;
                           break;
                       case GBP:
                           toValue=.74;
                           break;
                   }
                   String fromMenu=binding.fromCurrencyMenu.getText().toString();
                   double fromValue=0.0;
                   switch (fromMenu){
                       case egyptianPound:
                           fromValue=15.37;
                           break;
                       case americanDolor:
                           fromValue=1;
                           break;
                       case AED:
                           fromValue=3.67;
                           break;
                       case GBP:
                           fromValue=.74;
                           break;
                   }
                double result=(amount * toValue)/fromValue;
                   String formatResult=String.format("%.2f",result);
                   binding.etResult.setText(formatResult);


               }else{
                   Snackbar.make(binding.getRoot(),"Empty Field", Toast.LENGTH_LONG).show();
               }
            }
        });

    }

    private void populateDropDownMenu(){
        ArrayAdapter<String> adapter=new ArrayAdapter<>(context, android.R.layout.simple_list_item_1,countries);
        binding.toCurrencyMenu.setAdapter(adapter);
        binding.fromCurrencyMenu.setAdapter(adapter);

    }
}