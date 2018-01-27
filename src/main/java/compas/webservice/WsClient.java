package compas.webservice;

import hellios.wsdl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Created by CLLSDJACKT013 on 25/01/2018.
 */
/*
-@Component annotation informs Spring to import this bean into the container if automatic component scan is enabled
-@SpringBootApplication annotation at the entry point of the application performs ComponentScan by default
 */
@Component
public class WsClient {
    @Autowired
    private WebServiceTemplate webServiceTemplate;
    private Logger logger = LoggerFactory.getLogger(WsClient.class);
    //private ObjectFactory factory = new ObjectFactory();


    public Greeting sayHello(String firstName,String lastName){
        ObjectFactory factory = new ObjectFactory();
        Person person = factory.createPerson();
        //person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        Greeting greeting = (Greeting)webServiceTemplate.marshalSendAndReceive(person);
        logger.info("WS said >>>>" +greeting.getGreeting());
        return greeting;
    }
    public DeviceInfo queryDevice(String macAddress,String sNO){
        ObjectFactory factory = new ObjectFactory();
        Device device = factory.createDevice();
        device.setMacAddress(macAddress);
        device.setSNO(sNO);
        DeviceInfo deviceInfo = (DeviceInfo)webServiceTemplate.marshalSendAndReceive(device);
        return deviceInfo;
    }

    public SavedDevice saveDevice(DeviceToSave deviceToSave){
        logger.info("------------------------------");
        logger.info("Inside WS client saveDevice method");
        SavedDevice savedDevice = (SavedDevice) webServiceTemplate.marshalSendAndReceive(deviceToSave);
        logger.info("ok...");
        return savedDevice;
    }
}
