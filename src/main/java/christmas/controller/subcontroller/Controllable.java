package christmas.controller.subcontroller;

import christmas.controller.status.ApplicationStatus;

@FunctionalInterface
public interface Controllable {
    ApplicationStatus run();
}
