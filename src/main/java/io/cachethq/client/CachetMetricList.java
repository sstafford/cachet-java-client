package io.cachethq.client;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Cachet metric information.
 *
 */
public class CachetMetricList {
    private CachetPagination pagination = null;
    private ArrayList<CachetMetric> metrics = new ArrayList<CachetMetric>();

    /**
     * Construct the metric list.
     */
    public CachetMetricList() {
    }

    /**
     * Set the pagination information.
     */
    public void setPagination(CachetPagination pagination) {
        this.pagination = pagination;
    }

    /**
     * Add a metric to the list.
     *
     * @param  metric   Cachet metric
     */
    public void add(CachetMetric metric) {
        metrics.add(metric);
    }

    /**
     * Return the list of metrics.
     *
     * @return List of metrics.
     */
    public List<CachetMetric> getMetrics() {
        return metrics;
    }

    /**
     * Return the number of metrics in the list.
     *
     * @return Number of metrics currently populated
     */
    public int getMetricCount() {
        return metrics.size();
    }


    /**
     * Parse the JSON and return an incident update list
     *
     * @param node   Root of the JSON tree
     * @return Incident update list
     */
    public static CachetMetricList parseRootNode(JsonNode node) {
        CachetMetricList metricList = new CachetMetricList();

        JsonNode metaNode = node.get("meta");
        if (metaNode != null) {
            CachetPagination pagination = CachetPagination.parseMetaNode(metaNode);
            metricList.setPagination(pagination);
        }

        JsonNode dataNode = node.get("data");
        Iterator<JsonNode> iter = dataNode.elements();
        while (iter.hasNext()) {
            CachetMetric metric = CachetMetric.parseMetricNode(iter.next());
            metricList.add(metric);
        }

        return metricList;
    }

}
