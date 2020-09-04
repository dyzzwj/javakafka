package com.dyzwj.javakafka.partition;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @author zwj
 * @version 1.0.0
 * @ClassName CustomerPartitioner.java
 * @Description TODO
 * @createTime 2019年12月24日 16:49:00
 */
public class CustomerPartitioner implements Partitioner {
    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        return 1;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
