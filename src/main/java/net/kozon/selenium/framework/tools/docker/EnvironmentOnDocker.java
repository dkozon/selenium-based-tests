package net.kozon.selenium.framework.tools.docker;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.ContainerCreation;
import com.spotify.docker.client.messages.HostConfig;
import com.spotify.docker.client.messages.PortBinding;
import net.kozon.selenium.framework.tools.utils.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class EnvironmentOnDocker {

    private static final String DOCKER_IMAGE_OF_APP = Configuration.getPropertyFromFile("dockerAppImage");
    private static final String[] PORTS = {Configuration.getPropertyFromFile("dockerAppPort")};
    private static Logger logger = LoggerFactory.getLogger(EnvironmentOnDocker.class);
    private static DockerClient dockerClient;

    public final String startDockerClient() throws InterruptedException, DockerException, DockerCertificateException {
        dockerClient = DefaultDockerClient.fromEnv().build();

        dockerClient.pull(DOCKER_IMAGE_OF_APP);

        Map<String, List<PortBinding>> portBindings = new HashMap<>();

        for (String port : PORTS) {
            List<PortBinding> hostPorts = new ArrayList<>();
            hostPorts.add(PortBinding.of("0.0.0.0", port));
            portBindings.put(port, hostPorts);
        }

        HostConfig hostConfig = HostConfig.builder().portBindings(portBindings).build();

        ContainerConfig containerConfig = ContainerConfig.builder()
                .hostConfig(hostConfig)
                .image(DOCKER_IMAGE_OF_APP)
                .exposedPorts(PORTS)
                .build();

        ContainerCreation creation = dockerClient.createContainer(containerConfig);
        String id = creation.id();

        dockerClient.startContainer(id);

        TimeUnit.SECONDS.sleep(30);

        return id;
    }

    public final boolean stopAndRemoveDockerClient(String containerId) {
        try {
            dockerClient.stopContainer(containerId, 5);
            dockerClient.removeContainer(containerId);
            return true;
        } catch (DockerException | InterruptedException e) {
            logger.error("Problem with closing or removing container!", e);
            return false;
        }
    }
}
