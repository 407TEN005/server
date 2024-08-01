package sixgarlic.potenday.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClovaConfig {

    @Value("${clova.studio-api-key}")
    private String clovaStudioApiKey;

    @Value("${clova.apigw-api-key}")
    private String apiGatewayApiKey;

    @Value("${clova.request-id}")
    private String requestId;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @Bean
    public HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-NCP-CLOVASTUDIO-API-KEY", clovaStudioApiKey);
        headers.set("X-NCP-APIGW-API-KEY", apiGatewayApiKey);
        headers.set("X-NCP-CLOVASTUDIO-REQUEST-ID", requestId);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
