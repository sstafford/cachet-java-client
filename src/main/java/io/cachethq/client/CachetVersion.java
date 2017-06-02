package io.cachethq.client;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Cachet server version information.
 *
 */
public class CachetVersion {
    private String version = null;
    private boolean isLatest = true;


    /**
     * Construct the version.
     */
    public CachetVersion() {
    }

    /**
     * Set the Cachet version string.
     *
     * @param  version  Version string
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Return the Cachet version string.
     *
     * @return Version string
     */
    public String getVersion() {
        return version;
    }

    /**
     * Set a boolean to indicate whether this version is the latest available
     * version.
     *
     * @param  latest  TRUE if this is the latest version
     */
    public void setLatest(boolean latest) {
        isLatest = latest;
    }

    /**
     * Set a boolean to indicate whether this version is the latest available
     * version.
     *
     * @return  TRUE if this is the latest version
     */
    public boolean isLatest() {
        return isLatest;
    }

    /**
     * Parse the JSON and return a version object.
     *
     * @param root   Root of the JSON tree
     * @return Version information
     */
    public static CachetVersion parseRootNode(JsonNode node) {
        CachetVersion version = new CachetVersion();

        JsonNode metaNode = node.get("meta");
        JsonNode dataNode = node.get("data");

        version.setVersion(dataNode.asText());
        JsonNode onLatestNode = metaNode.get("on_latest");
        if (onLatestNode != null) {
            version.setLatest(onLatestNode.asBoolean());
        }

        return version;
    }

}
