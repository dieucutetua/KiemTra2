package com.example.quanlycacloaica;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CaAdapter extends ArrayAdapter<LoaiCa> {
    private Activity activity;
    private int resource;
    @NonNull
    private List<LoaiCa> objects;
    public CaAdapter(@NonNull Activity activity, int resource, @NonNull List<LoaiCa> objects) {
        super(activity, resource, objects);
        this.activity = activity;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View view = inflater.inflate(this.resource, null);

        TextView txtTenKH = view.findViewById(R.id.txtTenKH);
        TextView txtTenthuong = view.findViewById(R.id.txtTenThuong);
        TextView txtDactinh = view.findViewById(R.id.txtDacTinh);
        TextView txtMausac = view.findViewById(R.id.txtMauSac);

        LoaiCa loaiCa = this.objects.get(position) ;
        txtTenKH.setText(loaiCa.getTenKH());
        txtTenthuong.setText(loaiCa.getTenThuong());
        txtDactinh.setText(loaiCa.getDacTinh());
        txtMausac.setText(loaiCa.getMauSac());

        ImageView menu = view.findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(activity,view);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem .getItemId() == R.id.item_them){
                            Intent i = new Intent(activity,AddActivity.class);
                            activity.startActivity(i);
                        }
                        else
                        if(menuItem .getItemId() == R.id.item_xoa){
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("QuanLyCacLoaiCa");
                            myRef.child(loaiCa.getId()).removeValue(new DatabaseReference.CompletionListener() {

                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                    Toast.makeText(activity,"Xoa thanh cong !",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        return false;
                    }
                });
                popupMenu.getMenuInflater().inflate(R.menu.list_menu,popupMenu.getMenu());
                popupMenu.show();
            }
        });
        return view;
    }
}
