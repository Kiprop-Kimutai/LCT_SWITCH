package compas.device;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by CLLSDJACKT013 on 25/01/2018.
 */
@Document(collection = "devices")
public class Device {
    @Id
    private String id;
    private String macAddress;
    private String serial;
    private String retailer;
    private  String agency;

    //default class for database implementation
      public Device (){}

    public Device(String macAddress,String serial,String retailer,String agency){
        this.macAddress = macAddress;
        this.serial = serial;
        this.retailer = retailer;
        this.agency = agency;
    }

    public void setMacAddress(String macAddress){
        this.macAddress = macAddress;
    }
    public String getMacAddress(){
        return macAddress;
    }
    public void setSerial(String serial){
        this.serial = serial;
    }
    public String getSerial(){
        return serial;
    }
    public void setRetailer(String retailer){
        this.retailer = retailer;
    }
    public String getRetailer(){
        return retailer;
    }
    public void setAgency(String agency){
        this.agency = agency;
    }
    public String getAgency(){
        return agency;
    }

    public String getString(){
        return String.format("device[macaddress = %s  serialNo = %s   retailer = %s agency = %s]",macAddress,serial,retailer,agency);
    }

}
