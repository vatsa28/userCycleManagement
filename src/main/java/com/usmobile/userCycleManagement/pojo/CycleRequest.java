package com.usmobile.userCycleManagement.pojo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Request POJO for all the cycle Requests
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CycleRequest {

    @NotBlank(message = "userId is mandatory")
    private String userId;

    @NotBlank(message = "mdn is mandatory")
    private String mdn;
}
