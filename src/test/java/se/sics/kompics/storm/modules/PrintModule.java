package se.sics.kompics.storm.modules;


import se.sics.kompics.ComponentDefinition;
import se.sics.kompics.Handler;
import se.sics.kompics.Positive;

public class PrintModule extends ComponentDefinition {

    private Positive<FwModuleType> bolt = requires(FwModuleType.class);

    public PrintModule() {
        System.err.println("PrintModule:: Instatiating...");
        subscribe(handleInit, control);
        subscribe(handlePrint, bolt);
    }

    private Handler<PRINT_INIT> handleInit = new Handler<PRINT_INIT>() {
        @Override
        public void handle(PRINT_INIT event) {
            //
        }
    };

    private Handler<Eurika> handlePrint = new Handler<Eurika>() {
        public void handle(Eurika message) {
            System.err.println("PrintModule: " + message);
        }
    };
}
