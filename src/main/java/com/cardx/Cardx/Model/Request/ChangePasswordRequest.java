package com.cardx.Cardx.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChangePasswordRequest {

    private String email;
    private String currentPassword;
    private String newPassword;
}
