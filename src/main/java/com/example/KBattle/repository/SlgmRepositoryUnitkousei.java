package com.example.KBattle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.KBattle.entity.Unitkousei;

public interface SlgmRepositoryUnitkousei extends JpaRepository<Unitkousei, Integer> {
	//@Query("Select a.kid from unitkousei a inner join sendanm b on a.sid = b.id"  )

	//public List<Unitkousei> findAll();


	
	
}
