package com.cardx.Cardx.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FeaturesResponse {
    private int feature_id;
    private String icon;
    private String title;
    private String description;
}
