package com.example.KBattle.dao;

import java.io.Serializable;
import java.util.List;

import com.example.KBattle.entity.Unitkousei;

public interface Unitkouseidao  extends Serializable {
	public List<Unitkousei> search(Integer zid);
}
