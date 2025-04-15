package org.example.helpers.api.microservice1;

import org.example.helpers.api.BaseClient;

public class ClientMs1 extends BaseClient {
    public ClientMs1(String uri) {
        super(uri);
        this.needAuth = false;
    }
}