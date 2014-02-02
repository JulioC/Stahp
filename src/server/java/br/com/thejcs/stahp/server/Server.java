package br.com.thejcs.stahp.server;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ext.ContextResolver;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


public class Server extends ResourceConfig {

    private static Logger logger;

    public static final String BASE_URI = "http://0.0.0.0:8080/stahp/";

    public static void main(String[] args) throws IOException {
        // Configure and initialize Log4J
        PropertyConfigurator.configure("src/server/log4j.properties");
        logger = Logger.getLogger(Server.class.getName());

        try {
            final HttpServer server = create();
            logger.info("Server started");

            System.out.println(
                    String.format(
                            "Server started on %s%nPress ENTER to stop it...",
                            BASE_URI));
            System.in.read();
            server.shutdownNow();
        } catch (IOException ex) {
            logger.error(ex);
        }
    }

    /**
     * Create and configure the Grizzly server to work with Jersey and Spring
     *
     * @return HttpServer
     */
    public static HttpServer create() {
        // Jersey configuration
        final ResourceConfig rc = new ResourceConfig()
                // Base package for public resources
                .packages(false, "br.com.thejcs.stahp.server.resource")
                // Send validation errors to the client.
//                .property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true)
                // Add JSON media output
                .register(createMoxyJsonResolver());

        // Create Grizzly server
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

        // Initialize and add Spring-aware Jersey resource
        WebappContext ctx = new WebappContext("context", "/");
        ServletRegistration reg = ctx.addServlet("spring", org.glassfish.jersey.servlet.ServletContainer.class);
        reg.addMapping("/*");
        ctx.addContextInitParameter("contextConfigLocation", "classpath:applicationContext.xml");
        ctx.addListener("org.springframework.web.context.ContextLoaderListener");
        ctx.addListener("org.springframework.web.context.request.RequestContextListener");
        ctx.deploy(server);

        return server;
    }

    /**
     * Initialize the MoxyJson resolver
     *
     * @return ContextResolver used with Jersey's ResourceConfig
     */
    private static ContextResolver<MoxyJsonConfig> createMoxyJsonResolver() {
        final MoxyJsonConfig moxyJsonConfig = new MoxyJsonConfig();

        Map<String, String> namespacePrefixMapper = new HashMap<String, String>(1);
        namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");

        moxyJsonConfig.setNamespacePrefixMapper(namespacePrefixMapper).setNamespaceSeparator(':');

        return moxyJsonConfig.resolver();
    }

}

