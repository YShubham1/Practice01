package com.example.storingdataindatabase;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    EditText na,fa,el;
    RadioGroup rg;
    RadioButton rb;
    ImageView tl;
    CheckBox c1,c2;
    Button in,dl,dly;
    String selectedTime,gender;
    Datastored ds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        na=findViewById(R.id.name);
        fa=findViewById(R.id.father);
        el=findViewById(R.id.m);
        rg=findViewById(R.id.rg);
        tl=findViewById(R.id.t);
        c1=findViewById(R.id.cb1);
        c2=findViewById(R.id.cb2);
        in=findViewById(R.id.in);
        dl=findViewById(R.id.dl);
        dly=findViewById(R.id.ds);

        ds=new Datastored(this,"Details",null,1);
        tl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cd=Calendar.getInstance();
                int hour=cd.get(Calendar.HOUR);
                int minute=cd.get(Calendar.MINUTE);
                TimePickerDialog d=new TimePickerDialog(MainActivity.this, android.R.style.Theme_DeviceDefault_DialogWhenLarge, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedTime=hourOfDay+":"+minute;
                    }
                },hour,minute,false);
                d.show();
            }
        });
        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=na.getText().toString();
                String f=fa.getText().toString();
                String e=el.getText().toString();
                rb=findViewById(rg.getCheckedRadioButtonId());
                gender=rb.getText().toString();
                StringBuilder str=new StringBuilder("Selected Opt : ");
                if(c1.isChecked())
                {
                    str.append("Home");
                }
                if(c2.isChecked())
                {
                    str.append("\tOffice");
                }
                String opt= String.valueOf(str);
                ds.insertRecord(n,f,e,gender,selectedTime,opt);
                Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
            }
        });
        dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=na.getText().toString();
                ds.deleteData(n);
                Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        dly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb=findViewById(rg.getCheckedRadioButtonId());
                gender=rb.getText().toString();
                String det=ds.displayData(gender);
                Toast.makeText(MainActivity.this, "Displaying Data", Toast.LENGTH_SHORT).show();
                Intent it=new Intent(MainActivity.this, resultActivity.class);
                it.putExtra("Details",det);
                startActivity(it);
            }
        });

    }
}