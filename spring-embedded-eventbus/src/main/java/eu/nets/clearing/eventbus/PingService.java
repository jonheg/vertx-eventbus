package eu.nets.clearing.eventbus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;

@Service
public class PingService {
    private static Logger log = LoggerFactory.getLogger(PingService.class);

    @Autowired
    EventBus eventBus;

//    @Scheduled(fixedDelay = 5000)
    public void pingJson() {
        log.info("Sending ping...");
        JsonObject jsonPing = new JsonObject().putString("ping", "ping");
        eventBus.send("vertx.ping.json", jsonPing);
        // no handling of response
    }

//    @Scheduled(fixedDelay = 5000)
    public void pingJsonWithReply() {
        log.info("Sending ping...");
        JsonObject jsonPing = new JsonObject().putString("ping", "ping");
        eventBus.send("vertx.ping.json", jsonPing, new Handler<Message<JsonObject>>() {
            @Override
            public void handle(Message<JsonObject> message) {
               log.info("Got response:" + message.body().getString("ping"));
            }
        });

        log.info("Done !");
    }

//    @Scheduled(fixedDelay = 5000)
    public void pingJsonPublish() {
        log.info("Sending ping...");
        JsonObject jsonPing = new JsonObject().putString("ping", "ping");
        eventBus.publish("vertx.ping.publish", jsonPing);
    }

}
