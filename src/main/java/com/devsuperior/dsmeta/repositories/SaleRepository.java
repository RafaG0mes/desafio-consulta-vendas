package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

//    @Query("Select obj FROM Seller obj WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%',:name, '%'))")
//    @Query(nativeQuery = true, value = "SELECT  * " +
//                                        "FROM   TB_SELLER " +
//                                        "INNER JOIN TB_SALES ON TB_SALES.SELLER_ID = TB_SELLER.ID  " +
//                                        "WHERE TB_SALES.DATE BETWEEN :minDate AND :maxDate " +
//                                        "AND UPPER(TB_SELLER.NAME) LIKE UPPER(CONCAT('%', :name, '%')) ")

    @Query(value = "SELECT obj " +
            "FROM Sale obj JOIN FETCH obj.seller " +
            "WHERE obj.date BETWEEN :minDate AND :maxDate " +
            "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))",
            countQuery = "SELECT COUNT(obj) FROM Sale obj JOIN obj.seller " +
                    "WHERE obj.date BETWEEN :minDate AND :maxDate " +
                    "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Sale> searchBySellerName(String name, LocalDate minDate, LocalDate maxDate, Pageable pageable);


    @Query(nativeQuery = true, value = "SELECT  TB_SELLER.NAME, SUM(TB_SALES.AMOUNT) AS TOTAL" +
            "FROM TB_SELLER " +
            "INNER JOIN TB_SALES ON TB_SALES.SELLER_ID = TB_SELLER.ID " +
            "WHERE TB_SALES.DATE BETWEEN :minDate AND :maxDate " +
            "GROUP BY TB_SELLER.NAME ")
    Page<SaleMinDTO> searchBySaleSelerSummary(LocalDate minDate, LocalDate maxDate, Pageable pageable);

}
