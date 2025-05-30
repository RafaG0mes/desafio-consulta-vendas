package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleSumDTO;
import com.devsuperior.dsmeta.dto.SellerReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SellerReportDTO>> getReport(@RequestParam(name = "name", defaultValue = "") String name,
														   @RequestParam(name = "minDate", defaultValue = "") String minDate,
														   @RequestParam(name = "maxDate", defaultValue = "") String maxDate,
														   Pageable pageable) {
		Page<SellerReportDTO> list = service.searchByName(name, minDate, maxDate, pageable);
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SaleSumDTO>> getSummary(@RequestParam(name = "minDate", defaultValue = "") String minDate,
													   @RequestParam(name = "maxDate", defaultValue = "") String maxDate,
													   Pageable pageable) {
		Page<SaleSumDTO> list = service.searchBySaleSelerSummary(minDate, maxDate, pageable);
		return ResponseEntity.ok(list);
	}
}
