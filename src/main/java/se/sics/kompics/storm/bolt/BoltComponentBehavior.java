package se.sics.kompics.storm.bolt;


import backtype.storm.tuple.Tuple;

public interface BoltComponentBehavior {

    public void uponExecute(Tuple tuple);

}
