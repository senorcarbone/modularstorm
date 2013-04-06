package se.sics.kompics.storm.modules;


import se.sics.kompics.ComponentDefinition;
import se.sics.kompics.Handler;
import se.sics.kompics.Negative;
import se.sics.kompics.Positive;
import se.sics.kompics.storm.bolt.BoltType;
import se.sics.kompics.storm.bolt.evt.STRM_EXECUTE;
import se.sics.kompics.storm.bolt.evt.STRM_INIT;

public class FwModule extends ComponentDefinition {


    private Negative<FwModuleType> my = provides(FwModuleType.class);
    private Positive<BoltType> bolt = requires(BoltType.class);


    public FwModule() {
        System.err.println("FwModule:: Instatiating...");
        subscribe(handleInit, bolt);
        subscribe(handleExecute, bolt);
    }

    private Handler<STRM_INIT> handleInit = new Handler<STRM_INIT>() {
        public void handle(STRM_INIT message) {
            System.err.println("FwModule:: Init Received!");
        }
    };

    private Handler<STRM_EXECUTE> handleExecute = new Handler<STRM_EXECUTE>() {
        public void handle(STRM_EXECUTE message) {
            System.err.println("FwModule:: Execute Received! " + message.getTuple());
            Eurika msg = new Eurika("Eurika::" + message.getTuple().getStringByField("msg"));
            System.err.println("FwModule: triggering: " + msg);
            trigger(msg, my);
        }
    };


}
