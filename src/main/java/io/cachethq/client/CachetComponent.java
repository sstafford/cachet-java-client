package io.cachethq.client;

import com.fasterxml.jackson.databind.JsonNode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Cachet server version information.
 *
 */
public class CachetComponent {
    /** Date format used to parse the created_at and deleted_at fields (2015-07-24 14:42:10) */
    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private int id = 0;
    private String name = null;
    private String description = null;
    private String link = null;
    private int status = 0;
    private String statusName = null;
    private int order = 0;
    private int groupId = 0;
    private Date createdAt = null;
    private Date updatedAt = null;
    private Date deletedAt = null;
    //private CachetStatus statusName = null;
    private Map<String, String> tags = null;


    /**
     * Construct the component.
     */
    public CachetComponent() {
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
     * Set the Cachet component description.
     *
     * @param  description   Component description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Return the Cachet component description.
     *
     * @return Component description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the Cachet component link.
     *
     * @param  link   Component link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Return the Cachet component link.
     *
     * @return Component link
     */
    public String getLink() {
        return link;
    }

    /**
     * Set the Cachet component status.
     *
     * @param  status   Component status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Return the Cachet component status.
     *
     * @return Component status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Set the Cachet component status.
     *
     * @param  status   Component status
     */
    public void setStatusName(String status) {
        this.statusName = status;
    }

    /**
     * Return the Cachet component status.
     *
     * @return Component status
     */
    public String getStatusName() {
        return statusName;
    }

    /**
     * Set the Cachet component group ID.
     *
     * @param  id   Component group ID
     */
    public void setGroupId(int id) {
        this.groupId = id;
    }

    /**
     * Return the Cachet component group ID.
     *
     * @return Component group ID
     */
    public int getGroupId() {
        return groupId;
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
     * Set the Cachet component deletion date.
     *
     * @param  date   Deletion date
     */
    public void setDeletedAt(Date date) {
        this.deletedAt = date;
    }

    /**
     * Return the Cachet component deletion date.
     *
     * @return Component deletion date
     */
    public Date getDeletedAt() {
        return deletedAt;
    }

    /**
     * Parse the JSON and return a version object.
     *
     * @param node   Root of the JSON tree
     * @return Version information
     */
    public static CachetComponent parseRootNode(JsonNode node) {
        JsonNode dataNode = node.get("data");
        return parseComponentNode(dataNode);
    }

    /**
     * Parse the JSON and return a version object.
     *
     * @param root   Root of the JSON tree
     * @return Version information
     */
    public static CachetComponent parseComponentNode(JsonNode node) {
        CachetComponent component = new CachetComponent();
/*
        {
                "id": 1,
                "name": "API",
                "description": "This is the Cachet API.",
                "link": "",
                "status": 1,
                "order": 0,
                "group_id": 0,
                "created_at": "2015-07-24 14:42:10",
                "updated_at": "2015-07-24 14:42:10",
                "deleted_at": null,
                "status_name": "Operational",
                "tags": [
                		"slug-of-tag": "Tag Name"
                ]
        }
*/

        JsonNode idNode = node.get("id");
        if (idNode != null) {
            component.setId(idNode.asInt());
        }

        JsonNode nameNode = node.get("name");
        if (nameNode != null) {
            component.setName(nameNode.asText());
        }

        JsonNode descriptionNode = node.get("description");
        if (descriptionNode != null) {
            component.setDescription(descriptionNode.asText());
        }

        JsonNode linkNode = node.get("link");
        if (linkNode != null) {
            component.setLink(linkNode.asText());
        }

        JsonNode statusNode = node.get("status");
        if (statusNode != null) {
            component.setStatus(statusNode.asInt());
        }

        JsonNode orderNode = node.get("order");
        if (orderNode != null) {
            component.setOrder(orderNode.asInt());
        }

        JsonNode groupIdNode = node.get("group_id");
        if (groupIdNode != null) {
            component.setGroupId(groupIdNode.asInt());
        }

        JsonNode createdAtNode = node.get("created_at");
        if (createdAtNode != null) {
            try {
                Date createdDate = DATE_FORMAT.parse(createdAtNode.asText());
                component.setCreatedAt(createdDate);
            } catch (ParseException pex) {
                // Do nothing
            }
        }

        JsonNode updatedAtNode = node.get("updated_at");
        if (updatedAtNode != null) {
            try {
                Date updatedDate = DATE_FORMAT.parse(updatedAtNode.asText());
                component.setUpdatedAt(updatedDate);
            } catch (ParseException pex) {
                // Do nothing
            }
        }

        JsonNode deletedAtNode = node.get("deleted_at");
        if (deletedAtNode != null) {
            try {
                Date deletedDate = DATE_FORMAT.parse(deletedAtNode.asText());
                component.setDeletedAt(deletedDate);
            } catch (ParseException pex) {
                // Do nothing
            }
        }

        JsonNode statusNameNode = node.get("status_name");
        if (statusNameNode != null) {
            component.setStatusName(statusNameNode.asText());
        }

System.out.println("SSDEBUG: parsed component " + component.getId() + " " + component.getName());
        return component;
    }

}
