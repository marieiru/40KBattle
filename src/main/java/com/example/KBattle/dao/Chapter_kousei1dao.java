
package com.example.KBattle.dao;

import java.io.Serializable;
import java.util.List;

import com.example.KBattle.entity.Chapter_kousei1;

public interface Chapter_kousei1dao extends Serializable {
	public List<Chapter_kousei1> search(Integer did);
}