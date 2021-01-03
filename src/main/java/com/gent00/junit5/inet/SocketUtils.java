/*
 * Copyright 2020, Avinash Ramana, All rights reserved.
 */

package com.gent00.junit5.inet;

import javax.net.ssl.*;
import java.io.*;
import java.security.cert.X509Certificate;

public class SocketUtils {

    public static String testSSL(String host, Integer port, boolean trustCerts, String requestInput) throws Exception {
        final SSLContext sslContext = SSLContext.getInstance("SSL");
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }

        }};
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        SSLSocketFactory sslsocketfactory = sslContext.getSocketFactory();

        SSLSocket sslsocket = (SSLSocket) sslsocketfactory
                .createSocket(host, port);
        sslsocket.setKeepAlive(true);
        sslsocket.setUseClientMode(true);
//        sslsocket.setEnabledProtocols(new String[]{"TLSv1.2"});
//        sslsocket.setEnabledCipherSuites(
//                new String[]{
//                        "SSL_DH_anon_EXPORT_WITH_RC4_40_MD5",
//                        "SSL_DH_anon_WITH_RC4_128_MD5",
//                        "SSL_RSA_EXPORT_WITH_RC4_40_MD5",
//                        "SSL_RSA_WITH_RC4_128_MD5",
//                        "SSL_RSA_WITH_RC4_128_SHA",
//                        "TLS_ECDHE_ECDSA_WITH_RC4_128_SHA",
//                        "TLS_ECDHE_RSA_WITH_RC4_128_SHA",
//                        "TLS_ECDH_ECDSA_WITH_RC4_128_SHA",
//                        "TLS_ECDH_RSA_WITH_RC4_128_SHA",
//                        "TLS_ECDH_anon_WITH_RC4_128_SHA",
//                }
//        );
        sslsocket.startHandshake();
        StringBuilder outputLines = new StringBuilder();
        try (PrintWriter socketClientWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sslsocket.getOutputStream())));
             BufferedReader socketServerReader = new BufferedReader(new InputStreamReader(sslsocket.getInputStream()))) {
            socketClientWriter.print(requestInput);
            socketClientWriter.print("Accept: text/plain, text/html, text/*\r\n");
            socketClientWriter.print("\r\n");
            socketClientWriter.flush();
            String serverLine;
            while ((serverLine = socketServerReader.readLine()) != null) {
                outputLines.append(serverLine);
            }
        }

        return outputLines.toString();
    }
}
