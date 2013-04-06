package se.sics.kompics.storm.bolt;

import se.sics.kompics.PortType;
import se.sics.kompics.storm.bolt.evt.*;

/**
 * @author carbone
 */
public final class BoltType extends PortType {
    {
        request(STRM_EMIT.class);
        request(STRM_ACK.class);
        request(STRM_FAIL.class);
        indication(STRM_INIT.class);
        indication(STRM_EXECUTE.class);
    }
}
