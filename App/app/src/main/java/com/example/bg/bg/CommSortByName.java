package com.example.bg.bg;

import java.util.Comparator;

/**
 * Created by bbbia on 12/1/2016.
 */

public class CommSortByName implements Comparator<Committee> {
    @Override
    public int compare(Committee o1, Committee o2) {
//        return 0;
        return  o1.getName().compareTo(o2.getName());
    }
}
