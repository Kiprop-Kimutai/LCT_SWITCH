package compas;

/**
 * Created by CLLSDJACKT013 on 25/01/2018.
 */

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    public @Bean
    MongoTemplate mongoTemplate() throws Exception{
        MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient("127.0.0.1"),"lct");
        return mongoTemplate;
    }
}
