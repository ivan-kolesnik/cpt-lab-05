package com.lab_05_patterns.mediator;

public abstract class Plane {
    protected Dispatcher _dispatcher;
    protected boolean _isDone;

    public Plane(Dispatcher dispatcher) {
        _dispatcher = dispatcher;
    }

    public void requestTakeOff() {
        _isDone = false;
        _dispatcher.requestTakeOff(this);
    }

    public void requestLanding() {
        _isDone = false;
        _dispatcher.requestLanding(this);
    }

    public void onTakeOffAllowed() {
        Thread thead = new Thread(() -> {
            try {
                takeOff();

                _isDone = true;
                _dispatcher.runwayEmptyCb();
            } catch (InterruptedException e) {
                System.out.println("Error while taking off. Thread has been interrupted.");
            }
        });

        thead.start();
    }

    public void onLandingAllowed() {
        Thread thead = new Thread(() -> {
            try {
                land();

                _isDone = true;
                _dispatcher.runwayEmptyCb();
            } catch (InterruptedException e) {
                System.out.println("Error while landing. Thread has been interrupted.");
            }
        });

        thead.start();
    }

    public boolean isDone() {
        return _isDone;
    }

    protected abstract void takeOff() throws InterruptedException;
    protected abstract void land() throws InterruptedException;
}
