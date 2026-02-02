package com.gaspo.quarkustest.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaspo.quarkustest.model.ItemDto;
import com.gaspo.quarkustest.utils.FileUtils;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.*;

@ApplicationScoped
public class ItemProducer {


    @Outgoing("item-out")
    public Message<ItemDto> sendMessage() throws JsonProcessingException { //(Message<ItemDto> message) {
        String item1 = FileUtils.readFileFromResourceToString("sample-data/item1.json")
                .orElseThrow(() -> new RuntimeException("Item file not found"));
        ObjectMapper objectMapper = new ObjectMapper();
        ItemDto itemdto = objectMapper.readValue(item1, ItemDto.class);
        Message<ItemDto> message = Message.of(itemdto);
        return message
                .withPayload(message.getPayload());
    }


}
