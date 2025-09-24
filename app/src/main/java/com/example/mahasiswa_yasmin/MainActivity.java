package com.example.mahasiswa_yasmin;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.example.mahasiswa_yasmin.db.DbHelper;
import com.example.mahasiswa_yasmin.model.Mahasiswa;

public class MainActivity extends AppCompatActivity {
    private DbHelper dbHelper;
    private EditText etName, etNim;
    private Button btnSave, btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);
        etName = findViewById(R.id.edt_name);
        etNim = findViewById(R.id.edt_nim);
        btnSave = findViewById(R.id.btn_submit);
        btnList = findViewById(R.id.btn_list);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNim.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Error: Nim harus diisi!", Toast.LENGTH_SHORT).show();
                } else if (etName.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Error: Nama harus diisi!", Toast.LENGTH_SHORT).show();
                } else {
                    Mahasiswa mhs = new Mahasiswa();
                    mhs.setNim(etNim.getText().toString());
                    mhs.setName(etName.getText().toString());
                    dbHelper.insertMahasiswa(mhs);

                    etName.setText("");
                    etNim.setText("");
                    Toast.makeText(MainActivity.this, "Simpan berhasil!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListMahasiswaActivity.class);
            startActivity(intent);
        });
    }
}
