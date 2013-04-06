package se.sics.kompics.storm.bolt.evt;

import backtype.storm.tuple.Tuple;
import se.sics.kompics.Event;

/**
 * @author carbone
 */
public class STRM_FAIL extends Event {

    private final Tuple tuple;

    public STRM_FAIL(Tuple tuple) {
        this.tuple = tuple;
    }

    public Tuple getTuple() {
        return tuple;
    }

}
