package com.petrodevelopment.copdapp.record.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.petrodevelopment.copdapp.R;
import com.petrodevelopment.copdapp.fragments.SectionFragment;

/**
 * Created by user on 15-05-14.
 */
public class ReminderRecordFragment extends SectionFragment {
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ReminderRecordFragment newInstance(int sectionNumber, String sectionTitle) {
        ReminderRecordFragment fragment = new ReminderRecordFragment();
        SectionFragment.addSectionAndTitleToFragment(fragment, sectionNumber, sectionTitle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_record_reminder, container, false);

        return rootView;
    }
}