package com.example.KBattle.dao;

import java.io.Serializable;
import java.util.List;

import com.example.KBattle.entity.Bukim;

public interface Bukimdao extends Serializable {
	public List<Bukim> search(Integer sid,Integer h);
	public List<Bukim> search_wno(Integer wno);
}
