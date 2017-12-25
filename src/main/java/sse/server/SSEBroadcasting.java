package sse.server;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

@Path("broadcast")
@Singleton
public class SSEBroadcasting {

    @Context
    private Sse sse;

    private volatile SseBroadcaster sseBroadcaster;

    @PostConstruct
    public void init(){
        this.sseBroadcaster = sse.newBroadcaster();
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void register(@Context SseEventSink eventSink){
        eventSink.send(sse.newEvent("Welcome!"));
        sseBroadcaster.register(eventSink);
    }

    @POST
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void broadcast(@FormParam("event") String event){
        sseBroadcaster.broadcast(sse.newEvent(event));
    }




}
