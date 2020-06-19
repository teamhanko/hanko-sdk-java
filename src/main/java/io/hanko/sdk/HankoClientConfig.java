package io.hanko.sdk;

public class HankoClientConfig {
    private String apiUrl;
    private String apiKeyId;
    private String apiKey;
    private boolean isProxyEnabled;
    private String proxyAddress;
    private int proxyPort;
    private String proxyType;

    public HankoClientConfig(String apiUrl, String apiKey) {
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
    }

    public HankoClientConfig(String apiUrl, String apiKeyId, String apiKey) {
        this.apiUrl = apiUrl;
        this.apiKeyId = apiKeyId;
        this.apiKey = apiKey;
    }

    public HankoClientConfig(String apiUrl, String apiKey, String proxyAddress, int proxyPort, String proxyType) {
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
        this.isProxyEnabled = true;
        this.proxyAddress = proxyAddress;
        this.proxyPort = proxyPort;
        this.proxyType = proxyType;
    }

    public HankoClientConfig(String apiUrl, String apiKeyId, String apiKey, String proxyAddress, int proxyPort, String proxyType) {
        this.apiUrl = apiUrl;
        this.apiKeyId = apiKeyId;
        this.apiKey = apiKey;
        this.isProxyEnabled = true;
        this.proxyAddress = proxyAddress;
        this.proxyPort = proxyPort;
        this.proxyType = proxyType;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getApiKeyId() {
        return apiKeyId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public boolean isProxyEnabled() {
        return isProxyEnabled;
    }

    public String getProxyAddress() {
        return proxyAddress;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public String getProxyType() {
        return proxyType;
    }
}
