package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Seller;

public class SellerNameDTO {

    private Long id;
    private String name;

    public SellerNameDTO(){}

    public SellerNameDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SellerNameDTO(Seller seller){
        id = seller.getId();
        name = seller.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
