package com.ifortex.bookservice.dto;

import java.time.LocalDateTime;

/*
 * DTO for Member entity. 
 */
public record MemberDto(
        long id,
        String name,
        LocalDateTime membershipDate) {
}
