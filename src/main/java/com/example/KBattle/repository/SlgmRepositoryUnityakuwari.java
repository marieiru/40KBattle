package com.example.KBattle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.KBattle.entity.Unityakuwarim;

public interface SlgmRepositoryUnityakuwari extends JpaRepository<Unityakuwarim, Integer> {
	
//	@Query("SELECT id FROM unityakuwarim ORDER BY id")
//	Integer getRandomId();	
	
//	public List<Unityakuwarim>  findAll();
}
