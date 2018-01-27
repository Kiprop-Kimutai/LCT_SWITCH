package compas.device;

import com.mongodb.MongoException;
import compas.MongoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by CLLSDJACKT013 on 25/01/2018.
 */

public class DevRepository{
    ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
    MongoOperations mongoOperations =(MongoOperations) ctx.getBean("mongoTemplate");
    private Logger logger = LoggerFactory.getLogger(DevRepository.class);

    public Device saveDevice(Device device) throws Exception {
        //use MongoOperations to manipulate device
        try {
            mongoOperations.save(device, "devices");

        } catch (MongoException e) {
            e.printStackTrace();
        }
        return device;
    }
    public List<Device> insertDevices(List<Device> devices){
        try{
            mongoOperations.insert(devices,Device.class);
        }
        catch(MongoException e){
            e.printStackTrace();
        }
        return devices;
    }
    @Bean
    public List<Device> findAll(){
        List<Device> allDevices = null;
        try{
           allDevices =  mongoOperations.findAll(Device.class);
        }
        catch (MongoException e){
            e.printStackTrace();
        }
        return allDevices;
    }

    public Device findOne(Query query){
        System.out.println("findOne method Init......");
       Device foundDevice = null;
        try{
             foundDevice = mongoOperations.findOne(query,Device.class,"devices");
            logger.info(foundDevice.getString());
        }
        catch(MongoException e){
            e.printStackTrace();
        }
        return foundDevice;
    }
    Device findByMac(String macAddress){
        logger.info("method loading...");
        Device foundDevice = null;
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("macAddress").is(macAddress));
            foundDevice = mongoOperations.findOne(query,Device.class,"devices");
            logger.info("found device  " +foundDevice.getString());
        }
        catch(MongoException e){
            e.printStackTrace();
        }
        return foundDevice;
    }
}
