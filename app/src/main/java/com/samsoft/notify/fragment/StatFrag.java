package com.samsoft.notify.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samsoft.notify.R;

/**
 * Created by FRIEDRICH M. SAM on 30-Dec-16.
 */

public class StatFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_stats,container,false);
        return v;
    }
}
