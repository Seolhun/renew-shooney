package hi.cord.com.aws;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import hi.cord.com.BlogApplication;
import hi.cord.com.domain.news.NewsData;
import hi.cord.com.domain.news.NewsDataRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BlogApplication.class)
@WebAppConfiguration
@TestPropertySource(properties = {
        "amazon.dynamodb.endpoint=http://localhost:8000/",
        "amazon.aws.accesskey=AKIAJEK5RTYCEBDEBOLA",
        "amazon.aws.secretkey=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"})
public class AwsDynamoDBTest {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    NewsDataRepository repository;

    private static final String EXPECTED_COST = "RamdonKey";
    private static final long EXPECTED_PRICE = 50;

    @Before
    public void setup() throws Exception {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(NewsData.class);
        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);
        dynamoDBMapper.batchDelete(repository.findAll());
    }

    @Test
    public void sampleTestCase() {
        NewsData dave = new NewsData(EXPECTED_COST, EXPECTED_PRICE);
        repository.save(dave);

        List<NewsData> result
                = (List<NewsData>) repository.findAll();

        Assert.assertTrue("Not empty", result.size() > 0);
        Assert.assertTrue("Contains item with expected cost",
                result.get(0).getPk().equals(EXPECTED_COST));
    }
}