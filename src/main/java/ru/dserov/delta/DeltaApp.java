package ru.dserov.delta;

import java.util.ArrayList;
import java.util.List;

public class DeltaApp {
    public static void main(String[] args) {
        byte[] aBytes = "a".getBytes();
        var a = new ArrayList<Byte>();
        for (byte i : aBytes) {
            a.add(i);
        }

        byte[] cBytes = "c".getBytes();
        var c = new ArrayList<Byte>();
        for (byte i : cBytes) {
            c.add(i);
        }

        System.out.println("Insert sequence: (" + a + " " + a + ")");
        System.out.println("Remove sequence: (" + a + " " + c + ")");
        System.out.println("Delta: " + DiffUtils.delta(List.of(a, a), List.of(a, c)));
    }
}
