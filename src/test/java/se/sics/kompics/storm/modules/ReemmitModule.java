package se.sics.kompics.storm.modules;

import com.google.common.collect.Lists;
import se.sics.kompics.ComponentDefinition;
import se.sics.kompics.Handler;
import se.sics.kompics.Positive;
import se.sics.kompics.storm.bolt.BoltType;
import se.sics.kompics.storm.bolt.evt.STRM_EMIT;
import se.sics.kompics.storm.bolt.evt.STRM_EXECUTE;
import se.sics.kompics.storm.bolt.evt.STRM_INIT;

import java.util.List;

public class ReemmitModule extends ComponentDefinition {

    private Positive<BoltType> bolt = requires(BoltType.class);

    public ReemmitModule() {
        subscribe(handleInit, bolt);
        subscribe(handleExecute, bolt);
    }

    private Handler<STRM_INIT> handleInit = new Handler<STRM_INIT>() {
        public void handle(STRM_INIT message) {
            System.err.println("Reemmit:: Init Received!");
        }
    };

    private Handler<STRM_EXECUTE> handleExecute = new Handler<STRM_EXECUTE>() {
        public void handle(STRM_EXECUTE message) {
            System.err.println("Remmit:: Execute Received! Reemmiting");
            List<Object> tpl = Lists.newArrayList();
            tpl.add(String.valueOf(message.getTuple().getInteger(0)));
            trigger(new STRM_EMIT(message.getTuple(), tpl), bolt);
        }
    };

}
