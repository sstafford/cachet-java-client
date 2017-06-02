package io.cachethq.client;

import com.fasterxml.jackson.databind.JsonNode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Cachet component group information.
 *
 */
public class CachetComponentGroup {
    /** Date format used to parse the created_at and deleted_at fields (2015-07-24 14:42:10) */
    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private int id = 0;
    private String name = null;
    private int order = 0;
    private int collapsed = 0;
    private Date createdAt = null;
    private Date updatedAt = null;


    /**
     * Construct the component.
     */
    public CachetComponentGroup() {
    }

    /**
     * Set the Cachet component ID.
     *
     * @param  id   Component ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Return the Cachet component ID.
     *
     * @return Component ID
     */
    public int getId() {
        return id;
    }

    /**
     * Set the Cachet component name.
     *
     * @param  name   Component name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the Cachet component name.
     *
     * @return Component name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the Cachet component order.
     *
     * @param  order   Component order
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Return the Cachet component order.
     *
     * @return Component order
     */
    public int getOrder() {
        return order;
    }

    /**
     * Set the Cachet group collapsed state.
     *
     * @param  collapsed   Collapsed state
     */
    public void setCollapsed(int collapsed) {
        this.collapsed = collapsed;
    }

    /**
     * Return the Cachet group collapsed state.
     *
     * @return Collapsed state
     */
    public int getCollapsed() {
        return collapsed;
    }

    /**
     * Set the Cachet component creation date.
     *
     * @param  date   Creation date
     */
    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }

    /**
     * Return the Cachet component creation date.
     *
     * @return Component creation date
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Set the Cachet component update date.
     *
     * @param  date   Last update
     */
    public void setUpdatedAt(Date date) {
        this.updatedAt = date;
    }

    /**
     * Return the Cachet component update date.
     *
     * @return Last update
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Parse the JSON and return a version object.
     *
     * @param node   Root of the JSON tree
     * @return Version information
     */
    public static CachetComponentGroup parseRootNode(JsonNode node) {
        JsonNode dataNode = node.get("data");
        return parseComponentGroupNode(dataNode);
    }

    /**
     * Parse the JSON and return a group information object.
     *
     * @param root   Root of the JSON tree
     * @return Component group information
     */
    public static CachetComponentGroup parseComponentGroupNode(JsonNode node) {
        CachetComponentGroup group = new CachetComponentGroup();
/*
{
    "data": {
        "id": 1,
        "name": "Websites",
        "created_at": "2015-11-07 13:30:04",
        "updated_at": "2015-11-07 13:30:04",
        "order": 1,
        "collapsed": 0
    }
}
*/

        JsonNode idNode = node.get("id");
        if (idNode != null) {
            group.setId(idNode.asInt());
        }

        JsonNode orderNode = node.get("order");
        if (orderNode != null) {
            group.setOrder(orderNode.asInt());
        }

        JsonNode collapsedNode = node.get("collapsed");
        if (collapsedNode != null) {
            group.setCollapsed(collapsedNode.asInt());
        }

        JsonNode nameNode = node.get("name");
        if (nameNode != null) {
            group.setName(nameNode.asText());
        }

        JsonNode createdAtNode = node.get("created_at");
        if (createdAtNode != null) {
            try {
                Date createdDate = DATE_FORMAT.parse(createdAtNode.asText());
                group.setCreatedAt(createdDate);
            } catch (ParseException pex) {
                // Do nothing
            }
        }

        JsonNode updatedAtNode = node.get("updated_at");
        if (updatedAtNode != null) {
            try {
                Date updatedDate = DATE_FORMAT.parse(updatedAtNode.asText());
                group.setUpdatedAt(updatedDate);
            } catch (ParseException pex) {
                // Do nothing
            }
        }

System.out.println("SSDEBUG: parsed component group " + group.getId() + " " + group.getName());
        return group;
    }

}
