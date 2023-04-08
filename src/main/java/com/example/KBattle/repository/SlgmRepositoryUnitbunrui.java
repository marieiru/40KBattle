package com.example.KBattle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.KBattle.entity.Unitbunruim;

public interface SlgmRepositoryUnitbunrui extends JpaRepository<Unitbunruim, Integer> {
	
//	@Query("SELECT id FROM unitbunruim ORDER BY id")
//	Integer getRandomId();	
	
//	public List<Unitbunruim>  findAll();
}
