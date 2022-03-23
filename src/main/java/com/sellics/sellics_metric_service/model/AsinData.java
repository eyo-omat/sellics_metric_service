package com.sellics.sellics_metric_service.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sellics.sellics_metric_service.utils.SellicsDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AsinData {

    @JsonDeserialize(using = SellicsDateSerializer.class)
    private DateTime timestamp;
    private String asin;
    private String keyword;
    private int rank;
}
