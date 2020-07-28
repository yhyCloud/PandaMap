package com.example.pandamap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;

import static com.example.pandamap.R.id.Mapview_location;
import static com.example.pandamap.R.id.normal;

public class FirstFragment extends Fragment {
    private BaiduMap mBaiduMap;
    private MapView mMapView_location;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fg1, container, false);
        SDKInitializer.initialize(getActivity().getApplicationContext());



        return view;
    }
}
