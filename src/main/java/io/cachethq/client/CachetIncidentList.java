package io.cachethq.client;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Cachet incident information.
 *
 */
public class CachetIncidentList {
    private CachetPagination pagination = null;
    private ArrayList<CachetIncident> incidents = new ArrayList<CachetIncident>();

    /**
     * Construct the component.
     */
    public CachetIncidentList() {
    }

    /**
     * Set the pagination information.
     */
    public void setPagination(CachetPagination pagination) {
        this.pagination = pagination;
    }

    /**
     * Add an incident to the list.
     *
     * @param  incident   Cachet incident
     */
    public void add(CachetIncident incident) {
        incidents.add(incident);
    }

    /**
     * Return the list of incidents.
     *
     * @return List of incidents.
     */
    public List<CachetIncident> getIncidents() {
        return incidents;
    }

    /**
     * Return the number of incidents in the list.
     *
     * @return Number of incidents currently populated
     */
    public int getIncidentCount() {
        return incidents.size();
    }


    /**
     * Parse the JSON and return an incident list
     *
     * @param node   Root of the JSON tree
     * @return Incident list
     */
    public static CachetIncidentList parseRootNode(JsonNode node) {
        CachetIncidentList incidentList = new CachetIncidentList();

        JsonNode metaNode = node.get("meta");
        if (metaNode != null) {
            CachetPagination pagination = CachetPagination.parseMetaNode(metaNode);
            incidentList.setPagination(pagination);
        }

        JsonNode dataNode = node.get("data");
        Iterator<JsonNode> iter = dataNode.elements();
        while (iter.hasNext()) {
            CachetIncident incident = CachetIncident.parseIncidentNode(iter.next());
            incidentList.add(incident);
        }

        return incidentList;
    }

}
