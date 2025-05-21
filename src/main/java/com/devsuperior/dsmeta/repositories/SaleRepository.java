package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.entities.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("Select obj FROM Seller obj WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%',:name, '%'))")
    Page<Seller> searchByName(String name, Pageable pageable);

}
