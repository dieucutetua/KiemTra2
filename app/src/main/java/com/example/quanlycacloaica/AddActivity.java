
package com.example.quanlycacloaica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {

    private EditText edtTenKH, edtTenThuong, editDacTinh,edtMauSac;
    private Button btnThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenKH = edtTenKH.getText().toString();
                String tenthuong = edtTenThuong.getText().toString();
                String dactinh = editDacTinh.getText().toString();
                String mausac = edtMauSac.getText().toString();

                LoaiCa loaiCa =new LoaiCa(tenKH,tenthuong,dactinh,mausac);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("QuanLyCacLoaiCa");
                String id = myRef.push().getKey();
                myRef.child(id).setValue(loaiCa).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(),"Thêm thành công  !",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Thêm thất bại !",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    private void addControls() {
        edtTenKH = findViewById(R.id.edtTenKH);
        edtTenThuong = findViewById(R.id.edtTenThuong);
        editDacTinh = findViewById(R.id.edtDacTinh);
        edtMauSac = findViewById(R.id.edtMauSac);

        btnThem = findViewById(R.id.buttonAdd);
    }
}