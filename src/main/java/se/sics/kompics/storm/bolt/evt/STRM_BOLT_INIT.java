package se.sics.kompics.storm.bolt.evt;

import backtype.storm.task.TopologyContext;
import se.sics.kompics.Init;
import se.sics.kompics.storm.bolt.core.BoltBehavior;

/**
 * @author carbone
 */
public class STRM_BOLT_INIT extends Init {

    private final TopologyContext topologyContext;
    private BoltBehavior proxy;

    public STRM_BOLT_INIT(TopologyContext topologyContext, BoltBehavior proxy) {
        this.topologyContext = topologyContext;
        this.proxy = proxy;
    }

    public TopologyContext getTopologyContext() {
        return topologyContext;
    }

    public BoltBehavior getProxy() {
        return proxy;
    }
}
