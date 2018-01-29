package compas.billing;

import compas.MongoConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by CLLSDJACKT013 on 29/01/2018.
 */
public class BillingRepository {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
    MongoOperations mongoOperations = (MongoOperations) ctx.getBean("mongoTemplate");
}
