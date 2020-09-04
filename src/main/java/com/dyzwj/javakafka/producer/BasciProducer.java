package com.dyzwj.javakafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author zwj
 * @version 1.0.0
 * @ClassName BasciProducer.java
 * @Description TODO
 * @createTime 2019年12月25日 19:57:00
 */
public class BasciProducer {

    public static void main(String[] args) {
        //1、创建kafka生产者配置信息
        Properties properties = new Properties();

        //broker地址，多个broker以,隔开
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
        //生产者ack配置
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        //重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 1);
        //健序列化器
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        //值序列化器
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        //批次大小
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        //RecordAccumulator 缓冲区大小
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        //等待时间
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);


        //2、根据kafka生产配置信息创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        //3、循环发送数据
        for (int i = 10; i < 20; i++) {
            producer.send(new ProducerRecord("first", "zwj" + i));
        }

        //4、关闭资源
        producer.close();
    }
}
