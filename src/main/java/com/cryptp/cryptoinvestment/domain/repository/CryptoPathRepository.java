package com.cryptp.cryptoinvestment.domain.repository;

import com.cryptp.cryptoinvestment.domain.model.CryptoPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoPathRepository extends JpaRepository<CryptoPath, Integer> {

}
