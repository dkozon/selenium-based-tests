package net.kozon.selenium.framework.commons.grid;

import org.openqa.grid.common.RegistrationRequest;
import org.openqa.grid.internal.utils.SelfRegisteringRemote;
import org.openqa.grid.internal.utils.configuration.GridHubConfiguration;
import org.openqa.grid.internal.utils.configuration.GridNodeConfiguration;
import org.openqa.grid.shared.GridNodeServer;
import org.openqa.grid.web.Hub;
import org.openqa.selenium.remote.server.SeleniumServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestOnGrid {

    private static Logger logger = LoggerFactory.getLogger(TestOnGrid.class);

    private static Hub hub;
    private static GridNodeServer node;
    private static SelfRegisteringRemote remote;

    public TestOnGrid() {
        hub = new Hub(GridHubConfiguration.loadFromJSON("selenium-grid-hub-config.json"));
        RegistrationRequest registrationRequest = new RegistrationRequest(GridNodeConfiguration.loadFromJSON("selenium-grid-node-config.json"));
        node = new SeleniumServer(registrationRequest.getConfiguration());
        remote = new SelfRegisteringRemote(registrationRequest);
    }

    public boolean runHub() {
        try {
            hub.start();
            return true;
        } catch (Exception e) {
            logger.error("Cannot establish Selenium Grid HUB ", e);
            return false;
        }
    }

    public boolean stopHub() {
        try {
            hub.stop();
            return true;
        } catch (Exception e) {
            logger.error("Cannot stop Selenium Grid HUB ", e);
            return false;
        }
    }

    public boolean runNode() {
        remote.setRemoteServer(node);
        try {
            remote.startRemoteServer();
            remote.sendRegistrationRequest();
            remote.startRegistrationProcess();
            remote.setTimeout(1800, 5);
            return true;
        } catch (Exception e) {
            logger.error("Cannot establish Selenium Grid NODE", e);
            return false;
        }
    }

    public void stopNode() {
        remote.stopRemoteServer();
    }
}
