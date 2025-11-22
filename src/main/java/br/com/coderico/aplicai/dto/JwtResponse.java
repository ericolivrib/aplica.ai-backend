package br.com.coderico.aplicai.dto;

import java.time.Instant;

public record JwtResponse(String token, Instant expiration) {
}
