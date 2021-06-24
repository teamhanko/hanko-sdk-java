package io.hanko.sdk.webauthn.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a query for filtering and pagination of results when
 * {@link io.hanko.sdk.webauthn.HankoWebAuthnClient#listCredentials(CredentialQuery)
 * retrieving a list of credentials}.
 */
public class CredentialQuery {
    private Integer page;
    private Integer pageSize;
    private String userId;

    /**
     * Construct a CredentialQuery.
     */
    public CredentialQuery() {
    }

    /**
     * Construct a CredentialQuery
     * @param page nullable, the desired page to return from the result set
     * @param pageSize nullable, the page size of the returned result set
     * @param userId nullable, the userId to filter by
     */
    @JsonCreator
    public CredentialQuery(
            @JsonProperty("page") Integer page,
            @JsonProperty("page_size") Integer pageSize,
            @JsonProperty("user_id") String userId) {
        this.page = page;
        this.pageSize = pageSize;
        this.userId = userId;
    }

    /**
     * Get the page.
     * @return the page size as an Integer
     */
    public Integer getPage() {
        return page;
    }

    /**
     * Set the page to return from the result set.
     * @param page the page as an Integer
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * Get the page size.
     * @return the page size as an Integer
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * Set the page size.
     * @param pageSize the page size as an Integer
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Get the user ID.
     * @return the user ID as a String
     */
    public String getUserid() {
        return userId;
    }

    /**
     * Set the user ID.
     * @param userId the user ID as a String
     */
    public void setUserid(String userId) {
        this.userId = userId;
    }

    /**
     * Return a query string for the query.
     * <p>
     * Only non-{@code null} fields become part of the resulting query string.
     * @return the query string
     */
    public String toQueryString() {
        List<BasicNameValuePair> params = new ArrayList<>();
        if (userId != null && !userId.isEmpty()) {
            params.add(new BasicNameValuePair("user_id", userId));
        }
        if (page != null) {
            params.add(new BasicNameValuePair("page", Integer.toString(page)));
        }
        if (pageSize != null) {
            params.add(new BasicNameValuePair("page_size", Integer.toString(pageSize)));
        }

        if (!params.isEmpty()) {
            return "?" + URLEncodedUtils.format(params, Charset.forName(StandardCharsets.UTF_8.name()));
        } else {
            return "";
        }
    }
}
