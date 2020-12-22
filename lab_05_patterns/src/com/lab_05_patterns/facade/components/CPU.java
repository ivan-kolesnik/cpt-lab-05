package com.lab_05_patterns.facade.components;

public class CPU {
    public void exec(int command, byte[] data) {
        System.out.println("CPU: exec command");
    }

    public boolean check() {
        return true;
    }
}
