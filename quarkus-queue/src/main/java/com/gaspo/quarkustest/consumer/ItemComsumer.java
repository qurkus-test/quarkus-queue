package com.gaspo.quarkustest.consumer;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.*;

@ApplicationScoped
public class ItemComsumer {



    /**
     * Consume the message from the "words-in" channel, uppercase it and send it to the uppercase channel.
     * Messages come from the broker.
     **/
   // @Incoming("item-in")
    public Message<String> toUpperCase(Message<String> message) {
        return message.withPayload(message.getPayload().toUpperCase());
    }

    /**
     * Consume the uppercase channel (in-memory) and print the messages.
     **/
    //@Incoming("uppercase")
    public void sink(String word) {
        System.out.println(">> " + word);
    }
}
