package se.sics.kompics.storm.bolt.core;

import backtype.storm.tuple.Tuple;

import java.util.List;

public interface BoltBehavior {
    void emit(String streamID, List<Tuple> anchors, List<Object> tuple);

    void ack(Tuple tuple);

    void fail(Tuple tuple);

    void bind(BoltComponent component);
}
