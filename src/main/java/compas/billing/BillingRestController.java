package compas.billing;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by CLLSDJACKT013 on 29/01/2018.
 */
@RestController
@RequestMapping("/lct/rest")
public class BillingRestController {

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String senTestInfo(){
        return "ok";
    }
}
