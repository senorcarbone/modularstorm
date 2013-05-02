package se.sics.kompics.storm.bolt.evt;

import backtype.storm.task.TopologyContext;
import se.sics.kompics.Event;
import se.sics.kompics.Init;

/**
 * @author carbone
 */
public class STRM_INIT extends Event {

    private final TopologyContext topologyContext;

    public STRM_INIT(TopologyContext topologyContext) {
        this.topologyContext = topologyContext;
    }

    public TopologyContext getTopologyContext() {
        return topologyContext;
    }
}
