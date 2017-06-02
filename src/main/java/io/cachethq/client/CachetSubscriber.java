package io.cachethq.client;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Cachet subscriber information.
 *
 */
public class CachetSubscriber {

    /**
     * Construct the subscriber.
     */
    public CachetSubscriber() {
    }

    /**
     * Parse the JSON and return a subscriber object.
     *
     * @param root   Root of the JSON tree
     * @return Subscriber information
     */
    public static CachetSubscriber parseSubscriberNode(JsonNode node) {
        CachetSubscriber subscriber = new CachetSubscriber();
        // TODO
        return subscriber;
    }

}
