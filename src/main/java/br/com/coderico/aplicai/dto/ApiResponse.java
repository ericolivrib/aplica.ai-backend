package br.com.coderico.aplicai.dto;

public record ApiResponse<T>(String message, T data) {
}
