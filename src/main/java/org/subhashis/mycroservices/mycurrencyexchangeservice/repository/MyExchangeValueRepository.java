package org.subhashis.mycroservices.mycurrencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.subhashis.mycroservices.mycurrencyexchangeservice.model.ExchangeValue;

import java.util.Optional;

@Repository
public interface MyExchangeValueRepository extends JpaRepository<ExchangeValue,Long> {
	Optional<ExchangeValue> findByFromAndTo(String from, String to);
}
