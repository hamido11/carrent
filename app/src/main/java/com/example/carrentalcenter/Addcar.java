package com.example.carrentalcenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;


public class Addcar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcar);
        final EditText name =(EditText)findViewById(R.id.editText);
        final EditText id =(EditText)findViewById(R.id.editText2);
        final EditText model=(EditText)findViewById(R.id.editText1);
        final CheckBox status=(CheckBox)findViewById(R.id.checkBox);
        Button btn_3=(Button)findViewById(R.id.btn_3);
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Car car=new Car();
                car.setName(name.getText().toString());
                car.setModel(model.getText().toString());
                car.setId(id.getText().toString());
                car.setStatus(status.isChecked());
                Realm realm=Realm.getInstance(getApplicationContext());
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(car);
                realm.commitTransaction();
                Toast.makeText(Addcar.this,"The car has been added",Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}
