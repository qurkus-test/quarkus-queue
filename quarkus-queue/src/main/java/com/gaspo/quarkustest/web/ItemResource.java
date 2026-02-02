package com.gaspo.quarkustest.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gaspo.quarkustest.model.ItemDto;
import com.gaspo.quarkustest.producer.ItemProducer;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.reactive.messaging.Message;

@Path("/item")
@Slf4j
public class ItemResource {

    @Inject
    ItemProducer itemProducer;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String produceMessage(@RequestBody ItemDto itemDto) throws JsonProcessingException {
    //    ItemDto.builder().withDescription("primo item").withName("1-uno").withId(1L).build();
        Message<ItemDto> message = Message.of(itemDto);
        Message<ItemDto> messageO = itemProducer.sendMessage();
        log.info("messageO description:{}",messageO.getPayload().getDescription());
        return "Sent message: "+itemDto+" description:"+itemDto.getDescription();
    }
}
