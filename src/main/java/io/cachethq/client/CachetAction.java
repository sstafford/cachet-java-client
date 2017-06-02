package io.cachethq.client;

import com.fasterxml.jackson.databind.JsonNode;


/**
 * Cachet action information.
 *
 */
public class CachetAction {

    /**
     * Construct the action.
     */
    public CachetAction() {
    }

    /**
     * Parse the JSON and return an action object.
     *
     * @param root   Root of the JSON tree
     * @return Action information
     */
    public static CachetAction parseActionNode(JsonNode node) {
        CachetAction action = new CachetAction();
        // TODO
        return action;
    }

}
