package com.lab_05_patterns.facade.components;

public class Memory {
    public byte[] read(int address, int length) {
        System.out.println("Memory: reading data...");
        return new byte[] {};
    }

    public boolean write(int address, byte[] data) {
        System.out.println("Memory: writing data...");
        return true;
    }

    public boolean check() {
        return true;
    }
}
