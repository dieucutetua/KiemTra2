package com.example.quanlycacloaica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvLoaiCa;
    private ArrayList<LoaiCa> loaiCaArrayList;
    private  CaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvLoaiCa = findViewById(R.id.lvLoaiCa);

        loaiCaArrayList = new ArrayList<>();
        getData();
        adapter = new CaAdapter(this,R.layout.item_listview,loaiCaArrayList);
        lvLoaiCa.setAdapter(adapter);

        lvLoaiCa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0){
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, DetailActivity1.class);
                    startActivity(intent);
                } else if (i == 1){
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, DetailActivity2.class);
                    startActivity(intent);
                } else if (i == 2){
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, DetailActivity3.class);
                    startActivity(intent);
                }else if (i == 3){
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, DetailActivity4.class);
                    startActivity(intent);
                }


            }
        });

    }
    private  void getData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("QuanLyCacLoaiCa");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.clear();
                for(DataSnapshot data : snapshot.getChildren()){
                    LoaiCa loaiCa = data.getValue(LoaiCa.class);
                    if(loaiCa != null){
                        loaiCa.setId(data.getKey());
                        adapter.add(loaiCa);
                    }
                }
                Toast.makeText(getApplicationContext(),"Thành công !",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Thất bại !" + error.toString(),Toast.LENGTH_SHORT).show();
                Log.d("MYTAG","onCancelled: " + error.toString());
            }
        });
    }
}