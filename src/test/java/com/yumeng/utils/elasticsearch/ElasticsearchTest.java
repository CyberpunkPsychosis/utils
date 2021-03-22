package com.yumeng.utils.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description 测试
 * @Author ym <liu_hao_cheng@163.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2021/3/22 11:28
 */
@SpringBootTest
public class ElasticsearchTest {
    @Test
    void contextLoads() throws Exception{
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));

//        Map<String, Object> jsonMap = new HashMap<>();
//        jsonMap.put("first_name", "Douglas");
//        jsonMap.put("last_name", "Fir");
//        jsonMap.put("age", 35);
//        jsonMap.put("about", "I like to build cabinets");
//        jsonMap.put("interests", Arrays.asList("forestry"));
//        jsonMap.put("type", "employee");
//
//        IndexRequest indexRequest = new IndexRequest("megacorp")
//                .id("3").source(jsonMap)
//                .opType(DocWriteRequest.OpType.CREATE);

        SearchRequest searchRequest = new SearchRequest("megacorp");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.toString());

//        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
//        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
//            System.out.println("创建成功");
//        } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
//            System.out.println("更新成功");
//        }
//        ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
//        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
//            System.out.println(shardInfo.getTotal());
//        }
//        if (shardInfo.getFailed() > 0) {
//            for (ReplicationResponse.ShardInfo.Failure failure :
//                    shardInfo.getFailures()) {
//                String reason = failure.reason();
//                System.out.println(reason);
//            }
//        }
        client.close();
    }
}
