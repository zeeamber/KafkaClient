package com;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaProducer extends Thread{

	Scanner in=new Scanner(System.in);
	String TOPIC="";
	
	public kafka.javaapi.producer.Producer<String, String> producer;
	
	public KafkaProducer(String topic)
	{
		this.TOPIC=topic;
		Properties properties=new Properties();
		properties.put("metadata.broker.list", "172.20.11.198:9092");
		properties.put("serializer.class", "kafka.serializer.StringEncoder");
		ProducerConfig producerConfig=new ProducerConfig(properties);
		producer=new kafka.javaapi.producer.Producer<String, String>(producerConfig);
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			String message=in.nextLine();
			SimpleDateFormat sdf = new SimpleDateFormat();
			KeyedMessage<String, String> msg =new KeyedMessage<String, String>(TOPIC,message + "  "+sdf.format(new Date()));
			producer.send(msg);
		}
	}

	public void closeProducer()
	{
		producer.close();
	}

}
