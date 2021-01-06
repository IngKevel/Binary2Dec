package com.lacuevadelbot.binary2dec.binary2dec.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.lacuevadelbot.binary2dec.R;

public class MainActivity extends AppCompatActivity {

    TextInputEditText mTextInputBinaryNumber;
    TextView mTextViewDecimalNumber;
    Button mButtonConvert;
    TextView mTextViewHistory;

    String mResult = "0";
    Integer mBinaryNumber = 0;

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextInputBinaryNumber = findViewById(R.id.textInputBinary);
        mTextViewDecimalNumber = findViewById(R.id.textViewResult);
        mTextViewHistory = findViewById(R.id.textViewHistory);
        mTextViewHistory.setMovementMethod(new ScrollingMovementMethod());

        mButtonConvert = findViewById(R.id.btnConvert);
        mButtonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConvertBinToDec();
            }
        });

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    private void ConvertBinToDec(){
        if(!mTextInputBinaryNumber.getText().toString().isEmpty()){
            mBinaryNumber = Integer.parseInt(mTextInputBinaryNumber.getText().toString());
            Integer valorCounter = 0;
            Integer valorFinder = 1;
            Integer decimalNumber = 0;
            Integer powNumber = 0;

            while(mBinaryNumber > valorFinder){
                valorFinder = valorFinder * 10;
                valorCounter++;
            }

            if(mBinaryNumber < valorFinder && mBinaryNumber != 0){
                valorFinder = valorFinder / 10;
                valorCounter--;
            }

            do{
                if((mBinaryNumber / valorFinder) == 1){
                    powNumber = (int)Math.pow(2,valorCounter);
                    decimalNumber = decimalNumber + powNumber;
                    mBinaryNumber = mBinaryNumber - valorFinder;
                    valorFinder = valorFinder / 10;
                    valorCounter--;
                }
                else if((mBinaryNumber / valorFinder) == 0){
                    valorFinder = valorFinder / 10;
                    valorCounter--;
                }
            }while(mBinaryNumber != 0);

            mResult = decimalNumber.toString();
            mTextViewDecimalNumber.setText(mResult);
            ShowHistory();
        }
        else{
            Toast.makeText(this, "Ingresa un numero binario para continuar", Toast.LENGTH_SHORT).show();
        }
    }

    private void ShowHistory(){
        if(mTextViewHistory.getText() == "0 = 0"){
            mTextViewHistory.setText(mTextInputBinaryNumber.getText().toString() + " = " + mResult);
        }
        else if (mTextViewHistory.getText() != "0 = 0"){
            mTextViewHistory.append("\n" + mTextInputBinaryNumber.getText().toString() + " = " + mResult);
        }
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if(item.getItemId() == R.id.itemBinary){
                        //openFragment(new BinaryFragment());
                    }
                    else if(item.getItemId() == R.id.itemDecimal){
                        Intent intent = new Intent(MainActivity.this, DecimalActivity.class);
                        startActivity(intent);
                    }
                    return true;
                }
            };
}