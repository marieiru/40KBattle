package com.example.KBattle.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.KBattle.dao.Battlekousei2dao;
import com.example.KBattle.entity.Battlekousei2;

@SuppressWarnings("serial")
@Repository
public class FindBattlekousei2 implements Battlekousei2dao {

	@Autowired
	private EntityManager entityManager;

	public FindBattlekousei2() {
		super();
	}

	public FindBattlekousei2(EntityManager manager) {
		this();
		entityManager = manager;
	}

	//Daoクラスで用意したsearchメソッドをオーバーライドする
	@SuppressWarnings("unchecked")
	@Override
	public List<Battlekousei2> search(Integer id) {

		//StringBuilderでSQL文を連結する
		StringBuilder sql = new StringBuilder();
		//sql.append("Select a.kid from unitkousei a inner join sendanm b on a.sid = b.id WHERE ");

		sql.append("Select a from Battlekousei2 a WHERE ");

		//genreがブランクではなかった場合、sql変数にappendする
		//フラグをtrueにしとく
		if ((id) == 0) {
		} else {
			sql.append("a.id =" + id);

		}

		sql.append(" order by id ");

		Query query = entityManager.createQuery(sql.toString());

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Battlekousei2> search_LIVE(Integer FG) {

		//StringBuilderでSQL文を連結する
		StringBuilder sql = new StringBuilder();
		//sql.append("Select a.kid from unitkousei a inner join sendanm b on a.sid = b.id WHERE ");

		sql.append("Select a from Battlekousei2 a WHERE ");

		//genreがブランクではなかった場合、sql変数にappendする
		//フラグをtrueにしとく
		if ((FG) == 0) {
		//	sql.append(" a.death is null");
		//	sql.append(" Not (a.death =1)");    
		//	sql.append(" AND ");
			sql.append(" a.mvg =0");
		} else {
			sql.append(" a.death =1");
			sql.append(" AND ");
			sql.append(" a.mvg =0");
		}
		
		Query query = entityManager.createQuery(sql.toString());

		return query.getResultList();
	}

}
