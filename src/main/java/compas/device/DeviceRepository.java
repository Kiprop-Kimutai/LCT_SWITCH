package compas.device;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;

/**
 * Created by CLLSDJACKT013 on 25/01/2018.
 */
@RepositoryRestResource(collectionResourceRel = "people",path = "people")
public interface DeviceRepository extends MongoRepository<Device,String>{
    List<Device> findAll();
}
