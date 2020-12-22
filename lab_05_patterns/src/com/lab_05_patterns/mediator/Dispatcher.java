package com.lab_05_patterns.mediator;

import java.util.LinkedList;
import java.util.Queue;

public class Dispatcher {
    private final Queue<Plane> _requestedTakeOffPlanes;
    private final Queue<Plane> _requestedLandingPlanes;

    private Plane _currentPlane;

    public Dispatcher() {
        _requestedTakeOffPlanes = new LinkedList<>();
        _requestedLandingPlanes = new LinkedList<>();
    }

    public synchronized void requestTakeOff(Plane plane) {
        if (_currentPlane == plane) {
            return;
        }

        if (_currentPlane == null) {
            _currentPlane = plane;
            _currentPlane.onTakeOffAllowed();

            return;
        }

        if (!_requestedTakeOffPlanes.contains(plane)) {
            _requestedTakeOffPlanes.offer(plane);
        }
    }

    public synchronized void requestLanding(Plane plane) {
        if (_currentPlane == plane) {
            return;
        }

        if (_currentPlane == null) {
            _currentPlane = plane;
            _currentPlane.onLandingAllowed();

            return;
        }

        if (!_requestedLandingPlanes.contains(plane)) {
            _requestedLandingPlanes.offer(plane);
        }
    }

    public synchronized void runwayEmptyCb() {
        _currentPlane = null;

        if (!_requestedLandingPlanes.isEmpty()) {
            _currentPlane = _requestedLandingPlanes.poll();
            _currentPlane.onLandingAllowed();

            return;
        }

        if (!_requestedTakeOffPlanes.isEmpty()) {
            _currentPlane = _requestedTakeOffPlanes.poll();
            _currentPlane.onTakeOffAllowed();
        }
    }
}
