package io.cachethq.client;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Cachet metric information.
 *
 */
public class CachetMetric {

    /**
     * Construct the metric.
     */
    public CachetMetric() {
    }

    /**
     * Parse the JSON and return a metric object.
     *
     * @param root   Root of the JSON tree
     * @return Metric information
     */
    public static CachetMetric parseMetricNode(JsonNode node) {
        CachetMetric metric = new CachetMetric();
        // TODO
        return metric;
    }

}
