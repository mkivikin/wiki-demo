package interview.wikicredit.service;

import interview.wikicredit.dto.WikiSummaryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.util.Objects;

@Service
@Slf4j
public class WikipediaRequestService {

    private static final String BASE_URL = "https://en.wikipedia.org/api/rest_v1/page";
    private final WebClient requestClient = WebClient.builder().baseUrl(BASE_URL)
        .clientConnector(new ReactorClientHttpConnector(
            HttpClient.create().followRedirect(true)
        ))
        .filters(exchangeFilterFunctions -> {
            exchangeFilterFunctions.add(logRequest());
            exchangeFilterFunctions.add(logResponse());
        }).build();

    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            return Mono.just(clientRequest);
        });
    }

    private static ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(response -> {
            logStatus(response);
            return logBody(response);
        });
    }

    private static void logStatus(ClientResponse response) {
        HttpStatus status = response.statusCode();
        log.info("Returned status code {} ({})", status.value(), status.getReasonPhrase());
    }

    private static Mono<ClientResponse> logBody(ClientResponse response) {
        if ((response.statusCode().is4xxClientError() || response.statusCode()
            .is5xxServerError())) {
            return response.bodyToMono(String.class)
                .flatMap(body -> {
                    log.info("Body: {}", body);
                    return Mono.just(response);
                });
        } else {
            return Mono.just(response);
        }
    }

    public WikiSummaryResponse fetchSummaryFromWiki(String companyName) {
        return Objects.requireNonNull(requestClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/summary/{companyName}")
                    .build(companyName))
                .retrieve().toEntity(WikiSummaryResponse.class).block())
            .getBody();
    }

}
