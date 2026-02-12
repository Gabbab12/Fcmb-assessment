package com.fcmb.gabriel.coresecuritystarter.dtos.requests;

import com.fcmb.gabriel.coresecuritystarter.config.security.ToStringUtil;

public record LoginRequest (
        String username,
        String password
) {
    public String toString() {
        return ToStringUtil.print(this);
    }
}
