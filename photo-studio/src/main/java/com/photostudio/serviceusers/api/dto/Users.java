package com.photostudio.serviceusers.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class Users {
    String firstName, lastName, phone, login, password;
}
