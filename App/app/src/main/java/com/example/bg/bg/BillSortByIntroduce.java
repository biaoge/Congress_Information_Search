package com.example.bg.bg;

import java.util.Comparator;

/**
 * Created by bbbia on 11/30/2016.
 */

public class BillSortByIntroduce implements Comparator<Bill> {
    @Override
    public int compare(Bill o1, Bill o2) {
        return o2.getIntroduced_on().compareTo(o1.getIntroduced_on());
//        return 0;
    }
}
