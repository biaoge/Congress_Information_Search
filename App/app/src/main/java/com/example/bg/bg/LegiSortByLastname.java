package com.example.bg.bg;

import java.util.Comparator;

/**
 * Created by bbbia on 11/27/2016.
 */

public class LegiSortByLastname implements Comparator<Legislator> {

    @Override
    public int compare(Legislator o1, Legislator o2) {
//        return 0;
        return  o1.getLast_name().compareTo(o2.getLast_name());

    }

}
