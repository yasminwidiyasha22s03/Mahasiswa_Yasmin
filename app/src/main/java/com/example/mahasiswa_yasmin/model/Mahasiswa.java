package com.example.mahasiswa_yasmin.model;

import java.io.Serializable;

public class Mahasiswa implements Serializable {
    private int id;
    private String nim;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
