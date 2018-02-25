package net.kozon.selenium.example.test.framework.common.docker;


import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.ContainerCreation;
import com.spotify.docker.client.messages.HostConfig;
import com.spotify.docker.client.messages.PortBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class EnvironmentOnDocker {


    private static Logger logger = LoggerFactory.getLogger(EnvironmentOnDocker.class);

    private static DockerClient dockerClient;

    private static final String DOCKER_IMAGE_OF_APP = "gprestes/the-internet";

    public final String startDockerClient() throws InterruptedException, DockerException, DockerCertificateException {
        dockerClient = DefaultDockerClient.fromEnv().build();

        dockerClient.pull(DOCKER_IMAGE_OF_APP);

        String[] ports = {"5000"};
        Map<String, List<PortBinding>> portBindings = new HashMap<>();

        for (String port : ports) {
            List<PortBinding> hostPorts = new ArrayList<>();
            hostPorts.add(PortBinding.of("0.0.0.0/7080", port));
            portBindings.put(port, hostPorts);
        }

        HostConfig hostConfig = HostConfig.builder().portBindings(portBindings).build();

        ContainerConfig containerConfig = ContainerConfig.builder()
                .hostConfig(hostConfig)
                .image(DOCKER_IMAGE_OF_APP)
                .exposedPorts(ports)
                .build();

        ContainerCreation creation = dockerClient.createContainer(containerConfig);
        String id = creation.id();

        dockerClient.startContainer(id);

        TimeUnit.SECONDS.sleep(10);

        return id;
    }

    public final void stopAndRemoveDockerClient(String id) {
        try {
            dockerClient.stopContainer(id, 5);
            dockerClient.removeContainer(id);
        } catch (DockerException | InterruptedException e) {
            logger.error("Container has not been stopped or removed!", e);
        }
    }
}
