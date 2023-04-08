package com.example.KBattle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.KBattle.entity.rekka;

public interface SlgmRepositoryUnitrekka_h  extends JpaRepository<rekka, Integer> {
	
//	@Query("SELECT id FROM rekka ORDER BY id,kuunz DESC")
//	Integer getRandomId();	
	
//	public List<rekka>  findAll();
}
