package com.nist.integration.client;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.nist.integration.dto.NistResultDto;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NistClient {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
  private static final String START_INDEX = "startIndex";
  private static final String RESULTS_PER_PAGE = "resultsPerPage";
  private static final String START_DATE = "lastModStartDate";
  private static final String END_DATE = "lastModEndDate";

  private static final String NIST_URL = "https://services.nvd.nist.gov/rest/json/cpes/2.0";

  private final RestTemplate restTemplate;

  @Value("${config.request-per-page}")
  private String resultPerPage;

  public int getResultPerPage() {
    return Integer.parseInt(resultPerPage);
  }

  public NistResultDto fetch(int startIndex, @Nullable LocalDateTime from, @Nullable LocalDateTime till) {
    String startDate = Optional.ofNullable(from).map(FORMATTER::format).orElse("");
    String endDate = Optional.ofNullable(till).map(FORMATTER::format).orElse("");
    Map<String, ? extends Serializable> params = Map.of(START_INDEX, startIndex,
        RESULTS_PER_PAGE, resultPerPage,
        START_DATE, startDate,
        END_DATE, endDate);
    return restTemplate.getForEntity(NIST_URL, NistResultDto.class, params).getBody();
  }
}
