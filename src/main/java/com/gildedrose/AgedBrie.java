package com.gildedrose;

public class AgedBrie extends Item {
    public AgedBrie(int sellIn, int quality) {
        super("Aged Brie", sellIn, quality);
    }

    @Override
    void updateQuality() {
        if (quality < 50) {
            quality = quality + 1;
        }
        sellIn = sellIn - 1;
        if (sellIn < 0 && (quality < 50)) {
            quality = quality + 1;

        }
    }
}
