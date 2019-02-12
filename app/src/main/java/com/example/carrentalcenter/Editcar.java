package com.example.carrentalcenter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.List;

import io.realm.Realm;

public class Editcar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editcar);
        final EditText id=(EditText)findViewById(R.id.editText2);
        final EditText name=(EditText)findViewById(R.id.editText);
        final EditText date=(EditText)findViewById(R.id.editText1);
       final CheckBox statu=(CheckBox)findViewById(R.id.checkBox);
        int position=getIntent().getIntExtra("car",0);
        final Realm realm=Realm.getInstance(getApplicationContext());
        List<Car>cars=realm.allObjects(Car.class);
        final Car car=cars.get(position);

       name.setText(car.getName());
       id.setText(car.getId());
       date.setText(car.getModel());
       statu.setChecked(car.isStatus());
        Button delete=(Button)findViewById(R.id.button3);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Editcar.this)
                .setTitle("Attention")
                 .setMessage("Are you sure to delete the car")
                  .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          realm.beginTransaction();
                          car.removeFromRealm();
                          realm.commitTransaction();
                          finish();

                      }
                  }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();





            }
        });
        Button edit=(Button)findViewById(R.id.button2);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Editcar.this)
                        .setTitle("Attention")
                        .setMessage("Are you sure to modify the car info")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                realm.beginTransaction();
                                car.setName(name.getText().toString());
                                car.setModel(date.getText().toString());
                                car.setId(id.getText().toString());
                                car.setStatus(statu.isChecked());
                                realm.copyToRealmOrUpdate(car);
                                realm.commitTransaction();

                                finish();

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();





            }
        });
    }
}
