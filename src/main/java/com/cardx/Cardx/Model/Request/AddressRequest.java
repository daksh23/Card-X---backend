package com.cardx.Cardx.Model.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    @JsonIgnore
    private Long addressId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("unit_no")
    private Long unitNo;

    @JsonProperty("city")
    private String city;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("province")
    private String province;

    @JsonProperty("country")
    private String country;

}
