package io.cachethq.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Communicate with the Cachet REST interface.
 */
public class CachetClient {

    private String baseUrl = null;
    private Client client = null;
    private WebTarget target = null;

    /**
     * Construct the REST client.
     *
     * @param url   Base URL for the Cachet server
     */
    public CachetClient(String url) {
        baseUrl = url;
        client = ClientBuilder.newClient();
        target = client.target(baseUrl);
    }

    public void reload() {
        target = client.target(baseUrl);
    }

    /**
     * Send a ping request.  If the request fails, an exception will be thrown.
     */
    public void ping() {
        JsonNode rootNode = get("ping");

        // TODO verify that the response was "Pong!"
        JsonNode dataNode = rootNode.get("data");
        String pong = dataNode.asText();
    }

    /**
     * Query the Cachet server to get the version information.
     *
     * @return Cachet version information
     */
    public CachetVersion getVersion() {
        JsonNode rootNode = get("version");
        CachetVersion version = CachetVersion.parseRootNode(rootNode);

System.out.println("SSDEDBUG: version = " + version.getVersion() + " (latest=" + version.isLatest() + ")");
        return version;
    }

    /**
     * Get the list of components.
     *
     * @return Cachet components
     */
    public CachetComponentList getComponents() {
        JsonNode rootNode = get("components");
        CachetComponentList compList = CachetComponentList.parseRootNode(rootNode);
        return compList;
    }

    /**
     * Get the list of component groups.
     *
     * @return Cachet component groups
     */
    public CachetComponentGroupList getComponentGroups() {
        throw new RuntimeException("Method not implemented.");
    }

    /**
     * Get the list of Cachet incidents.
     *
     * @return Cachet incident list
     */
    public CachetIncidentList getIncidents() {
        throw new RuntimeException("Method not implemented.");
    }

    /**
     * Get the list of incident updates.
     *
     * @return Cachet incident updates
     */
    public CachetIncidentUpdateList getIncidentUpdates() {
        throw new RuntimeException("Method not implemented.");
    }

    /**
     * Get the list of metrics.
     *
     * @return Cachet metrics
     */
    public CachetMetricList getMetrics() {
        throw new RuntimeException("Method not implemented.");
    }

    /**
     * Get the list of subscribers.
     *
     * @return Cachet subscribers
     */
    public CachetSubscriberList getSubscribers() {
        throw new RuntimeException("Method not implemented.");
    }

    /**
     * Get the list of actions.
     *
     * @return Cachet actions
     */
    public CachetActionList getActions() {
        throw new RuntimeException("Method not implemented.");
    }


    /**
     * Perform a GET request and return the response as a string.
     *
     * @param  path  Request path
     * @return Response body
     * @throws ResponseProcessingException if the request is not successful
     */
    private JsonNode get(String path) throws ResponseProcessingException {
        JsonNode rootNode = null;

        WebTarget webTarget = target.path(path);
        Invocation.Builder builder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
        Response restResponse = builder.get();

        Response.StatusType responseInfo = restResponse.getStatusInfo();
        if (responseInfo.getFamily() == Response.Status.Family.SUCCESSFUL) {
            InputStream inputStream = (InputStream) restResponse.getEntity();

            // Convert the input stream to a string
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            String json = null;
            try {
                while ((length = inputStream.read(buffer)) != -1) {
                    result.write(buffer, 0, length);
                }
                json = result.toString(StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException exEnc) {
                throw new ResponseProcessingException(restResponse, "Invalid character encoding for response stream.");
            } catch (IOException exIO) {
                throw new ResponseProcessingException(restResponse, "Failed to read from the response stream.");
            }


            // Parse the JSON to an object
            try {
                ObjectMapper mapper = new ObjectMapper();
                rootNode = mapper.readTree(json);
            } catch (IOException exIO) {
                throw new ResponseProcessingException(restResponse, "Failed to parse JSON response body.");
            }

        } else {
            throw new ResponseProcessingException(restResponse, "GET operation was unsuccessful");
        }

        return rootNode;
    }

}
