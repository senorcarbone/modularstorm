package se.sics.kompics.storm.modules;


import backtype.storm.topology.OutputFieldsDeclarer;
import se.sics.kompics.Component;
import se.sics.kompics.Fault;
import se.sics.kompics.Handler;
import se.sics.kompics.storm.bolt.BoltType;
import se.sics.kompics.storm.bolt.KompicsManager;
import se.sics.kompics.storm.bolt.ModuleSetup;

import java.io.Serializable;

public class PrintSetup implements ModuleSetup, Serializable {

    @Override
    public void setup(KompicsManager moduleManager, Component boltComponent, Handler<Fault> failHandler) {
        System.err.println("TEST_SETUP:: setting up components...");
        Component my = moduleManager._create(FwModule.class);
        Component prt = moduleManager._create(PrintModule.class);
        moduleManager._trigger(new PRINT_INIT(), prt.control());
        moduleManager._subscribe(failHandler, my.control());
        moduleManager._subscribe(failHandler, prt.control());
        moduleManager._connect(boltComponent.provided(BoltType.class), my.required(BoltType.class));
        moduleManager._connect(my.provided(FwModuleType.class), prt.required(FwModuleType.class));
    }

    @Override
    public void cleanup(KompicsManager moduleManager) {
    }

    @Override
    public void declareOutput(OutputFieldsDeclarer outputFieldsDeclarer) {
        //none
    }
}
