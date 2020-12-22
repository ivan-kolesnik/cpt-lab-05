package com.lab_05_patterns.facade;

import com.lab_05_patterns.facade.components.*;

public class Computer {
    private final CPU _cpu;
    private final Memory _memory;
    private final HardDrive _hardDrive;

    // OS load settings

    private final int OS_LOAD_HD_READ_ADDRESS   = 0x0010;
    private final int OS_LOAD_HD_READ_LENGTH    = 1024;

    private final int OS_LOAD_MEM_WRITE_ADDRESS = 0x0000;

    private final int OS_SD_MEM_READ_ADDRESS    = 0x7649;
    private final int OS_SD_MEM_READ_LENGTH     = 128;

    private final int OS_SD_HD_WRITE_ADDRESS    = 0x0200;

    // Program1 load settings

    private final int PROG1_LOAD_HD_READ_ADDRESS   = 0x6000;
    private final int PROG1_LOAD_HD_READ_LENGTH    = 512;

    private final int PROG1_LOAD_MEM_WRITE_ADDRESS = 0x9240;

    private final int PROG1_SD_MEM_READ_ADDRESS    = 0xa500;
    private final int PROG1_SD_MEM_READ_LENGTH     = 256;

    private final int PROG1_SD_HD_WRITE_ADDRESS    = 0x6100;

    // helpers

    private boolean _isProgram1Open;

    public Computer() {
        _cpu = new CPU();
        _memory = new Memory();
        _hardDrive = new HardDrive();
    }

    public void turnOn() {
        System.out.println("\nChecking components status...");

        if (!isComponentsOK()) {
            System.out.println("Components damaged!");
            return;
        }

        System.out.println("Done.");

        startOS();
    }

    public void turnOff() {
        closeProgram1();
        shutDownOS();
    }

    public void openProgram1() {
        System.out.println("\nOpening program 1...");

        byte[] data = _hardDrive.read(PROG1_LOAD_HD_READ_ADDRESS, PROG1_LOAD_HD_READ_LENGTH);
        _cpu.exec(3, data);
        _cpu.exec(4, data);
        _memory.write(PROG1_LOAD_MEM_WRITE_ADDRESS, data);

        _isProgram1Open = true;

        System.out.println("\nDone.");
    }

    public void closeProgram1() {
        if (!_isProgram1Open) {
            return;
        }

        System.out.println("\nClosing program 1...");

        byte[] data = _memory.read(PROG1_SD_MEM_READ_ADDRESS, PROG1_SD_MEM_READ_LENGTH);
        _cpu.exec(5, data);
        _cpu.exec(6, data);
        _hardDrive.write(PROG1_SD_HD_WRITE_ADDRESS, data);

        System.out.println("\nDone.");
    }

    private void startOS() {
        System.out.println("\nStarting OS...");

        byte[] data = _hardDrive.read(OS_LOAD_HD_READ_ADDRESS, OS_LOAD_HD_READ_LENGTH);
        _cpu.exec(5, data);
        _cpu.exec(6, data);
        _memory.write(OS_LOAD_MEM_WRITE_ADDRESS, data);

        System.out.println("\nDone.");
    }

    private void shutDownOS() {
        System.out.println("\nShutting down OS...");

        byte[] data = _memory.read(OS_SD_MEM_READ_ADDRESS, OS_SD_MEM_READ_LENGTH);
        _cpu.exec(1, data);
        _cpu.exec(2, data);
        _hardDrive.write(OS_SD_HD_WRITE_ADDRESS, data);

        System.out.println("\nDone.");
    }

    private boolean isComponentsOK() {
        boolean isCPUOk = _cpu.check();
        boolean isMemoryOk = _memory.check();
        boolean isHardDriveOk = _hardDrive.check();

        return isCPUOk && isMemoryOk && isHardDriveOk;
    }
}
