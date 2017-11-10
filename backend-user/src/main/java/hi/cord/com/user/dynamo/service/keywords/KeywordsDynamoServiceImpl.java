package hi.cord.com.user.dynamo.service.keywords;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import hi.cord.com.user.dynamo.domain.keywords.Keywords;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class KeywordsDynamoServiceImpl implements KeywordsDynamoService {

    @Value("${aws.dynamo.accesskey}")
    private String accesskey;
    @Value("${aws.dynamo.secretkey}")
    private String secretkey;

    private DynamoDBMapper mapper;

    @PostConstruct
    public void init() {
        if (mapper == null) {
            BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accesskey, secretkey);
            AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                    .withRegion(Regions.AP_NORTHEAST_2)
                    .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                    .build();

            this.mapper = new DynamoDBMapper(client);
        }
    }

    @Override
    public void saveKeywords(Keywords keywords) {
        if (keywords != null) {
            this.mapper.save(keywords);
        }
    }

    @Override
    public Keywords findById(String id) {
        Keywords keywords = null;
        if (id != null) {
            keywords = new Keywords();
        }

        return keywords;
    }
//    public void saveOffSet(String shardId, String lastSequenceNumber) {
//        WS_Fraud_Offset offSet = new WS_Fraud_Offset();
//        offSet.setShardId(shardId);
//        offSet.setCheckPoint(lastSequenceNumber);
//        this.mapper.save(offSet);
//    }
//
//    public String getOffSet(String shardId) {
//        String result = "1";
//        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
//        eav.put(":val1", new AttributeValue().withS(shardId));
//        DynamoDBQueryExpression<WS_Fraud_Offset> queryExpression = new DynamoDBQueryExpression<WS_Fraud_Offset>().withKeyConditionExpression("shardId = :val1").withExpressionAttributeValues(eav);
//
//        List<WS_Fraud_Offset> offSet = this.mapper.query(WS_Fraud_Offset.class, queryExpression);
//        if (offSet != null && offSet.size() > 0) {
//            result = ((WS_Fraud_Offset) offSet.get(0)).getCheckPoint();
//        }
//        return result;
//    }
}
