package com.miasol.common.dto;

import javax.validation.constraints.NotBlank; // springboot2.3+ 不提供
import java.io.Serializable;
import lombok.Data;

@Data
public class UserDto {
    @NotBlank(message = "账号不能位空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;


}
