package com.example.lethanhky_16011051;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtTen, edtMH, edtTL, edtID;
    Button btnThem, btnSua;
    ArrayAdapter adapter;
    ListView lvSV;
    ArrayList<SV> lstSV;
    ArrayList<String> lstTenSV;
    DBManageSV svDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        svDB = new DBManageSV(this);
        edtTen = (EditText)findViewById(R.id.edtTen);
        edtMH = (EditText)findViewById(R.id.edtMH);
        btnThem = (Button)findViewById(R.id.btnThem);
        edtTL = (EditText)findViewById(R.id.edtTL);
        lvSV = (ListView)findViewById(R.id.lvSV);
        btnSua = (Button)findViewById(R.id.btnSua);
        edtID = (EditText)findViewById(R.id.edtID);
        svDB.InsertLop(new Lop("DHKTPM12A"));
        svDB.InsertLop(new Lop("DHKTPM12ATT"));
        svDB.InsertSV(new SV("Nhu", "DHKTPM12A", "Android"));
        svDB.InsertSV(new SV("Ky", "DHKTPM12A", "Android"));
        lstTenSV = svDB.getAllTen();
        lstSV = svDB.getAll();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lstTenSV);
        lvSV.setAdapter(adapter);
//
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SV sv = new SV();
                sv.setName(edtTen.getText().toString());
                sv.setClass_name(edtTL.getText().toString());
                sv.setSubject(edtMH.getText().toString());


                    lstTenSV.add(sv.getName());
                    adapter.notifyDataSetChanged();
                    svDB.InsertSV( sv);
                    Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT).show();

            }
        });

        lvSV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xóa");
                builder.setMessage("Bạn có xóa không?");
                builder.setCancelable(false);
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        lstTenSV.remove(position);
                        adapter.notifyDataSetChanged();
                        svDB.deleteSV( lstSV.get(position).getId());
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return false;
            }
        });
        lvSV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                edtID.setText(lstSV.get(i).getId()+"");
                edtTen.setText(lstSV.get(i).getName());
                edtMH.setText(lstSV.get(i).getSubject());
                edtTL.setText(lstSV.get(i).getClass_name());
            }
        });

btnSua.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

            svDB.updateSV(Integer.parseInt(edtID.getText().toString()), new SV(edtTen.getText().toString(),edtTL.getText().toString(), edtMH.getText().toString()));
        Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_SHORT).show();
    }
});
    }
}
