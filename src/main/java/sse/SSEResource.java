package sse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

@Path("fire")
public class SSEResource {

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void toString(@Context SseEventSink eventSink, @Context Sse sse) {
        try(SseEventSink sink = eventSink){
            sink.send(sse.newEvent("data"));
            sink.send(sse.newEvent("WhateverNameForThisEvnet","more data"));

            OutboundSseEvent event = sse.newEventBuilder().
                id("EventId").
                name("EventName").
                data("Data").
                reconnectDelay(10000).
                comment("Anything i wanna send here").
                build();

            sink.send(event);

        }
    }
}
