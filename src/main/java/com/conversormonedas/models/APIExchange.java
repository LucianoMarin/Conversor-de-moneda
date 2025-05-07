package com.conversormonedas.models;

import java.util.Map;

public record APIExchange(String base_code, Map<String, Double> conversion_rates) {
}
