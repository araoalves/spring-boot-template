package com.template.config.kafka;

import com.template.model.Cliente;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Profile("kafka")
public class KafkaConfiguration {

	@Value("${kafka.server}")
	private String HOST;

	@Value("${kafka.port}")
	private String PORT;

	@Value("${kafka.gruop}")
	private String GRUOP;


	@Bean
	public ProducerFactory<String, Cliente> producerFactory(){
		String SERVER = HOST + ":" + PORT;

		Map<String, Object> config = new HashMap<>();

		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return new DefaultKafkaProducerFactory<>(config);
	}

	@Bean
	public KafkaTemplate<String, Cliente> kafkaTemplate(){
		return new KafkaTemplate<>(producerFactory());
	}

	@Bean
	public ConsumerFactory<String, Cliente> clienteConsumerFactory(){
		String SERVER = HOST + ":" + PORT;

		Map<String, Object> config = new HashMap<>();

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, GRUOP);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(Cliente.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Cliente> clienteKafkaListenerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, Cliente> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(clienteConsumerFactory());
		return factory;
	}

}
