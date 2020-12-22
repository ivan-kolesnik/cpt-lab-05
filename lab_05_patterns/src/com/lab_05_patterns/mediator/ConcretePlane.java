package com.lab_05_patterns.mediator;

public class ConcretePlane extends Plane {
    private final InterruptableRunnable _takeOffCb;
    private final InterruptableRunnable _landCb;

    public ConcretePlane(Dispatcher dispatcher, InterruptableRunnable takeOffCb, InterruptableRunnable landCb) {
        super(dispatcher);

        _takeOffCb = takeOffCb;
        _landCb = landCb;
    }

    @Override
    protected void takeOff() throws InterruptedException {
        _takeOffCb.run();
    }

    @Override
    protected void land() throws InterruptedException {
        _landCb.run();
    }
}
