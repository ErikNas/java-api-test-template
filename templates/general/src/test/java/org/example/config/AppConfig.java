package org.example.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${STAND}.properties",
        "classpath:stage.properties"})
public interface AppConfig extends Config {
    @Key("stand")
    String stand();

    @Key("uri")
    String uri();

    @Key("microservice2.token")
    String microservice2Token();
}
