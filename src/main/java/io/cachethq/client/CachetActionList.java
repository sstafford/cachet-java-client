package io.cachethq.client;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Cachet action information.
 *
 */
public class CachetActionList {
    private CachetPagination pagination = null;
    private ArrayList<CachetAction> actions = new ArrayList<CachetAction>();

    /**
     * Construct the action list.
     */
    public CachetActionList() {
    }

    /**
     * Set the pagination information.
     */
    public void setPagination(CachetPagination pagination) {
        this.pagination = pagination;
    }

    /**
     * Add an action to the list.
     *
     * @param  action   Cachet action
     */
    public void add(CachetAction action) {
        actions.add(action);
    }

    /**
     * Return the list of actions.
     *
     * @return List of actions.
     */
    public List<CachetAction> getActions() {
        return actions;
    }

    /**
     * Return the number of actions in the list.
     *
     * @return Number of actions currently populated
     */
    public int getActionCount() {
        return actions.size();
    }


    /**
     * Parse the JSON and return an action list
     *
     * @param node   Root of the JSON tree
     * @return Action list
     */
    public static CachetActionList parseRootNode(JsonNode node) {
        CachetActionList actionList = new CachetActionList();

        JsonNode metaNode = node.get("meta");
        if (metaNode != null) {
            CachetPagination pagination = CachetPagination.parseMetaNode(metaNode);
            actionList.setPagination(pagination);
        }

        JsonNode dataNode = node.get("data");
        Iterator<JsonNode> iter = dataNode.elements();
        while (iter.hasNext()) {
            CachetAction action = CachetAction.parseActionNode(iter.next());
            actionList.add(action);
        }

        return actionList;
    }

}
