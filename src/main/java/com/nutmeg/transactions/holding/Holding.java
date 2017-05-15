package com.nutmeg.transactions.holding;

import java.io.Serializable;

public class Holding implements Serializable {

    private String asset;

    private double holding;

    public String getAsset() {
        return asset;
    }

    public void setAsset(final String asset) {
        this.asset = asset;
    }

    public double getHolding() {
        return holding;
    }

    public void setHoldings(final double holding) {
        this.holding = holding;
    }

    public String toString() {
        return getAsset() + ":\t" + getHolding();
    }
}