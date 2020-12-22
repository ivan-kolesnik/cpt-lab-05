package com.lab_05_patterns;

import com.lab_05_patterns.facade.Computer;
import com.lab_05_patterns.mediator.ConcretePlane;
import com.lab_05_patterns.mediator.Dispatcher;
import com.lab_05_patterns.mediator.InterruptableRunnable;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n--- Facade Example ---");
        facadeExample();

        System.out.println("\n--- Mediator Example ---\n");
        mediatorExample();
    }

    private static void facadeExample() {
        Computer computer = new Computer();

        computer.turnOn();

        computer.openProgram1();
        computer.closeProgram1();

        computer.turnOff();
    }

    private static void mediatorExample() {
        List<Thread> planeThreads = new ArrayList<>();
        Dispatcher dispatcher = new Dispatcher();

        final int takeOffDuration = 2000;
        final int landingDuration = 2000;

        for (int i = 0; i < 5; i += 1) {

            final int planeIndex = i;
            int runDelay = getRandomIntFromRange(0, 10000);

            String planeIdStr = "Plane (" + planeIndex + ") ";

            System.out.println(planeIdStr + "op delay: " + runDelay);

            InterruptableRunnable takeOffCb = () -> {
                Thread.sleep(takeOffDuration);
                System.out.println(planeIdStr + "was taken off.");
            };

            InterruptableRunnable landCb = () -> {
                Thread.sleep(landingDuration);
                System.out.println(planeIdStr + "was landed.");
            };

            ConcretePlane plane = new ConcretePlane(dispatcher, takeOffCb, landCb);

            final boolean isTakingOff = Math.random() > 0.5;

            Runnable threadCb = () -> {
                try {
                    Thread.sleep(runDelay);
                    String str = planeIdStr;

                    if (isTakingOff) {
                        plane.requestTakeOff();
                        str += "requested takeoff.";
                    } else {
                        plane.requestLanding();
                        str += "requested landing.";
                    }

                    System.out.println(str);
                } catch (InterruptedException e) {
                    System.out.println("Thread has been interrupted.");
                }
            };

            Thread thread = new Thread(threadCb);
            thread.start();

            planeThreads.add(thread);
        }

        System.out.println();
    }

    private static int getRandomIntFromRange(int min, int max) {
        int range = max - min + 1;
        return (int) (Math.random() * range) + min;
    }
}
