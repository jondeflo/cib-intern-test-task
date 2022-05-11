package com.jondeflo.cibinterntesttask.controller;

import com.jondeflo.cibinterntesttask.model.Socks;
import com.jondeflo.cibinterntesttask.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping(value = "/api/socks")
public class SocksController {

	@Autowired
	private SocksService socksService;

	@GetMapping(params = {"color", "operation", "cottonPart"})
	public ResponseEntity getSocksByParams(@RequestParam("color") String color,
										   @RequestParam("operation") String operation,
										   @RequestParam("cottonPart") int cottonPart) {

		return socksService.getSocksByParams(color, operation, cottonPart);
	}


	@PostMapping(value = "/income")
	public ResponseEntity postIncome(@RequestBody Socks socks) {

		return socksService.registerIncome(socks.getColor(), socks.getCottonPart(), socks.getQuantity());
	}

	@PostMapping(value = "/outcome")
	public ResponseEntity postOutcome(@RequestBody Socks socks) {

		return socksService.registerOutcome(socks.getColor(), socks.getCottonPart(), socks.getQuantity());
	}
}
