modularstorm
============

An experimental integration project of the [Kompics](https://github.com/kompics/kompics) (SICS-KTH)  message-passing component model on [Storm](https://github.com/nathanmarz/storm).
It offers a clean modular component-based api to Storm that feels to be missing in the Java version.
For implementations that involve multi-threading and different component stacks and dependencies on top of the 
storm processing model modularstorm can be leveraged for removing most of such complexity.
Scheduling of component workers is also handled by the Kompics scheduler and allows for efficient usage of multi-core machines.
Flow-control events might be added soon since accumulated execute events can potentially trigger memory issues if the processing rate is too slow.

It is first advised to go through the Kompics [documentation](http://kompics.sics.se/trac/attachment/wiki/WikiStart/kompics-tutorial.pdf) to see how to build some basic components.
On this implementation one Kompics scheduler is being instantiated per Storm worker (JVM process) and further component binding can be done manually 
upon a task 'prepared' state.

As of its current version a *KompicsBolt* component is given as a basis that provides the following specification:

```
Request:
------------
STRM_EMIT :: {StreamID, [Anchors], [Object]}
STRM_ACK :: {Tuple} 
STRM_FAIL :: {Tuple}

Indication:
---------------
STRM_INIT :: {TopologyContext} 
STRM_EXECUTE :: {Tuple}
```

A short demo on how to use the current api for assembling your own components on top of *KompicsBolt* can be found [here](https://github.com/senorcarbone/modularstorm/blob/master/src/test/java/se/sics/kompics/storm/topologies/TestTopology.java)
