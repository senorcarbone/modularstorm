package se.sics.kompics.storm.evt;

import backtype.storm.task.TopologyContext;
import se.sics.kompics.Init;

/**
 * @author carbone
 */
public class STRM_INIT extends Init {

    private final TopologyContext topologyContext;

    public STRM_INIT(TopologyContext topologyContext) {
        this.topologyContext = topologyContext;
    }

    public TopologyContext getTopologyContext() {
        return topologyContext;
    }
}
