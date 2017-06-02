package io.cachethq.client;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Cachet subscriber information.
 *
 */
public class CachetSubscriberList {
    private CachetPagination pagination = null;
    private ArrayList<CachetSubscriber> subscribers = new ArrayList<CachetSubscriber>();

    /**
     * Construct the subscriber list.
     */
    public CachetSubscriberList() {
    }

    /**
     * Set the pagination information.
     */
    public void setPagination(CachetPagination pagination) {
        this.pagination = pagination;
    }

    /**
     * Add a subscriber to the list.
     *
     * @param  subscriber   Cachet subscriber
     */
    public void add(CachetSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * Return the list of subscribers.
     *
     * @return List of subscribers.
     */
    public List<CachetSubscriber> getSubscribers() {
        return subscribers;
    }

    /**
     * Return the number of subscribers in the list.
     *
     * @return Number of subscribers currently populated
     */
    public int getSubscriberCount() {
        return subscribers.size();
    }


    /**
     * Parse the JSON and return a subscriber list
     *
     * @param node   Root of the JSON tree
     * @return Subscriber list
     */
    public static CachetSubscriberList parseRootNode(JsonNode node) {
        CachetSubscriberList subscriberList = new CachetSubscriberList();

        JsonNode metaNode = node.get("meta");
        if (metaNode != null) {
            CachetPagination pagination = CachetPagination.parseMetaNode(metaNode);
            subscriberList.setPagination(pagination);
        }

        JsonNode dataNode = node.get("data");
        Iterator<JsonNode> iter = dataNode.elements();
        while (iter.hasNext()) {
            CachetSubscriber subscriber = CachetSubscriber.parseSubscriberNode(iter.next());
            subscriberList.add(subscriber);
        }

        return subscriberList;
    }

}
