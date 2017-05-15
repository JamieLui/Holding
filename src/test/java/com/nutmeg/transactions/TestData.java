package com.nutmeg.transactions;


import com.nutmeg.transactions.holding.Holding;

import java.util.*;

public class TestData {

    public static Map<String, List<Holding>> setUpCashHolding() {
        Map<String, List<Holding>> holdingAccounts = new HashMap<String, List<Holding>>();
        List<Holding> holdingList = new ArrayList<Holding>();
        Holding holding = new Holding();
        holding.setAsset("CASH");
        holding.setHoldings(100);
        holdingList.add(holding);
        holdingAccounts.put("NEAA0000", holdingList);
        return holdingAccounts;
    }

    public static  Map<String, List<Holding>> setUpCashHoldingWithVuke() {
        Map<String, List<Holding>> holdingAccounts = new HashMap<String, List<Holding>>();
        List<Holding> holdingList = new ArrayList<Holding>();
        Holding holdingCash = new Holding();
        holdingCash.setAsset("CASH");
        holdingCash.setHoldings(100);
        Holding holdingVuke = new Holding();
        holdingVuke.setAsset("VUKE");
        holdingVuke.setHoldings(20);
        holdingList.add(holdingCash);
        holdingList.add(holdingVuke);
        holdingAccounts.put("NEAA0000", holdingList);
        return holdingAccounts;
    }
}
