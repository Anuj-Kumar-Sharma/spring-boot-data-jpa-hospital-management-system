package com.codingshuttle.youtube.hospitalManagement.entity;

import lombok.Builder;

@Builder
public record LoginResponse(String userid, String jwt) {
}
