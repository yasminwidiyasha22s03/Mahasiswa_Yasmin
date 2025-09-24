package com.example.mahasiswa_yasmin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mahasiswa_yasmin.R;
import com.example.mahasiswa_yasmin.UpdateActivity;
import com.example.mahasiswa_yasmin.model.Mahasiswa;

import java.util.ArrayList;
public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Mahasiswa> listMahasiswa;

    public MahasiswaAdapter(Context context) {
        this.context = context;
        this.listMahasiswa = new ArrayList<>();
    }

    public void setListStudents(ArrayList<Mahasiswa> list) {
        this.listMahasiswa = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MahasiswaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mahasiswa, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaAdapter.ViewHolder holder, int position) {
        Mahasiswa mhs = listMahasiswa.get(position);
        holder.tvName.setText(mhs.getName());
        holder.tvNim.setText(mhs.getNim());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("user", mhs);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listMahasiswa.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvNim;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvNim = itemView.findViewById(R.id.tv_nim);
        }
    }
}