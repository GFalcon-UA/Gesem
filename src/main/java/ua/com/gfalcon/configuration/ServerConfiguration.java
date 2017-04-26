package ua.com.gfalcon.configuration;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class ServerConfiguration implements EmbeddedServletContainerCustomizer {


    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        setServerPort(container);
    }

    private void setServerPort(ConfigurableEmbeddedServletContainer container) {
        String port = System.getenv("GESEM_SERVER_PORT");
        if (port != null && !port.equals("")) {
            int serverPort = Integer.parseInt(port);
            if (serverPort > 0) {
                container.setPort(serverPort);
            }
        }
    }
}
