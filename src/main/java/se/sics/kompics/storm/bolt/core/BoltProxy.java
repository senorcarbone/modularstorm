package se.sics.kompics.storm.bolt.core;


import backtype.storm.tuple.Tuple;
import se.sics.kompics.storm.bolt.KompicsBolt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author carbone
 */
public class BoltProxy implements BoltBehavior, BoltComponentBehavior {

    private KompicsBolt bolt;
    private BoltComponent component;
    private List<Tuple> executeBuffer = new ArrayList<Tuple>();

    public BoltProxy(KompicsBolt bolt) {
        this.bolt = bolt;
    }

    @Override
    public void bind(BoltComponent component) {
        this.component = component;
        for (Tuple tuple : executeBuffer) {
            this.component.uponExecute(tuple);
        }
        executeBuffer.clear();
    }

    @Override
    public void emit(String streamID, List<Tuple> anchors, List<Object> tuple) {
        bolt.emit(streamID, anchors, tuple);
    }

    @Override
    public void ack(Tuple tuple) {
        bolt.ack(tuple);
    }

    @Override
    public void fail(Tuple tuple) {
        bolt.fail(tuple);
    }

    @Override
    public void uponExecute(Tuple tuple) {
        if (component == null) {
            executeBuffer.add(tuple);
        } else {
            component.uponExecute(tuple);
        }

    }
}
