package com.gildedrose;

import org.approvaltests.Approvals;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("fixme", app.items[0].name);
    }

    /**
     * 검증 항목이 많은 경우 - toString()을 이용한 검증
     */
    @Test
    void updateQuality() {
        GildedRose sut = new GildedRose(new Item[]{new Item("foo", 0, 0)});
        sut.updateQuality();
        // 개별 검증
        assertThat(sut.items[0].name).isEqualTo("foo");
        assertThat(sut.items[0].quality).isZero();
        assertThat(sut.items[0].sellIn).isEqualTo(-1);

        // toString()을 이용한 검증
        assertThat(sut.items[0].toString()).isEqualTo("Item{name='foo', sellIn=-1, quality=0}");
    }

    @Test
    void updateQuality_list_toString() {
        GildedRose sut = new GildedRose(new Item[]{
                new Item("foo", 0, 0),
                new Item("Aged Brie", 0, 0),
                new Item("Backstage passes to a TAFKAL80ETC concert", 0, 0),
                new Item("Sulfuras, Hand of Ragnaros", 0, 0),
        });

        sut.updateQuality();

        // toString()을 이용한 검증
        assertThat(sut.items[0].toString()).isEqualTo("Item{name='foo', sellIn=-1, quality=0}");
        assertThat(sut.items[1].toString()).isEqualTo("Item{name='Aged Brie', sellIn=-1, quality=2}");
        assertThat(sut.items[2].toString()).isEqualTo("Item{name='Backstage passes to a TAFKAL80ETC concert', sellIn=-1, quality=0}");
        assertThat(sut.items[3].toString()).isEqualTo("Item{name='Sulfuras, Hand of Ragnaros', sellIn=0, quality=0}");
    }

    /**
     * approvaltests 를 이용한 검증
     * build.gradle 에 아래의 의존성을 추가해야 합니다.
     * testImplementation 'com.approvaltests:approvaltests:18.5.0'
     * 수행하면 아래와 같은 파일이 생성됩니다.
     * 테스트메소드이름.received.txt
     * 생성된 파일을 다음과 같이 이름을 변경합니다.
     * 테스트메소드이름.approved.txt
     */
    @Test
    void updateQuality_list_approvals_before() {
        GildedRose sut = new GildedRose(new Item[]{
                new Item("foo", 0, 0),
                new Item("Aged Brie", 0, 0),
                new Item("Backstage passes to a TAFKAL80ETC concert", 0, 0),
                new Item("Sulfuras, Hand of Ragnaros", 0, 0),
        });

        sut.updateQuality();

        // approvals 를 이용한 검증
        Approvals.verify(Arrays.asList(sut.items).toString());
    }

    /**
     * approve 를 이용해서 검증
     * Item 에 있는 name, sellIn, quality 를 조합하여 검증하고 싶다.
     */
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
