package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleSumDTO;
import com.devsuperior.dsmeta.dto.SellerReportDTO;
import com.devsuperior.dsmeta.projections.SaleSumProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	private final DateTimeFormatter dtm = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
	private LocalDate result = today.minusYears(1L);
	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SellerReportDTO> searchByName(String name, String minDate, String maxDate, Pageable pageable){
		if(minDate.trim().isEmpty()){
			minDate = String.valueOf(result);
		}

		if(maxDate.trim().isEmpty()){
			maxDate = String.valueOf(today);
		}
		LocalDate dateMin = LocalDate.parse(minDate, dtm);
		LocalDate dateMax = LocalDate.parse(maxDate, dtm);
		Page<Sale> result = repository.searchBySellerName(name, dateMin, dateMax, pageable);
		return result.map(x -> new SellerReportDTO(x));
	}

	public Page<SaleSumDTO> searchBySaleSelerSummary(String minDate, String maxDate, Pageable pageable){
		if(minDate.trim().isEmpty()){
			minDate = String.valueOf(result);
		}

		if(maxDate.trim().isEmpty()){
			maxDate = String.valueOf(today);
		}
		LocalDate dateMin = LocalDate.parse(minDate, dtm);
		LocalDate dateMax = LocalDate.parse(maxDate, dtm);

		Page<SaleSumProjection> list = repository.searchBySaleSelerSummary(dateMin, dateMax, pageable);
		Page<SaleSumDTO> result = list.map(x -> new SaleSumDTO(x));
		return result;
	}

}
