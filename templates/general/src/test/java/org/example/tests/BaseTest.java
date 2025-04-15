package org.example.tests;

import org.example.helpers.api.microservice1.ClientMs1;
import org.example.helpers.api.microservice2.ClientMs2;

import static org.example.config.AppConfigProvider.props;

public class BaseTest {

    public static ClientMs1 clientMs1;
    public static ClientMs2 clientMs2;

    static {
        clientMs1 = new ClientMs1(props().uri());
        clientMs2 = new ClientMs2(props().uri());
    }

}
