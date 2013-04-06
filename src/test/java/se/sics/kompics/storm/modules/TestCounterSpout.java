package se.sics.kompics.storm.modules;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;

import java.util.Map;

import static backtype.storm.utils.Utils.tuple;

public class TestCounterSpout extends BaseRichSpout {


    private final int maxNum;
    private final String numAlias;
    private SpoutOutputCollector _collector;
    int currentNum = 0;

    public TestCounterSpout(int maxNum, String numAlias) {
        this.maxNum = maxNum;
        this.numAlias = numAlias;
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields(numAlias));
    }

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        _collector = spoutOutputCollector;
    }

    @Override
    public void nextTuple() {
        if (currentNum <= maxNum) {
            _collector.emit(tuple(currentNum++));
        }
    }
}
