package org.example.helpers.api.microservice2;

import org.example.config.AppConfigProvider;
import org.example.helpers.api.BaseClient;

public class ClientMs2 extends BaseClient {
    public ClientMs2(String uri) {
        super(uri);
        this.needAuth = true;
        this.token = AppConfigProvider.props().microservice2Token();
    }
}