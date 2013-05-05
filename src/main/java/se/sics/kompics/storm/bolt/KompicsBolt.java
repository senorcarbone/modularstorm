package se.sics.kompics.storm.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;
import se.sics.kompics.Component;
import se.sics.kompics.Fault;
import se.sics.kompics.Handler;
import se.sics.kompics.storm.bolt.core.BoltBehavior;
import se.sics.kompics.storm.bolt.core.BoltComponent;
import se.sics.kompics.storm.bolt.core.BoltComponentBehavior;
import se.sics.kompics.storm.bolt.core.BoltProxy;
import se.sics.kompics.storm.bolt.evt.STRM_BOLT_INIT;

import java.util.List;
import java.util.Map;

/**
 * @author carbone
 */
public class KompicsBolt implements IRichBolt, BoltBehavior {

    private transient BoltComponentBehavior boltProxy;
    private transient OutputCollector outputCollector;
    private ModuleSetup moduleSetup;

    private KompicsBolt(ModuleSetup moduleSetup) {
        this.moduleSetup = moduleSetup;
    }

    private void processFailure(Fault fault) {
        moduleSetup.cleanup(KompicsManager.getInstance());
        outputCollector.reportError(new Throwable(fault.getFault()));
    }

    public static KompicsBolt build(ModuleSetup setup) {
        return new KompicsBolt(setup);
    }

    public ModuleSetup getModuleSetup() {
        return moduleSetup;
    }

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
        setupModules(topologyContext);
    }

    @Override
    public void cleanup() {
        moduleSetup.cleanup(KompicsManager.getInstance());
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        moduleSetup.declareOutput(outputFieldsDeclarer);
    }

    private void setupModules(TopologyContext topologyContext) {
        Handler<Fault> failHandler = new Handler<Fault>() {
            @Override
            public void handle(Fault fault) {
                processFailure(fault);
            }
        };
        KompicsManager moduleManager = KompicsManager.getInstance();
        Component boltComponent = moduleManager._create(BoltComponent.class);
        moduleManager._subscribe(failHandler, boltComponent.control());
        moduleSetup.setup(moduleManager, boltComponent, failHandler);
        BoltBehavior proxy = new BoltProxy(this);
        boltProxy = (BoltComponentBehavior) proxy;
        moduleManager._trigger(new STRM_BOLT_INIT(topologyContext, proxy), boltComponent.control());
    }

    @Override
    public void execute(Tuple tuple) {
        boltProxy.uponExecute(tuple);
    }


    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

    @Override
    public void emit(String streamID, List<Tuple> anchors, List<Object> tuple) {
        if (streamID == null && anchors == null) {
            outputCollector.emit(tuple);
        } else if (streamID == null)
            outputCollector.emit(anchors, tuple);
        else
            outputCollector.emit(streamID, anchors, tuple);
    }

    @Override
    public void ack(Tuple tuple) {
        outputCollector.ack(tuple);
    }

    @Override
    public void fail(Tuple tuple) {
        outputCollector.fail(tuple);
    }

    @Override
    public void bind(BoltComponent component) {
    }
}
