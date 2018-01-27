//package compas.webservice;
//
//import hellios.wsdl.Device;
//import hellios.wsdl.DeviceInfo;
//import hellios.wsdl.Greeting;
//import hellios.wsdl.Person;
//import org.springframework.ws.server.endpoint.annotation.Endpoint;
//import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
//import org.springframework.ws.server.endpoint.annotation.RequestPayload;
//import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
//
///**
// * Created by CLLSDJACKT013 on 25/01/2018.
// */
///*
//-this annotation identifies the class as a potential candidate to handle incoming SOAP requests
// */
//@Endpoint
//public class WsEndPoints {
//
//    /*
//    -to indicate what type of messages can be hold, @PayloadRoot annotation provides a qualified name; namespace + localPart name.
//    -if incoming messages match th qualified name, the method is invoked
//    -@ResponsePayload maps the response to the correct object, which is the output message defined in the method's portType config
//    -@RequestPayload annotation demands that the request object be mapped to the defined request object
//     */
//    @PayloadRoot(namespace = "http://codenotfound.com/types/lct", localPart = "device")
//    @ResponsePayload
//    public DeviceInfo queryDevice(@RequestPayload Device requestDevice){
//        DeviceInfo device = new DeviceInfo();
//        device.setMacaddress("00:25:7E:03");
//        device.setSNo("825674");
//        device.setRetailer("Tiesto");
//        device.setRetailer("Techno");
//        device.setAgency("Amsterdam");
//        return device;
//    }
//
//    @PayloadRoot(namespace = "http://codenotfound.com/types/lct", localPart = "person")
//    @ResponsePayload
//    public Greeting sayHello(@RequestPayload Person person){
//        Greeting greeting = new Greeting();
//        String greetingMsg = String.format("Guten Morgen " +person.getFirstName() +person.getLastName());
//        greeting.setGreeting(greetingMsg);
//        return greeting;
//    }
//}
