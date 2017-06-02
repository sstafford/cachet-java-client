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
public class CachetComponentGroupList {
    private CachetPagination pagination = null;
    private ArrayList<CachetComponentGroup> groups = new ArrayList<CachetComponentGroup>();

    /**
     * Construct the component.
     */
    public CachetComponentGroupList() {
    }

    /**
     * Set the pagination information.
     */
    public void setPagination(CachetPagination pagination) {
        this.pagination = pagination;
    }

    /**
     * Add a group to the list.
     *
     * @param  group   Component group
     */
    public void add(CachetComponentGroup group) {
        groups.add(group);
    }

    /**
     * Return the list of component groups.
     *
     * @return List of component groups
     */
    public List<CachetComponentGroup> getGroups() {
        return groups;
    }

    /**
     * Return the number of component groups in the list.
     *
     * @return Number of component groups currently populated
     */
    public int getGroupCount() {
        return groups.size();
    }


    /**
     * Parse the JSON and return a version object.
     *
     * @param node   Root of the JSON tree
     * @return Component group list
     */
    public static CachetComponentGroupList parseRootNode(JsonNode node) {
        CachetComponentGroupList groupList = new CachetComponentGroupList();

        JsonNode metaNode = node.get("meta");
        if (metaNode != null) {
            CachetPagination pagination = CachetPagination.parseMetaNode(metaNode);
            groupList.setPagination(pagination);
        }

        JsonNode dataNode = node.get("data");
        Iterator<JsonNode> compIter = dataNode.elements();
        while (compIter.hasNext()) {
            CachetComponentGroup group = CachetComponentGroup.parseComponentGroupNode(compIter.next());
            groupList.add(group);
        }

        return groupList;
    }

}
