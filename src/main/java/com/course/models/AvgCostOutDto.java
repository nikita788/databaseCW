package com.course.models;

import java.math.BigDecimal;

public class AvgCostOutDto {

    private BigDecimal costForeign;
    private BigDecimal costOur;

    public BigDecimal getCostForeign() {
        return costForeign;
    }

    public void setCostForeign(BigDecimal costForeign) {
        this.costForeign = costForeign;
    }

    public BigDecimal getCostOur() {
        return costOur;
    }

    public void setCostOur(BigDecimal costOur) {
        this.costOur = costOur;
    }
}
