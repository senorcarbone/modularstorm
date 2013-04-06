package se.sics.kompics.storm.modules;

import se.sics.kompics.Event;


public class Eurika extends Event {
    private String message;

    public Eurika(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
