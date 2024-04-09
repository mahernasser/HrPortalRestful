package com.llun.error;

public record ErrorDetails(String timestamp, int status, String error, String message, String path) {
}