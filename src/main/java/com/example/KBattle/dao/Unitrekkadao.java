package com.example.KBattle.dao;

import java.io.Serializable;
import java.util.List;

import com.example.KBattle.entity.Unitrekka;

public interface Unitrekkadao  extends Serializable {
	public List<Unitrekka> search(Integer sid, Integer yid);
	public List<Unitrekka> search_zid(Integer zid,Integer kuunz);
}