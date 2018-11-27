package ru.dserov.delta;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DiffUtilsTest {
    private static List<Byte> a;
    private static List<Byte> b;
    private static List<Byte> c;

    @BeforeClass
    public static void setupSampleLists() {
        byte[] aBytes = "a".getBytes();
        a = new ArrayList<>();
        for (byte i : aBytes) {
            a.add(i);
        }

        byte[] bBytes = "b".getBytes();
        b = new ArrayList<>();
        for (byte i : bBytes) {
            b.add(i);
        }

        byte[] cBytes = "c".getBytes();
        c = new ArrayList<>();
        for (byte i : cBytes) {
            c.add(i);
        }
    }

    @Test
    public void deltaSimpleIntersectionTest() {
        var actual = DiffUtils.delta(List.of(a, b), List.of(a, c));
        var expected = new ListPair<>(List.of(b), List.of(c));
        assertEquals(expected, actual);
    }

    @Test
    public void deltaRepeatingElementTest() {
        var actual = DiffUtils.delta(List.of(a), List.of(a, a, a, a));
        var expected = new ListPair<>(List.of(), List.of(a, a, a));
        assertEquals(expected, actual);
    }

    @Test
    public void deltaInsertNothingTest() {
        var actual = DiffUtils.delta(List.of(a, b), List.of(a, b, c));
        var expected = new ListPair<>(List.of(), List.of(c));
        assertEquals(expected, actual);
    }

    @Test
    public void deltaRemoveNothingTest() {
        var actual = DiffUtils.delta(List.of(a, b, c), List.of(a, b));
        var expected = new ListPair<>(List.of(c), List.of());
        assertEquals(expected, actual);
    }

    @Test
    public void deltaNoChangesTest() {
        var actual = DiffUtils.delta(List.of(a, b, c), List.of(a, b, c));
        var expected = new ListPair<>(List.of(), List.of());
        assertEquals(expected, actual);
    }

    @Test
    public void deltaEmptyTest() {
        var actual = DiffUtils.delta(List.of(), List.of());
        var expected = new ListPair<>(List.of(), List.of());
        assertEquals(expected, actual);
    }

    @Test
    public void deltaNullParameterTest() {
        var actual = DiffUtils.delta(null, List.of(a));
        assertNull(actual);
    }
}