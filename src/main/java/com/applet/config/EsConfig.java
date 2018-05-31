package com.applet.config;

import com.applet.utils.common.CommonUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class EsConfig {


    @Value("${spring.data.elasticsearch.ip}")
    public String  esIp;

    @Value("${spring.data.elasticsearch.port}")
    public Integer esPort;

    @Value("${spring.data.elasticsearch.cluster-name}")
    public String clusterName;

    @Bean
    public TransportClient transportClient() throws UnknownHostException {
        InetSocketTransportAddress node = new InetSocketTransportAddress(
                InetAddress.getByName(esIp),
//                InetAddress.getByName("localhost"),
                esPort
        );

        Settings settings = Settings.builder()
                .put("cluster.name", clusterName)
                .build();
        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(node);
        return client;
    }
}
