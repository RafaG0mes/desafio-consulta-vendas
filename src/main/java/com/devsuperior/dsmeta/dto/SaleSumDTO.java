package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleSumProjection;

public class SaleSumDTO {

    private String sellerName;
    private Double total;

    public SaleSumDTO() {
    }

    public SaleSumDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public SaleSumDTO(Sale sale) {
        sellerName = sale.getSeller().getName();
        total = sale.getAmount();
    }

    public SaleSumDTO(SaleSumProjection projection) {
        sellerName = projection.getName();
        total = projection.getTotal();
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
}
