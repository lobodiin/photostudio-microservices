package com.photostudio.servicecertificate.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class Certificate {
    String user_login, studioName;
    Integer price;
}