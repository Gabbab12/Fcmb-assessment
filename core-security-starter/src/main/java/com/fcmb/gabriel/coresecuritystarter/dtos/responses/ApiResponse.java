package com.fcmb.gabriel.coresecuritystarter.dtos.responses;


import com.fcmb.gabriel.coresecuritystarter.config.security.ToStringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    @Override
    public String toString() {
        return ToStringUtil.print(this);
    }
}
