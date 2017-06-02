package io.cachethq.client;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Cachet server version information.
 *
 */
public class CachetComponentList {
    private CachetPagination pagination = null;
    private ArrayList<CachetComponent> components = new ArrayList<CachetComponent>();

    /**
     * Construct the component.
     */
    public CachetComponentList() {
    }

    /**
     * Set the pagination information.
     */
    public void setPagination(CachetPagination pagination) {
        this.pagination = pagination;
    }

    /**
     * Add a component to the list.
     *
     * @param  comp   Component
     */
    public void add(CachetComponent comp) {
        components.add(comp);
    }

    /**
     * Return the list of components.
     *
     * @return List of components
     */
    public List<CachetComponent> getComponents() {
        return components;
    }

    /**
     * Return the number of components in the list.
     *
     * @return Number of components currently populated
     */
    public int getComponentCount() {
        return components.size();
    }


    /**
     * Parse the JSON and return a version object.
     *
     * @param node   Root of the JSON tree
     * @return Component list
     */
    public static CachetComponentList parseRootNode(JsonNode node) {
        CachetComponentList compList = new CachetComponentList();

        JsonNode metaNode = node.get("meta");
        if (metaNode != null) {
            CachetPagination pagination = CachetPagination.parseMetaNode(metaNode);
            compList.setPagination(pagination);
        }

        JsonNode dataNode = node.get("data");
        Iterator<JsonNode> compIter = dataNode.elements();
        while (compIter.hasNext()) {
            CachetComponent comp = CachetComponent.parseComponentNode(compIter.next());
            compList.add(comp);
        }

        return compList;
    }

}
