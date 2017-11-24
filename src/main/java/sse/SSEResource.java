package sse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

@Path("fire")
public class SSEResource {

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void toString(@Context SseEventSink eventSink, @Context Sse sse) {
        try(SseEventSink sink = eventSink){
            sink.send(sse.newEvent("Event1"));
            sink.send(sse.newEvent("Event2"));
            sink.send(sse.newEvent("Event3"));
        }
    }
}
