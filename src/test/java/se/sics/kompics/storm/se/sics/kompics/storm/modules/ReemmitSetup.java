package se.sics.kompics.storm.se.sics.kompics.storm.modules;


import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import se.sics.kompics.Component;
import se.sics.kompics.Fault;
import se.sics.kompics.Handler;
import se.sics.kompics.storm.bolt.BoltType;
import se.sics.kompics.storm.bolt.KompicsManager;
import se.sics.kompics.storm.bolt.ModuleSetup;

import java.io.Serializable;

public class ReemmitSetup implements ModuleSetup, Serializable {

    @Override
    public void setup(KompicsManager moduleManager, Component boltComponent, Handler<Fault> failHandler) {
        System.err.println("TEST_SETUP:: setting up components...");
        Component rem = moduleManager._create(ReemmitModule.class);
        moduleManager._subscribe(failHandler, rem.control());
        moduleManager._connect(boltComponent.provided(BoltType.class), rem.required(BoltType.class));
    }

    @Override
    public void cleanup(KompicsManager moduleManager) {
        //add support
    }

    @Override
    public void declareOutput(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("msg"));
    }
}
