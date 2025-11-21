package com.county.entity;

import java.util.Map;

public record ApiError(
        String timestamp,
        int status,
        String error,
        String message,
        Map<String, Object> details
) {}
