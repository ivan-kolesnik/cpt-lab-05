package com.lab_05_patterns.facade.components;

public class HardDrive {
    public byte[] read(int address, int length) {
        System.out.println("Hard drive: reading data...");
        return new byte[] {};
    }

    public boolean write(int address, byte[] data) {
        System.out.println("Hard drive: writing data...");
        return true;
    }

    public boolean check() {
        return true;
    }
}
