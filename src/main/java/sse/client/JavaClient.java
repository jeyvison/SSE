package sse.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;

public class JavaClient {

    public void connectToServer() {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/events/broadcast");

        try (SseEventSource source = SseEventSource.target(target).build()) {
            source.register(System.out::println);
            source.open();
            Thread.sleep(500); // Consume events for just 500 ms
        } catch (InterruptedException e) {

        }
    }

}
