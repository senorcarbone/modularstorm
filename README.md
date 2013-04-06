modularstorm
============

An experimental integration project of the Kompics (SICS-KTH) module messaging framework to Storm.
It offers a clean modular actor-like api to Storm that seems to be missing in the Java version.
For implementations that involve multi-threading and different component stacks and dependencies on top of the 
storm processing model modularstorm can be leveraged for removing most of such complexity.
Scheduling of component workers is also handled by the Kompics scheduler and allows for efficient usage of multi-core machines.

It is first adviced to go through the Kompics documentation (http://kompics.sics.se/) to see how to build some basic components.
On this implementation one Kompics scheduler is being instantiated per JVM core/Storm worker and further component binding can be done manually 
upon a task 'prepared' state.
