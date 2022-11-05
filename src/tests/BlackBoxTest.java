package tests;

import mainClasses.BlackBox;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.TreeMap;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BlackBoxTest {

    @org.junit.Test
    @DisplayName("Test 1")
    public void add() {
        BlackBox box = new BlackBox();
        box.add(5);
        TreeMap <Integer, Integer> boxTreeMap = box.getIntegerTreeMap();
        TreeMap<Integer, Integer> rightTreeMap = new TreeMap<>();
        rightTreeMap.put(1, 5);

        BlackBox box1 = new BlackBox();
        int[] mas = new int[]{1, 2, 3};
        box1.add(mas);
        TreeMap<Integer, Integer> boxTreeMap1 = box1.getIntegerTreeMap();
        TreeMap<Integer, Integer> rightTreeMap1 = new TreeMap<>();
        rightTreeMap1.put(1, 1);
        rightTreeMap1.put(2, 2);
        rightTreeMap1.put(3, 3);

        assertAll(() -> assertEquals(rightTreeMap, boxTreeMap),
                () -> assertEquals(rightTreeMap1, boxTreeMap1));
    }
}