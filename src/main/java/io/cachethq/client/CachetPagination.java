package io.cachethq.client;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Date;


/**
 * The pagination metadata provides additional information about list results
 * that are returned by the server in batches.
 */
public class CachetPagination {
    private int total = 0;
    private int count = 0;
    private int perPage = 0;
    private int currentPage = 0;
    private int totalPages = 0;
    private String nextPage = null;
    private String previousPage = null;


    /**
     * Construct the component.
     */
    public CachetPagination() {
    }

    /**
     * Return the total number of pages.
     *
     * @return Number of pages
     */
    public int getTotal() {
        return total;
    }

    /**
     * Set the total number of pages.
     *
     * @param  total   Number of pages
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Return the total number of entries.
     *
     * @return Number of entries
     */
    public int getCount() {
        return count;
    }

    /**
     * Set the total number of entries.
     *
     * @param  count   Number of entries
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Return the total number of entries per page.
     *
     * @return Number of entires
     */
    public int getEntriesPerPage() {
        return perPage;
    }

    /**
     * Set the total number of entries per page.
     *
     * @param  count   Number of entries
     */
    public void setEntriesPerPage(int count) {
        this.perPage = count;
    }

    /**
     * Return the current page
     *
     * @return Page number
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * Set the current page
     *
     * @param  page   Page number
     */
    public void setCurrentPage(int page) {
        this.currentPage = page;
    }

    /**
     * Return the total number of pages
     *
     * @return Number of pages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * Set the total number of pages
     *
     * @param  count   Number of pages
     */
    public void setTotalPages(int count) {
        this.totalPages = count;
    }

    /**
     * Set the link to the next page.
     *
     * @param  link   Next page
     */
    public void setNextPage(String link) {
        this.nextPage = link;
    }

    /**
     * Return the link to the next page.
     *
     * @return Next page
     */
    public String getNextPage() {
        return nextPage;
    }

    /**
     * Set the link to the previous page.
     *
     * @param  link   Previous page
     */
    public void setPreviousPage(String link) {
        this.previousPage = link;
    }

    /**
     * Return the link to the previous page.
     *
     * @return Previous page
     */
    public String getPreviousPage() {
        return previousPage;
    }

    /**
     * Parse the JSON and return a pagination object.
     *
     * @param node   Meta node of the JSON tree
     * @return Version information
     */
    public static CachetPagination parseMetaNode(JsonNode node) {
        CachetPagination pagination = new CachetPagination();

        JsonNode pageNode = node.get("pagination");
        if (pageNode != null) {
            JsonNode totalNode = pageNode.get("total");
            if (totalNode != null) {
                pagination.setTotal(totalNode.asInt());
            }

            JsonNode countNode = pageNode.get("count");
            if (countNode != null) {
                pagination.setCount(countNode.asInt());
            }

            JsonNode perPageNode = pageNode.get("per_page");
            if (perPageNode != null) {
                pagination.setEntriesPerPage(perPageNode.asInt());
            }

            JsonNode currentPageNode = pageNode.get("current_page");
            if (currentPageNode != null) {
                pagination.setCurrentPage(currentPageNode.asInt());
            }

            JsonNode totalPagesNode = pageNode.get("total_pages");
            if (totalPagesNode != null) {
                pagination.setTotalPages(totalPagesNode.asInt());
            }

            JsonNode linksNode = pageNode.get("links");
            if (linksNode != null) {
                JsonNode nextPageNode = pageNode.get("next_page");
                if (nextPageNode != null) {
                    pagination.setNextPage(nextPageNode.asText());
                }

                JsonNode prevPageNode = pageNode.get("previous_page");
                if (prevPageNode != null) {
                    pagination.setPreviousPage(prevPageNode.asText());
                }
            }
        }

        return pagination;
    }


}
