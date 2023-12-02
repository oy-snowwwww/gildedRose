package com.gildedrose;

import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class GildedRoseTest {

    @Test
    void updateQuality_list_approvals_after() {
        String[] name = {"foo"};
        Integer[] sellIn = {0};
        Integer[] quality = {0};

        CombinationApprovals.verifyAllCombinations(this::doUpdateQuality, name, sellIn, quality);
    }

    private String doUpdateQuality(String name, int sellIn, int quality) {
        GildedRose sut = new GildedRose(new Item[]{new Item(name, sellIn, quality),});
        sut.updateQuality();
        String response = Arrays.asList(sut.items).toString();
        return response;
    }

}
