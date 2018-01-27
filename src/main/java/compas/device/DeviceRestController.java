package compas.device;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import compas.RestService.RestServiceConfig;
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
@RequestMapping("/lct/rest/")
public class DeviceRestController {
    private DevRepository deviceRepository = new DevRepository();
    private Logger logger = LoggerFactory.getLogger(DeviceRestController.class);
    @Autowired
    private RestServiceConfig restService;
    @RequestMapping(method = RequestMethod.GET,path = "")
    String sendGreeting(){
        return "Hello Garrixx. RestController >>>>>>>>.";
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
    public Device saveDevice(@RequestBody Device device) throws Exception {

        logger.info("received device:::<<< "+device);
        String returnedData = restService.RestServiceConfiguration("/hmis/rest/save_devices","POST",new Gson().toJson(device));
        Device savedDevice = new Device();
        logger.info("Returned data:::>>>>" +returnedData);
        return device;
    }
    @RequestMapping(method = RequestMethod.GET,path = "/test")
    public String testRestService(){
        String returneData = restService.RestServiceConfiguration("/","GET","");
        logger.info("Test returned:::"+returneData);
        return returneData;
    }



}
