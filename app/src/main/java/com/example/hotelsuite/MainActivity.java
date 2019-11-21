package com.example.hotelsuite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView checkin,checkout;
    Boolean a;
    Button calc,rooms,adult,child,reset;
    Spinner location,roomt;
    private int yearin,yearout,monthin,monthout,dayin,dayout,roomno=0,noofdays=0,adultno=0,childno=0,totalpeople=0;
    TextView reportlocation,reportrooms,reportnorooms,reportnoofdays,reportvat,reportsc,reporttotal,reportpeople;
    float priceofdeluxe=2000,priceofpremium=3000,priceofplatinum=4000,vat=0,sc=0,total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkin=findViewById(R.id.checkin);
        checkout=findViewById(R.id.checkout);
        location=findViewById(R.id.location);
        roomt=findViewById(R.id.roomtype);
        rooms=findViewById(R.id.rooms);
        adult=findViewById(R.id.adults);
        child=findViewById(R.id.childrens);
        reset=findViewById(R.id.Reset);
        reportlocation=findViewById(R.id.reportlocation);
        reportrooms=findViewById(R.id.reportroomtype);
        reportnorooms=findViewById(R.id.reportrooms);
        reportnoofdays=findViewById(R.id.reportdays);
        reportvat=findViewById(R.id.reportvat);
        reportsc=findViewById(R.id.reportsc);
        reporttotal=findViewById(R.id.reportprice);
        reportpeople=findViewById(R.id.reportpeople);
        calc=findViewById(R.id.calc);
        final String locations[]={"Kathmandu","Lalitpur","Chitwan","Bhaktapur","Morang"};
        final ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,locations);
        location.setAdapter(adapter);
        final String roomtype[]={"Deluxe","Premium","Platinum"};
        final ArrayAdapter adapter1=new ArrayAdapter(this,android.R.layout.simple_list_item_1,roomtype);
        roomt.setAdapter(adapter1);
rooms.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        roomno=roomno+1;
        rooms.setText(""+roomno);
    }
});
reset.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        roomno=adultno=childno=0;
        rooms.setText(""+roomno);
        adult.setText(""+adultno);
        child.setText(""+childno);
        defaultReport();

    }
});
adult.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        adultno=adultno+1;
        adult.setText(""+adultno);
    }
});
child.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        childno=childno+1;
        child.setText(""+childno);
    }
});
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loc=location.getSelectedItem().toString();
                String rooms=roomt.getSelectedItem().toString();
                reportlocation.setText(loc);
                reportrooms.setText(rooms);

                reportnorooms.setText(""+roomno);
                noofdays=(((yearout-yearin)*365))+((monthout-monthin)*30)+(dayout-dayin);
                reportnoofdays.setText(""+noofdays);
                if(rooms=="Premium"){vat=(roomno*priceofpremium)*13/100;}
                else if(rooms=="Deluxe"){vat=(roomno*priceofdeluxe)*13/100;}
                else if(rooms=="Platinum"){vat=(roomno*priceofplatinum)*13/100;}
                reportvat.setText(""+vat);
                if(rooms=="Premium"){sc=(roomno*priceofpremium)*10/100;}
                else if(rooms=="Deluxe"){sc=(roomno*priceofdeluxe)*10/100;}
                else if(rooms=="Platinum"){sc=(roomno*priceofplatinum)*10/100;}
                reportsc.setText(""+sc);
                if(rooms=="Premium"){total=(roomno*priceofpremium)+vat+sc;}
                else if(rooms=="Deluxe"){total=(roomno*priceofdeluxe)+vat+sc;}
                else if(rooms=="Platinum"){total=(roomno*priceofplatinum)+vat+sc;}
                reporttotal.setText(""+total);
                totalpeople=childno+adultno;
                reportpeople.setText(""+totalpeople);



if(yearin>yearout) {
    Toast.makeText(getApplicationContext(), "Checked In-Out year error " + yearin +">"+ yearout, Toast.LENGTH_LONG).show();
    defaultReport();
}

else if(yearin<=yearout && monthin>monthout){

    Toast.makeText(getApplicationContext(), "Checked In-Out month error " + monthin +">"+ monthout, Toast.LENGTH_LONG).show();
    defaultReport();
}else if(yearin==yearout && monthin==monthout && dayin>=dayout){
    Toast.makeText(getApplicationContext(), "Checked In-Out day error" , Toast.LENGTH_LONG).show();
    defaultReport();
}




            }
        });

        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDatePicker();

                a=true;
            }
        });
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDatePicker();

                a=false;
            }
        });

    }


    private void ShowDatePicker(){
      final Calendar calendar=Calendar.getInstance();

      int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);

        int day=calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog=new DatePickerDialog(this,this,year,month,day);
datePickerDialog.show();
    };
    public void defaultReport(){
        reportnoofdays.setText("");
        reportnorooms.setText("");
        reportrooms.setText("");
        reportvat.setText("");
        reportsc.setText("");
        reportpeople.setText("");
        reporttotal.setText("");
        reportlocation.setText("");

    };

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        String date=year+"/"+(++month)+"/"+dayOfMonth ;
if(a==true){
    checkin.setText(date);
    yearin=year;
    monthin=month;
    dayin=dayOfMonth;


}else if(a==false){checkout.setText(date);
yearout=year;
monthout=month;
dayout=dayOfMonth;}
    }
}
