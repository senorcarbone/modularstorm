package se.sics.kompics.storm.bolt;

import backtype.storm.tuple.Tuple;
import se.sics.kompics.ComponentDefinition;
import se.sics.kompics.Handler;
import se.sics.kompics.Negative;
import se.sics.kompics.storm.evt.*;

/**
 * @author carbone
 */
public class BoltComponent extends ComponentDefinition implements BoltComponentBehavior {

    Negative<BoltType> blt = negative(BoltType.class);

    private BoltBehavior stormProxy;

    public BoltComponent() {
        subscribe(handleInit, control);
        subscribe(handleEmit, blt);
        subscribe(handleAck, blt);
        subscribe(handleFail, blt);
    }

    private Handler<STRM_BOLT_INIT> handleInit = new Handler<STRM_BOLT_INIT>() {
        public void handle(STRM_BOLT_INIT message) {
            BoltComponent.this.stormProxy = message.getProxy();
            BoltComponent.this.stormProxy.bind(BoltComponent.this);
            trigger(new STRM_INIT(message.getTopologyContext()), blt);
        }
    };

    private Handler<STRM_EMIT> handleEmit = new Handler<STRM_EMIT>() {
        public void handle(STRM_EMIT message) {
            stormProxy.emit(message.getStreamID(), message.getAnchors(), message.getTuple());
        }
    };

    private Handler<STRM_ACK> handleAck = new Handler<STRM_ACK>() {
        public void handle(STRM_ACK message) {
            stormProxy.ack(message.getTuple());
        }
    };

    private Handler<STRM_FAIL> handleFail = new Handler<STRM_FAIL>() {
        public void handle(STRM_FAIL message) {
            stormProxy.fail(message.getTuple());
        }
    };

    @Override
    public void uponExecute(Tuple tuple) {
        trigger(new STRM_EXECUTE(tuple), blt);
    }

}
