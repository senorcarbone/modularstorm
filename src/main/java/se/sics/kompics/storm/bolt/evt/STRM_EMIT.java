package se.sics.kompics.storm.bolt.evt;


import backtype.storm.tuple.Tuple;
import com.google.common.collect.ImmutableList;
import se.sics.kompics.Event;

import java.util.List;

/**
 * @author carbone
 */
public class STRM_EMIT extends Event {

    private final String streamID;
    private final List<Tuple> anchors;
    private final List<Object> tuple;

    public STRM_EMIT(String streamID, List<Tuple> anchors, List<Object> tuple) {
        this.streamID = streamID;
        this.anchors = anchors;
        this.tuple = tuple;
    }

    public STRM_EMIT(List<Tuple> anchors, List<Object> tuple) {
        this.streamID = null;
        this.anchors = anchors;
        this.tuple = tuple;
    }

    public STRM_EMIT(Tuple tuple, List<Object> values) {
        this.streamID = null;
        this.anchors = ImmutableList.of(tuple);
        this.tuple = values;
    }

    public String getStreamID() {
        return streamID;
    }

    public List<Tuple> getAnchors() {
        return anchors;
    }

    public List<Object> getTuple() {
        return tuple;
    }

}
