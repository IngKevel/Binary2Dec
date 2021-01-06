package com.lacuevadelbot.binary2dec.binary2dec.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.lacuevadelbot.binary2dec.R;

public class DecimalActivity extends AppCompatActivity {

    TextInputEditText mTextInputDecimalNumber;
    TextView mTextViewBinaryNumber;
    Button mButtonConvert;
    TextView mTextViewHistory;

    String mResult = "0";
    Integer mDecimalNumber = 0;

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decimal);

        mTextInputDecimalNumber = findViewById(R.id.textInputDecimal);
        mTextViewBinaryNumber = findViewById(R.id.textViewBinary);
        mTextViewHistory = findViewById(R.id.textViewHistory);
        mTextViewHistory.setMovementMethod(new ScrollingMovementMethod());

        mButtonConvert = findViewById(R.id.btnConvert);
        mButtonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConvertDecToBin();
            }
        });

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    private void ConvertDecToBin(){
        if(!mTextInputDecimalNumber.getText().toString().isEmpty()){
            mDecimalNumber = Integer.parseInt(mTextInputDecimalNumber.getText().toString());
            Integer binaryNumber = 0;
            Integer valorCounter = 1;

            do{
                binaryNumber = binaryNumber + ((mDecimalNumber % 2) * valorCounter);
                mDecimalNumber = mDecimalNumber / 2;
                valorCounter = valorCounter * 10;
            }while(mDecimalNumber >= 1);
            binaryNumber = binaryNumber + (mDecimalNumber * valorCounter);
            mResult = binaryNumber.toString();
            mTextViewBinaryNumber.setText(mResult);
            ShowHistory();
        }
        else{
            Toast.makeText(this, "Ingresa un numero decimal para continuar", Toast.LENGTH_SHORT).show();
        }
    }

    private void ShowHistory(){
        if(mTextViewHistory.getText() == "0 = 0"){
            mTextViewHistory.setText(mTextInputDecimalNumber.getText().toString() + " = " + mResult);
        }
        else if (mTextViewHistory.getText() != "0 = 0"){
            mTextViewHistory.append("\n" + mTextInputDecimalNumber.getText().toString() + " = " + mResult);
        }
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if(item.getItemId() == R.id.itemBinary){
                        Intent intent = new Intent(DecimalActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else if(item.getItemId() == R.id.itemDecimal){
                        //openFragment(new DecimalFragment());
                    }
                    return true;
                }
            };
}