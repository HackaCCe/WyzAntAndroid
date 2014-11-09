package com.hackacce.WyzAntAndroid;

import android.content.Context;
import android.util.Log;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class TutorDrawer {
    private static final String TAG = "TutorDrawer";
    private ArrayList<Tutor> mTutors;

    private static TutorDrawer sTutorDrawer;
    private Context mAppContext;

    private TutorDrawer(Context appContext) {
        mAppContext = appContext;
        mTutors = TutorListActivity.getTutors();

    }

    public static TutorDrawer get(Context t) {
        if (sTutorDrawer == null)
            sTutorDrawer = new TutorDrawer(t.getApplicationContext());
        return sTutorDrawer;
    }

    public ArrayList<Tutor> getTutors() {
        return mTutors;
    }

}
