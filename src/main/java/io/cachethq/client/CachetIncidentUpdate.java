package io.cachethq.client;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Cachet incident update information.
 *
 */
public class CachetIncidentUpdate {

    /**
     * Construct the update.
     */
    public CachetIncidentUpdate() {
    }

    /**
     * Parse the JSON and return an incident update object.
     *
     * @param root   Root of the JSON tree
     * @return Incident update information
     */
    public static CachetIncidentUpdate parseIncidentUpdateNode(JsonNode node) {
        CachetIncidentUpdate update = new CachetIncidentUpdate();
        // TODO
        return update;
    }


}
