package com.example.KBattle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.KBattle.entity.Unitjyouhou;

public interface SlgmRepositoryUnitjyouhou  extends JpaRepository<Unitjyouhou, Integer> {

//	@Query("SELECT zid FROM unitjyouhou ORDER BY zid")
//	Integer getRandomId();	
	
//	public List<Unitjyouhou>  findAll();		
}
