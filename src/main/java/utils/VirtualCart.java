package utils;

import java.util.Map;
import java.util.TreeMap;

public class VirtualCart {
    private static Map<String, Double> purchase;

    public static void addPurchase(String name, Double price) {
        getPurchase();
        purchase.put(name, price);
    }

    private VirtualCart() {
    }

    public static Map<String, Double> getPurchase() {
        if (purchase == null){
            purchase = new TreeMap<>();
        }
        return purchase;
    }



    public Double getPurchasePrice(String name) {
        return purchase.get(name);
    }

    public void removePurchase(String name) {
        purchase.remove(name);
    }

}