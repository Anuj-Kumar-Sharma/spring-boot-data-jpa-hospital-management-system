package com.codingshuttle.youtube.hospitalManagement.entity;

import lombok.Builder;

@Builder
public record SignUpResponse(Long id, String username) {
}
