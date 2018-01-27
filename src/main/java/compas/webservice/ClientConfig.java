package compas.webservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Created by CLLSDJACKT013 on 25/01/2018.
 */
/*
-@Configuration annotation is used by Spring IoC container as a source of Bean definitions
 */
@Configuration
public class ClientConfig {
    @Value("http://localhost:9000/codenotfound/ws/lct")
    String defaultUri;
    /*
    -WebServiceTemplate is the core class for client-side WS access in Spring
    -It is used to send and receive responses
    -Additionally it can Marshal and UnMarshal objects into and from XML
     */
    @Bean
    public WebServiceTemplate webServiceTemplate(){
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(jaxb2Marshaller());
        webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
        webServiceTemplate.setDefaultUri(defaultUri);
        return webServiceTemplate;
    }
    /*
    -we will use Jaxb2Marshaller to serialize and deserialize objects into and from XML
    -Set contextPath is th name of the derived package from the wsdl file as defined in pom.xml
     */
    @Bean
    public Jaxb2Marshaller jaxb2Marshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("hellios.wsdl");
        return jaxb2Marshaller;
    }
}
