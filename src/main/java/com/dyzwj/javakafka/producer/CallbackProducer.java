package com.dyzwj.javakafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author zwj
 * @version 1.0.0
 * @ClassName CallbackProducer.java
 * @Description TODO
 * @createTime 2019年12月24日 15:38:00
 */
public class CallbackProducer {


    public static void main(String[] args) {

        //1、创建kafka配置信息
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092,localhost:9093,localhost:9094");
        properties.put(ProducerConfig.LINGER_MS_CONFIG,1);
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.RETRIES_CONFIG,1);
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        //设置分区器
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.dyzwj.kafkajava.partition.CustomerPartitioner");

        //2、根据kafka配置信息创建生产者
        KafkaProducer<String,String> producer = new KafkaProducer(properties);


        //3、循环发送消息，并传入回调
        for (int i = 20; i < 30; i++) {
            producer.send(new ProducerRecord("first","zwj","dyz--" + i),
                    //producer收到ack是=时调用，为异步调用
                    (metadata,exception) -> {
                        if(exception == null){
                            System.out.println(metadata.partition() + "===>" + metadata.offset());
                        }else {
                            exception.printStackTrace();
                        }

                    });

        }

        producer.close();


    }



}
