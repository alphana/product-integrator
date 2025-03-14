package name.alp.productintegrator.config;


import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.RequestListener;
import com.github.tomakehurst.wiremock.http.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Configuration
public class WireMockConfig {

    @Bean
    public WireMockServer wireMockServer() {
        WireMockServer wireMockServer = new WireMockServer(8101);
        wireMockServer.start();

        wireMockServer.stubFor(validationResponseChannel());
        wireMockServer.stubFor(detailsResponseChannel());
        wireMockServer.stubFor(eventResponseChannel());

//        wireMockServer.addMockServiceRequestListener(mockRequestListener());

        return wireMockServer;
    }

    private MappingBuilder eventResponseChannel() {
        return post(urlEqualTo("/api/event"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200));
    }

    private MappingBuilder detailsResponseChannel() {
        return get(urlMatching("/api/details/.*"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"legacyFieldA\": \"valueA\", \"legacyFieldB\": \"valueB\", \"extraDetails\": \"some details\"}")
                        .withStatus(200));
    }

    private MappingBuilder validationResponseChannel() {
        return post(urlEqualTo("/api/validate"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"result\": \"valid\"}")
                        .withStatus(200));
    }

}