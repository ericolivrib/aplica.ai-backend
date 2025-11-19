package br.com.coderico.aplicai.dto;

import java.util.List;

public record ApiListResponse<T>(String message, List<T> data) {
}
