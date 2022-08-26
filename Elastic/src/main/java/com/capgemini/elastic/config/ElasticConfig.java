package com.capgemini.elastic.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.capgemini.elastic.repository")
public class ElasticConfig{


	@Value("${elastic.host.port}")
	private String hostAndPort;
	
    @SuppressWarnings("deprecation")
	@Bean
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration 
            = ClientConfiguration.builder()
                .connectedTo(hostAndPort)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    public ElasticsearchRestTemplate elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(elasticsearchClient());
    }

  
//    @Bean
//    public ElasticsearchClient client() throws Exception {
//        Settings settings = Settings.builder().put("cluster.name", esClusterName).build();
//        TransportClient client = new PreBuiltTransportClient(settings);
//        client.addTransportAddress(new TransportAddress(InetAddress.getByName(esHost), esPort));
//        return client;
//    }
	
//	@Bean
//	public ElasticsearchClient client() {
//		
//	RestClient httpClient = RestClient.builder(
//	    new HttpHost("localhost", 9200)
//	).build();
//	
//	
//
//	ElasticsearchTransport transport = new RestClientTransport(
//	    httpClient,
//	    new JacksonJsonpMapper()
//	);
//
//	return new ElasticsearchClient(transport);
//	}
		
//	  @Override
//	    @Bean
//	    public ReactiveElasticsearchClient reactiveElasticsearchClient() {
//	        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//	                .connectedTo("localhost:9200")
//	                .build();
//	        return ReactiveRestClients.create(clientConfiguration);
//	    }
}
