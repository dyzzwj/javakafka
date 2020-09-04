package com.dyzwj.javakafka.consumer;



import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author zwj
 * @version 1.0.0
 * @ClassName BasicConsumer.java
 * @Description TODO
 * @createTime 2019年12月25日 19:51:00
 */
public class BasicConsumer {

    public static void main(String[] args) {


        //1、创建kafka消费者配置信息

        Properties properties = new Properties();
        //kafka broker地址，多个broker用,隔开
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        //key、value反序列化器
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        //是否自动提交offset
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
        //自动提交offset周期
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,1000);

        //指定消费者所在的消费者组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"zwj");


        //2、创建kafka消费者
        KafkaConsumer<String,String> consumer = new KafkaConsumer<>(properties);

        //3、订阅主题
        consumer.subscribe(Arrays.asList("first"));

        while (true){
        //4、消费者拉取数据
        ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
        consumerRecords.forEach( x -> System.out.println(x.key() + "==>" + x.value()));
        }
    }
}
