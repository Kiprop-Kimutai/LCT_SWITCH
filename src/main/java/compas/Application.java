package compas;

import compas.device.DevRepository;
import compas.device.Device;
import compas.webservice.WsClient;
import hellios.wsdl.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.logging.Logger;


/**
 * Created by CLLSDJACKT013 on 25/01/2018.
 */
@SpringBootApplication
public class Application{
    private org.slf4j.Logger logger = LoggerFactory.getLogger(Application.class);
    private DevRepository deviceRepository = new DevRepository();
    private ObjectFactory factory = new ObjectFactory();
    @Autowired
    private WsClient client;
    public static void main(String [] args){
        System.out.println("Init....");
        SpringApplication.run(Application.class,args);
    }
    @Bean
    CommandLineRunner run(){
            return args ->{
/*            ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
            MongoOperations mongoOperations = (MongoOperations)ctx.getBean("mongoTemplate");
            Device saral = new Device("00:25:7E:03","82134","david guetta","metallica");
            mongoOperations.save(saral,"devices");

            //find one device by criteria - macaddress
            Query findQuery = new Query();
            findQuery.addCriteria(Criteria.where("macAddress").is("00:25:7E:03"));
            Device foundDevice = mongoOperations.findOne(findQuery,Device.class,"devices");
            logger.info(foundDevice.getString());*/

            logger.info("querying one device");
            logger.info("---------------------------------------");
            Query findOneDeviceQuery = new Query();
            findOneDeviceQuery.addCriteria(Criteria.where("macAddress").is("00:25:7E:03"));
            logger.info("Returned device::::" +deviceRepository.findOne(findOneDeviceQuery).getString());

            logger.info("Testing LCT WS and WS-Client");
            logger.info("-------------------------------------------");
            Greeting greeting = client.sayHello("Dimitri","Vegas");
            logger.info("WebService returned >>>>>" +greeting.getGreeting());

            DeviceInfo deviceInfo = client.queryDevice("00:25:7E:03","824123");
            logger.info("WebService returned device  >>>>>" +deviceInfo.getMacAddress() + " " +deviceInfo.getSNo() + " " +deviceInfo.getRetailer());

            logger.info("--------------------------------------------");
            logger.info("make or break");
                DeviceToSave deviceToSave = factory.createDeviceToSave();
                deviceToSave.setMacAddress("00:25:7E:03");
                deviceToSave.setSerial("xxxx");
                deviceToSave.setRetailer("Tiesto");
                deviceToSave.setAgency("Humlot");
               SavedDevice savedDevice =  client.saveDevice(deviceToSave);
               logger.info("success....>>>");
               System.out.println(String.format("savedDevice[macaddress = %s  serial = %s  retailer = %s  agency = %s]",savedDevice.getMacAddress(),savedDevice.getSerial(),savedDevice.getRetailer(),savedDevice.getAgency()));
        };
    }
}
