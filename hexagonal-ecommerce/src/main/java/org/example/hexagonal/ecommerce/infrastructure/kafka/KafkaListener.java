package org.example.hexagonal.ecommerce.infrastructure.kafka;

import java.util.function.Consumer;

import org.example.hexagonal.ecommerce.application.services.SizeService;
import org.example.hexagonal.ecommerce.infrastructure.events.ProductAvailabilityEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class KafkaListener {
	
	@Autowired
	private SizeService sizeService;
	
	@Bean
	public Consumer<Message<ProductAvailabilityEvent>> KafkaConsumer() {
		return message -> {
			ProductAvailabilityEvent availabilityEvent = message.getPayload();
			sizeService.updateSizeByProductAvailabilityEvent(availabilityEvent);
		};
	}

}
