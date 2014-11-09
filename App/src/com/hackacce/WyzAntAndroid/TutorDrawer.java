package com.hackacce.WyzAntAndroid;

import android.content.Context;

import java.util.ArrayList;
//import java.util.UUID;

public class TutorDrawer {
    private ArrayList<Tutor> mTutors;

    private static TutorDrawer sTutorDrawer;
    private Context mAppContext;

    private TutorDrawer(Context appContext) {
        mAppContext = appContext;
        mTutors = new ArrayList<>();

    }

    public static TutorDrawer get(Context t) {
        if (sTutorDrawer == null)
            sTutorDrawer = new TutorDrawer(t.getApplicationContext());
        return sTutorDrawer;
    }

    public ArrayList<Tutor> getTutors() {
        return mTutors;
    }

/*    public Tutor getCrime(UUID id) {
        for (Tutor t: mTutors)
            if (t.getId().equals(id))
                return t;
        return null;
    }*/
}
