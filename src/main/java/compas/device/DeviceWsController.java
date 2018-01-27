package compas.device;

import compas.webservice.WsClient;
import hellios.wsdl.DeviceToSave;
import hellios.wsdl.SavedDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CLLSDJACKT013 on 25/01/2018.
 */
@RestController
@RequestMapping("/lct/ws/")
public class DeviceWsController {
    private DevRepository deviceRepository = new DevRepository();
    private Logger logger = LoggerFactory.getLogger(DeviceWsController.class);
    @Autowired
    private WsClient wsClient;
    @RequestMapping(method = RequestMethod.GET,path = "")
    String sendGreeting(){
        return "Hello Garrixx";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getdevice")
    Device findDeviceByMac(@RequestParam(value = "mac") String macaddress){
        //invoke device repository and return device
        return deviceRepository.findByMac(macaddress);

    }
    @RequestMapping(method = RequestMethod.POST,path = "/saveDevices")
    List<Device> saveDevices(@RequestBody ArrayList<Device> devicesToSave){
        logger.info("-------------------------------------------------");
        logger.info("Save devices request received");
        //redirect the method to WebService client to POST the devices to WebService
        return devicesToSave;
    }
    @RequestMapping(method = RequestMethod.POST, path = "/savedevice")
    public SavedDevice savedDevice(@RequestBody DeviceToSave deviceToSave){
        logger.info("---------------------------------------------------");
        logger.info("save device request received");
        //redirect the method to WebService client method saveDevice
        logger.info(deviceToSave.getMacAddress());
        System.out.println(String.format("deviceToSave[macAddress = %s   sNo = %s    retailer = %s   agency = %s]",deviceToSave.getMacAddress(),deviceToSave.getSerial(),deviceToSave.getRetailer(),deviceToSave.getAgency()));
        SavedDevice savedDevice= wsClient.saveDevice(deviceToSave);
        return savedDevice;
    }



}
