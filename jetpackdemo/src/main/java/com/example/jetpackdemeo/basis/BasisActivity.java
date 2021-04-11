package com.example.jetpackdemeo.basis;

import android.os.Bundle;
import android.util.ArrayMap;
import android.util.SparseArray;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BasisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        SparseArray<String> sparseArray = new SparseArray<>();
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
    }
}
