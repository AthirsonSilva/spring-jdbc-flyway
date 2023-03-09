package com.amigoscode.actor;

import lombok.Builder;

@Builder
public record Actor(Integer id, String name) {

}