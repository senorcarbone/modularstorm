package se.sics.kompics.storm.evt;

import backtype.storm.tuple.Tuple;
import se.sics.kompics.Event;

/**
 * @author carbone
 */
public class STRM_ACK extends Event {

    private final Tuple tuple;

    public STRM_ACK(Tuple tuple) {
        this.tuple = tuple;
    }

    public Tuple getTuple() {
        return tuple;
    }

}
