//package compas.webservice;
//
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.ws.config.annotation.EnableWs;
//import org.springframework.ws.config.annotation.WsConfigurerAdapter;
//import org.springframework.ws.transport.http.MessageDispatcherServlet;
//import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
//import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
//
///**
// * Created by CLLSDJACKT013 on 25/01/2018.
// */
//@EnableWs
//@Configuration
//public class WebServiceConfig extends WsConfigurerAdapter {
//
//    /*
//    -use the ServletRegistration bean to set and configure MessageDispatcher servlet, which provides communication/support for http protocols
//    -in other words, MessageDispatcher servlet adds support for http
//    -inject ApplicationContect to this config so that other WebService beans related to the application can be found
//     */
//    @Bean
//    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext){
//        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
//        servlet.setApplicationContext(applicationContext);
//        return new ServletRegistrationBean(servlet,"/codenotfound/ws/");
//    }
//
//    /*
//    -this method exposes default WSDL 1.1 definitions to be used by the application
//    -Bean name param is the name of the wsdl file
//     */
//    @Bean(name = "lct")
//    public Wsdl11Definition wsdl11Definition(){
//        SimpleWsdl11Definition simpleWsdl11Definition = new SimpleWsdl11Definition();
//        simpleWsdl11Definition.setWsdl(new ClassPathResource("/wsdl/lct.wsdl"));
//        return simpleWsdl11Definition;
//    }
//}
