package io.cachethq.client;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Cachet incident update information.
 *
 */
public class CachetIncidentUpdateList {
    private CachetPagination pagination = null;
    private ArrayList<CachetIncidentUpdate> updates = new ArrayList<CachetIncidentUpdate>();

    /**
     * Construct the incident update list.
     */
    public CachetIncidentUpdateList() {
    }

    /**
     * Set the pagination information.
     */
    public void setPagination(CachetPagination pagination) {
        this.pagination = pagination;
    }

    /**
     * Add an update to the list.
     *
     * @param  update   Cachet incident update
     */
    public void add(CachetIncidentUpdate update) {
        updates.add(update);
    }

    /**
     * Return the list of incident updates.
     *
     * @return List of incident updates.
     */
    public List<CachetIncidentUpdate> getIncidentUpdates() {
        return updates;
    }

    /**
     * Return the number of incident updates in the list.
     *
     * @return Number of incident updates currently populated
     */
    public int getIncidentUpdateCount() {
        return updates.size();
    }


    /**
     * Parse the JSON and return an incident update list
     *
     * @param node   Root of the JSON tree
     * @return Incident update list
     */
    public static CachetIncidentUpdateList parseRootNode(JsonNode node) {
        CachetIncidentUpdateList updateList = new CachetIncidentUpdateList();

        JsonNode metaNode = node.get("meta");
        if (metaNode != null) {
            CachetPagination pagination = CachetPagination.parseMetaNode(metaNode);
            updateList.setPagination(pagination);
        }

        JsonNode dataNode = node.get("data");
        Iterator<JsonNode> iter = dataNode.elements();
        while (iter.hasNext()) {
            CachetIncidentUpdate update = CachetIncidentUpdate.parseIncidentUpdateNode(iter.next());
            updateList.add(update);
        }

        return updateList;
    }

}
