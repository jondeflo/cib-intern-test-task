package com.jondeflo.cibinterntesttask.repository;

import com.jondeflo.cibinterntesttask.model.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Integer> {

	List<Socks> findByColorAndCottonPartEquals(String color, int cottonPart);
	List<Socks> findByColorAndCottonPartGreaterThan(String color, int cottonPart);
	List<Socks> findByColorAndCottonPartLessThan(String color, int cottonPart);

}
