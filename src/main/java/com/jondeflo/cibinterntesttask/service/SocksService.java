package com.jondeflo.cibinterntesttask.service;

import com.jondeflo.cibinterntesttask.model.Socks;
import com.jondeflo.cibinterntesttask.repository.SocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SocksService {

	@Autowired
	private SocksRepository socksRepository;

	public ResponseEntity getSocksByParams(String color, String operation, int cottonPart) {

		if (color.length() == 0 || !color.matches("[a-zA-Z]+") || cottonPart < 0 || cottonPart > 100 || !operation.matches("(moreThan|lessThan|equal)"))
			return new ResponseEntity<>("Wrong request parameters, please recheck", new HttpHeaders(), 400);

		switch (operation) {
			case "moreThan":
				return new ResponseEntity<>(
						socksRepository.findByColorAndCottonPartGreaterThan(color, cottonPart), new HttpHeaders(), 200);
			case "lessThan":
				return new ResponseEntity<>(
						socksRepository.findByColorAndCottonPartLessThan(color, cottonPart), new HttpHeaders(), 200);
			case "equal":
				return new ResponseEntity<>(
						socksRepository.findByColorAndCottonPartEquals(color, cottonPart), new HttpHeaders(), 200);
			default:
				return new ResponseEntity<>(null, new HttpHeaders(), 400);
		}
	}

	public ResponseEntity registerIncome(String color, int cottonPart, int quantity) {

		if (!validateParams(color, cottonPart, quantity))
			return new ResponseEntity<>("Wrong request body, please recheck", new HttpHeaders(), 400);

		List<Socks> list = socksRepository.findByColorAndCottonPartEquals(color, cottonPart);

		if (list.size() == 0) {
			socksRepository.save(new Socks(color, cottonPart, quantity));
		} else {
			Socks socks = list.get(0);
			if ((long)socks.getQuantity() + (long)quantity > Integer.MAX_VALUE) {
				return new ResponseEntity<>("Maximum stock capacity exceeded", new HttpHeaders(), 400);
			} else {
				socks.setQuantity(socks.getQuantity() + quantity);
				socksRepository.save(socks);
			}
		}

		return new ResponseEntity<>(null, new HttpHeaders(), 200);
	}

	public ResponseEntity registerOutcome(String color, int cottonPart, int quantity) {

		if (!validateParams(color, cottonPart, quantity))
			return new ResponseEntity<>("Wrong request body, please recheck", new HttpHeaders(), 400);

		List<Socks> list = socksRepository.findByColorAndCottonPartEquals(color, cottonPart);

		System.out.println("here");
		if (list.size() == 0) {
			System.out.println("here 1");
			return new ResponseEntity<>("No socks of requested color was found on the stock", new HttpHeaders(), 400);
		} else {
			Socks socks = list.get(0);
			if (socks.getQuantity() - quantity < 0) {
				return new ResponseEntity<>("No such amount of socks on the stock", new HttpHeaders(), 400);
			} else {
				socks.setQuantity(socks.getQuantity() - quantity);
				socksRepository.save(socks);
			}
		}

		return new ResponseEntity<>(null, new HttpHeaders(), 200);
	}

	private boolean validateParams(String color, int cottonPart, int quantity) {
		if (color.length() == 0 || !color.matches("[a-zA-Z]+") || cottonPart < 0 || cottonPart > 100 || quantity < 1)
			return false;
		return true;
	}
}
