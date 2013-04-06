package se.sics.kompics.storm.bolt;

import backtype.storm.topology.OutputFieldsDeclarer;
import se.sics.kompics.Component;
import se.sics.kompics.Fault;
import se.sics.kompics.Handler;

/**
 * An interface for setting up module dependencies and binding to a underlying bolt module
 * for communicating with storm
 *
 * @author carbone
 */
public interface ModuleSetup {

    /**
     * Will be called during the runtime preparation of the storm bolt. In this function the moduleManager can be used
     * to assign dependencies via the Kompics execution environment. All modules setup should be done here.
     * Initiation messages should also be sent here as well. It is advised to assign the given fault handler to each boltComponent
     * in order to guarantee a fail-stop behavior for the bolt as a whole.
     *
     * @param moduleManager
     * @param boltComponent
     * @param failHandler
     */
    public void setup(KompicsManager moduleManager, Component boltComponent, Handler<Fault> failHandler);

    /**
     * Will be called during shutdown of a bolt in order to de-associate modules from the Kompics scheduler
     * and release resources
     *
     * @param moduleManager
     */
    public void cleanup(KompicsManager moduleManager);

    /**
     * Will be called for custom fields declaration by the storm runtime
     *
     * @param outputFieldsDeclarer
     */
    public void declareOutput(OutputFieldsDeclarer outputFieldsDeclarer);
}
