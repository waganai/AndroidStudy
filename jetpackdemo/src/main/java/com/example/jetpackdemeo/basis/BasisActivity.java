package com.example.jetpackdemeo.basis;

import android.os.Bundle;
import android.util.ArrayMap;
import android.util.SparseArray;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class BasisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(null, null);
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(null, null);

        SparseArray<String> sparseArray = new SparseArray<>();
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
    }
}
