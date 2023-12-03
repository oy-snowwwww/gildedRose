package com.gildedrose;

import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class GildedRoseTest {

    @Test
    void updateQuality_list_approvals_after() {
        String[] name = {"foo", "Aged Brie", "Backstage passes to a TAFKAL80ETC concert", "Sulfuras, Hand of Ragnaros"};
        Integer[] sellIn = {-1, 0, 1, 6, 11};
        Integer[] quality = {0, 1, 49, 50};

        CombinationApprovals.verifyAllCombinations(this::doUpdateQuality, name, sellIn, quality);
    }

    private String doUpdateQuality(String name, int sellIn, int quality) {
        GildedRose sut = new GildedRose(new Item[]{Item.of(name, sellIn, quality),});
        sut.updateQuality();
        String response = Arrays.asList(sut.items).toString();
        return response;
    }

}
