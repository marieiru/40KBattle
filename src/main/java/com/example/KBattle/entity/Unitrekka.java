package com.example.KBattle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="unitrekka")
public class Unitrekka {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//追加
	@Column
	private Integer id;
	@Column
	private Integer zid;
	@Column
	private Integer kuunz;	
	@Column
	private Integer mv;	
	@Column
	private Integer ass;
	@Column
	private Integer scs;
	@Column
	private Integer sid;	
	@Column
	private Integer yid;		
	
    @ManyToOne
    @JoinColumn(name="zid",insertable=false, updatable=false )
    private Unitjyouhou unitjyouhou;	

}
