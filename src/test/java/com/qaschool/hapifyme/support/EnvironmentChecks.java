package com.qaschool.hapifyme.support;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

public final class EnvironmentChecks {
    private EnvironmentChecks() {
    }

    public static boolean baseUrlHostCanResolve(String baseUrl) {
        try {
            URI uri = URI.create(baseUrl);
            String host = uri.getHost();
            return host != null && InetAddress.getByName(host) != null;
        } catch (IllegalArgumentException | UnknownHostException exception) {
            return false;
        }
    }
}