package com.example.KBattle.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.KBattle.dao.Battlekousei1dao;
import com.example.KBattle.entity.Battlekousei1;

@SuppressWarnings("serial")
@Repository
public class FindBattlekousei1 implements Battlekousei1dao {

	@Autowired
	private EntityManager entityManager;

	public FindBattlekousei1() {
		super();
	}

	public FindBattlekousei1(EntityManager manager) {
		this();
		entityManager = manager;
	}

	//Daoクラスで用意したsearchメソッドをオーバーライドする
	@SuppressWarnings("unchecked")
	@Override
	public List<Battlekousei1> search(Integer id) {

		//StringBuilderでSQL文を連結する
		StringBuilder sql = new StringBuilder();
		//sql.append("Select a.kid from unitkousei a inner join sendanm b on a.sid = b.id WHERE ");

		sql.append("Select a from Battlekousei1 a WHERE ");

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
	public List<Battlekousei1> search_LIVE(Integer FG) {

		//StringBuilderでSQL文を連結する
		StringBuilder sql = new StringBuilder();
		//sql.append("Select a.kid from unitkousei a inner join sendanm b on a.sid = b.id WHERE ");

		sql.append("Select a from Battlekousei1 a WHERE ");

		//genreがブランクではなかった場合、sql変数にappendする
		//フラグをtrueにしとく
		if ((FG) == 0) {
	//		sql.append(" a.death is null");
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
