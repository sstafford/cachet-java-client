package io.cachethq.client;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Cachet incident information.
 *
 */
public class CachetIncident {

    /**
     * Construct the component.
     */
    public CachetIncident() {
    }

    /**
     * Parse the JSON and return an incident object.
     *
     * @param root   Root of the JSON tree
     * @return Incident information
     */
    public static CachetIncident parseIncidentNode(JsonNode node) {
        CachetIncident incident = new CachetIncident();
        // TODO
        return incident;
    }

}
