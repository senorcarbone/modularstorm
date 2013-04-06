package se.sics.kompics.storm.bolt.core;


import backtype.storm.tuple.Tuple;

public interface BoltComponentBehavior {

    public void uponExecute(Tuple tuple);

}
