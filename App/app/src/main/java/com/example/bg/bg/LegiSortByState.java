package com.example.bg.bg;

import java.util.Comparator;

/**
 * Created by bbbia on 11/27/2016.
 */

public class LegiSortByState implements Comparator<Legislator> {

    @Override
    public int compare(Legislator o1, Legislator o2) {
//        return 0;
        int dif = o1.getState().compareTo(o2.getState());
        if (dif==0)
        {
            dif = o1.getLast_name().compareTo(o2.getLast_name());
            return  dif;
        }
        else
            return dif;

    }
}
