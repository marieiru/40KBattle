package com.example.KBattle.controller;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.KBattle.entity.Battlekousei1;
import com.example.KBattle.entity.Battlekousei10;
import com.example.KBattle.entity.Battlekousei2;
import com.example.KBattle.entity.Battlekousei20;
import com.example.KBattle.entity.Battlemode;
import com.example.KBattle.entity.Battlereport;
import com.example.KBattle.entity.Bukim;
import com.example.KBattle.entity.Chapter_kousei1;
import com.example.KBattle.entity.Chapter_kousei2;
import com.example.KBattle.entity.Unitrekka;
import com.example.KBattle.form.FormBattlemode;
import com.example.KBattle.form.FormBattlereport;
import com.example.KBattle.repository.FindBattlekousei1;
import com.example.KBattle.repository.FindBattlekousei2;
import com.example.KBattle.repository.FindUnitrekkaRepository;
import com.example.KBattle.repository.FindbukimRepository;
import com.example.KBattle.repository.SlgmRepositoryBattlekousei1;
import com.example.KBattle.repository.SlgmRepositoryBattlekousei10;
import com.example.KBattle.repository.SlgmRepositoryBattlekousei2;
import com.example.KBattle.repository.SlgmRepositoryBattlekousei20;
import com.example.KBattle.repository.SlgmRepositoryBattlemode;
import com.example.KBattle.repository.SlgmRepositoryBattlereport;
import com.example.KBattle.repository.SlgmRepositoryChapter_kousei1;
import com.example.KBattle.repository.SlgmRepositoryChapter_kousei2;
import com.example.KBattle.repository.SlgmRepositoryChapter_unit1;
import com.example.KBattle.repository.SlgmRepositoryChapter_unit2;

@Controller
@RequestMapping("/battle")
public class BattleController {

	@Autowired
	SlgmRepositoryChapter_unit1 repoChapter_unit1;

	@Autowired
	SlgmRepositoryChapter_unit2 repoChapter_unit2;

	@Autowired
	SlgmRepositoryBattlekousei1 repoBattlekousei1;

	@Autowired
	SlgmRepositoryBattlekousei2 repoBattlekousei2;

	@Autowired
	SlgmRepositoryBattlekousei10 repoBattlekousei10;

	@Autowired
	SlgmRepositoryBattlekousei20 repoBattlekousei20;

	@Autowired
	SlgmRepositoryChapter_kousei1 repoChapter_kousei1;

	@Autowired
	SlgmRepositoryChapter_kousei2 repoChapter_kousei2;

	@Autowired
	SlgmRepositoryBattlemode repoBattlemode;

	@Autowired
	SlgmRepositoryBattlereport repoBattlereport;

	@Autowired
	FindUnitrekkaRepository findUnitrekkaRepository;

	@Autowired
	FindBattlekousei1 findBK1Repo;

	@Autowired
	FindBattlekousei2 findBK2Repo;

	/**
	RandonBattlekousei1 randomBK1;
	
	
	RandonBattlekousei2 randomBK2;
	**/

	@Autowired
	FindbukimRepository Findbuki;

	int Gekiha1 = 0;
	int Gekiha2 = 0;
	int DMGM = 0;

	int hit1 = 0;
	int hit2 = 0;

	int DMGC1 = 0;
	int DMGC2 = 0;

	int UUNZ1 = 0;
	int UUNZ2 = 0;

	int akia1 = 0;
	int akia2 = 0;
	int ahit1 = 0;
	int ahit2 = 0;
	int aDMGC1 = 0;
	int aDMGC2 = 0;
	int aUUNZ1 = 0;
	int aUUNZ2 = 0;

	int EnId = 0;
	int EnTaff = 0;
	int EnSV1 = 0;
	int EnSV2 = 0;
	int EnUUNZ = 0;

	int ks = 0; //回数
	int num = 0; //回数の変換前									

	String syubetu = "";
	int syatei = 0;
	int at = 0;
	int ap = 0;

	String Sks = "";
	String dmg = "";
	String abl = "";

	int hit = 0;
	int sss = 0;

	//ダメージ判定のフラグ
	int damageF = 0;
	int SVRF = 0;

	int kyori = 0;
	int IDOUF = 0;
	String BUKIn = "";

	String BTComment = "";

	int chargeF = 0;

	int IDF = 0;

	int Skeizoku = 0;

	@GetMapping("/index")
	public String bukim(FormBattlemode formBattlemode, Model model) {

		model.addAttribute("chapter1List", repoChapter_unit1.findAll(Sort.by(Sort.Direction.ASC, "id")));
		model.addAttribute("chapter2List", repoChapter_unit2.findAll(Sort.by(Sort.Direction.ASC, "id")));

		//演算テーブルをクリア
		//		repoBattlekousei1.deleteAll();
		//		repoBattlekousei2.deleteAll();
		//	repoBattlemode.deleteAll();
		//		repoBattlereport.deleteAll();

		// reportList
		model.addAttribute("reportList", repoBattlereport.findAll(Sort.by(Sort.Direction.ASC, "id")));

		List<Battlemode> result_mode = repoBattlemode.findAll(Sort.by(Sort.Direction.DESC, "id"));
		if (result_mode != null && result_mode.size() >= 1) {

			formBattlemode.setBmode1(result_mode.get(0).getBmode1());
			formBattlemode.setBmode2(result_mode.get(0).getBmode2());
			formBattlemode.setBmvc(result_mode.get(0).getBmvc());
			formBattlemode.setTrik(result_mode.get(0).getTrik());

		} else {
			formBattlemode.setBmvc(20);
			formBattlemode.setTrik(1);
		}

		return "battle";
	}

	@ModelAttribute
	public FormBattlemode setFormBattlemode() {
		return new FormBattlemode();
	}

	@ModelAttribute
	public FormBattlereport setFormBattlereport() {
		return new FormBattlereport();
	}

	@GetMapping("/report")
	public String report(@ModelAttribute("formModel") FormBattlemode formBattlemode, Model model)
			throws InterruptedException {

		model.addAttribute("msg", "検索結果");
		model.addAttribute("chapter1List", repoChapter_unit1.findAll(Sort.by(Sort.Direction.ASC, "id")));
		model.addAttribute("chapter2List", repoChapter_unit2.findAll(Sort.by(Sort.Direction.ASC, "id")));
		repoBattlereport.deleteAll();
		repoBattlekousei1.deleteAll();
		repoBattlekousei2.deleteAll();
		repoBattlemode.deleteAll();

		//設定を保存
		Battlemode battlemode = new Battlemode();
		battlemode.setBmode1(formBattlemode.getBmode1());
		battlemode.setBmode2(formBattlemode.getBmode2());
		battlemode.setBmvc(formBattlemode.getBmvc());
		battlemode.setTrik(formBattlemode.getTrik());
		repoBattlemode.saveAndFlush(battlemode);

		//最初に両方のリストを対戦用DBに複写しておく
		List<Chapter_kousei1> result_Ck1 = repoChapter_kousei1.findAll(Sort.by(Sort.Direction.DESC, "id"));

		if (result_Ck1 != null && result_Ck1.size() >= 1) {
			for (int i = 0; i <= result_Ck1.size() - 1; i++) {

				Battlekousei1 battlekousei1 = new Battlekousei1();

				battlekousei1.setAbl(result_Ck1.get(i).getAbl());
				battlekousei1.setAk(result_Ck1.get(i).getAk());
				battlekousei1.setAsa(result_Ck1.get(i).getAsa());
				battlekousei1.setAssp(result_Ck1.get(i).getAssp());
				battlekousei1.setAt(result_Ck1.get(i).getAt());
				battlekousei1.setBid(result_Ck1.get(i).getBid());
				battlekousei1.setWno(result_Ck1.get(i).getWno());
				battlekousei1.setWno2(result_Ck1.get(i).getWno2());
				battlekousei1.setWno3(result_Ck1.get(i).getWno3());
				battlekousei1.setWno4(result_Ck1.get(i).getWno4());
				battlekousei1.setWno5(result_Ck1.get(i).getWno5());
				battlekousei1.setWno6(result_Ck1.get(i).getWno6());
				battlekousei1.setWno7(result_Ck1.get(i).getWno7());

				battlekousei1.setKid(result_Ck1.get(i).getKid());
				battlekousei1.setKname(result_Ck1.get(i).getKname());

				battlekousei1.setLd(result_Ck1.get(i).getLd());
				battlekousei1.setMvg(formBattlemode.getBmvc() / 2);
				battlekousei1.setTaff(result_Ck1.get(i).getTaff());
				battlekousei1.setUunz(result_Ck1.get(i).getUunz());
				battlekousei1.setZid(result_Ck1.get(i).getZid());

				//	battlekousei1.setCh(0);

				//	battlekousei1.setDeath(0);

				if (i < result_Ck1.size() - 1) {
					repoBattlekousei1.save(battlekousei1);
				} else {
					repoBattlekousei1.save(battlekousei1);
					repoBattlekousei1.flush();
				}

			}

		}

		List<Chapter_kousei2> result_Ck2 = repoChapter_kousei2.findAll(Sort.by(Sort.Direction.DESC, "id"));

		if (result_Ck2 != null && result_Ck2.size() >= 1) {
			for (int i = 0; i <= result_Ck2.size() - 1; i++) {
				Battlekousei2 battlekousei2 = new Battlekousei2();

				battlekousei2.setAbl(result_Ck2.get(i).getAbl());
				battlekousei2.setAk(result_Ck2.get(i).getAk());
				battlekousei2.setAsa(result_Ck2.get(i).getAsa());
				battlekousei2.setAssp(result_Ck2.get(i).getAssp());
				battlekousei2.setAt(result_Ck2.get(i).getAt());
				battlekousei2.setBid(result_Ck2.get(i).getBid());
				battlekousei2.setWno(result_Ck2.get(i).getWno());
				battlekousei2.setWno2(result_Ck2.get(i).getWno2());
				battlekousei2.setWno3(result_Ck2.get(i).getWno3());
				battlekousei2.setWno4(result_Ck2.get(i).getWno4());
				battlekousei2.setWno5(result_Ck2.get(i).getWno5());
				battlekousei2.setWno6(result_Ck2.get(i).getWno6());
				battlekousei2.setWno7(result_Ck2.get(i).getWno7());

				battlekousei2.setKid(result_Ck2.get(i).getKid());
				battlekousei2.setKname(result_Ck2.get(i).getKname());

				battlekousei2.setLd(result_Ck2.get(i).getLd());
				battlekousei2.setMvg(formBattlemode.getBmvc() / 2);
				battlekousei2.setTaff(result_Ck2.get(i).getTaff());
				battlekousei2.setUunz(result_Ck2.get(i).getUunz());
				battlekousei2.setZid(result_Ck2.get(i).getZid());

				//	battlekousei2.setCh(0);
				//	battlekousei2.setDeath(0);

				if (i < result_Ck2.size() - 1) {
					repoBattlekousei2.save(battlekousei2);
				} else {
					repoBattlekousei2.save(battlekousei2);
					repoBattlekousei2.flush();
				}

			}
		}
		////複写おわり

		//対戦ターン経過　1-7
		Random random = new Random();

		for (int tn = 0; tn <= 6; tn++) {

			Gekiha1 = 0;
			Gekiha2 = 0;
			DMGM = 0;

			hit1 = 0;
			hit2 = 0;

			DMGC1 = 0;
			DMGC2 = 0;

			UUNZ1 = 0;
			UUNZ2 = 0;

			akia1 = 0;
			akia2 = 0;
			ahit1 = 0;
			ahit2 = 0;
			aDMGC1 = 0;
			aDMGC2 = 0;
			aUUNZ1 = 0;
			aUUNZ2 = 0;

			EnId = 0;
			EnTaff = 0;
			EnSV1 = 0;
			EnSV2 = 0;
			EnUUNZ = 0;

			ks = 0; //回数
			num = 0; //回数の変換前									

			syubetu = "";
			syatei = 0;
			at = 0;
			ap = 0;

			Sks = "";
			dmg = "";
			abl = "";

			hit = 0;
			sss = 0;

			//ダメージ判定のフラグ
			damageF = 0;
			SVRF = 0;

			kyori = 0;
			IDOUF = 0;
			BUKIn = "";

			BTComment = "";

			chargeF = 0;

			IDF = 0;

			Skeizoku = 0;

			//先攻側 移動処理
			IDOUF = 0;
			if (formBattlemode.getBmode1() == 1) {
			} else {
				IDOUF = 1;
			}
			//移動処理が存在する場合、移動する
			if (IDOUF == 1) {
				List<Battlekousei1> result_Bk11 = repoBattlekousei1.findAll(Sort.by(Sort.Direction.DESC, "id"));
				if (result_Bk11 != null && result_Bk11.size() >= 1) {

					for (int i = 0; i <= result_Bk11.size() - 1; i++) {

						if (result_Bk11.get(i).getMvg() == 0) {
						} else {
							//構成員の現在の能力値をとりだす
							List<Unitrekka> result_zid = findUnitrekkaRepository.search_zid(result_Bk11.get(i).getZid(),
									result_Bk11.get(i).getUunz());

							if (result_zid != null && result_zid.size() >= 1) {

								Battlekousei1 battlekousei1 = new Battlekousei1();

								battlekousei1.setId(result_Bk11.get(i).getId());
								battlekousei1.setAbl(result_Bk11.get(i).getAbl());
								battlekousei1.setAk(result_Bk11.get(i).getAk());
								battlekousei1.setAsa(result_Bk11.get(i).getAsa());
								battlekousei1.setAssp(result_Bk11.get(i).getAssp());
								battlekousei1.setAt(result_Bk11.get(i).getAt());
								battlekousei1.setBid(result_Bk11.get(i).getBid());
								battlekousei1.setWno(result_Bk11.get(i).getWno());
								battlekousei1.setWno2(result_Bk11.get(i).getWno2());
								battlekousei1.setWno3(result_Bk11.get(i).getWno3());
								battlekousei1.setWno4(result_Bk11.get(i).getWno4());
								battlekousei1.setWno5(result_Bk11.get(i).getWno5());
								battlekousei1.setWno6(result_Bk11.get(i).getWno6());
								battlekousei1.setWno7(result_Bk11.get(i).getWno7());

								battlekousei1.setKid(result_Bk11.get(i).getKid());
								battlekousei1.setKname(result_Bk11.get(i).getKname());

								battlekousei1.setLd(result_Bk11.get(i).getLd());

								//	battlekousei1.setCh(result_Bk11.get(i).getCh());
								//		battlekousei1.setDeath(0);

								if ((result_Bk11.get(i).getMvg() - result_zid.get(0).getMv()) < 0) {
									battlekousei1.setMvg(0);
								} else {
									battlekousei1.setMvg(result_Bk11.get(i).getMvg() - result_zid.get(0).getMv());
								}

								battlekousei1.setTaff(result_Bk11.get(i).getTaff());
								battlekousei1.setUunz(result_Bk11.get(i).getUunz());
								battlekousei1.setZid(result_Bk11.get(i).getZid());

								repoBattlekousei1.saveAndFlush(battlekousei1);

							}
						}
					}
				}

			}

			//先攻側
			//先攻側
			//先攻側
			//先攻側を一体ずつ射撃で処理
			List<Battlekousei1> result_Bk1 = repoBattlekousei1.findAll(Sort.by(Sort.Direction.DESC, "id"));

			if (result_Bk1 != null && result_Bk1.size() >= 1) {
				for (int i = 0; i <= result_Bk1.size() - 1; i++) {

					if (result_Bk1.get(i).getMvg() == 0) {
					} else {

						IDF = result_Bk1.get(i).getId();

						kyori = result_Bk1.get(i).getMvg();

						//後攻が全滅している場合を想定して
						List<Battlekousei2> result_Ck22 = repoBattlekousei2.findAll();
						if (result_Ck22 != null && result_Ck22.size() >= 1) {

							Skeizoku = 1;

							//ランダムにひとり取り出して距離をとる
							//	Optional<Battlekousei2> result_Ck22 = selectOneRandomId2();
							//	Optional<Battlekousei2> result_Ck22 =repoBattlekousei2.
							//	Item.order(Arel.sql('RANDOM()')).limit(1)
							Battlekousei2 battlekousei2 = new Battlekousei2();

							battlekousei2.setId(result_Ck22.get(0).getId());
							battlekousei2.setAbl(result_Ck22.get(0).getAbl());
							battlekousei2.setAk(result_Ck22.get(0).getAk());
							battlekousei2.setAsa(result_Ck22.get(0).getAsa());
							battlekousei2.setAssp(result_Ck22.get(0).getAssp());
							battlekousei2.setAt(result_Ck22.get(0).getAt());
							battlekousei2.setBid(result_Ck22.get(0).getBid());
							battlekousei2.setWno(result_Ck22.get(0).getWno());
							battlekousei2.setWno2(result_Ck22.get(0).getWno2());
							battlekousei2.setWno3(result_Ck22.get(0).getWno3());
							battlekousei2.setWno4(result_Ck22.get(0).getWno4());
							battlekousei2.setWno5(result_Ck22.get(0).getWno5());
							battlekousei2.setWno6(result_Ck22.get(0).getWno6());
							battlekousei2.setWno7(result_Ck22.get(0).getWno7());

							battlekousei2.setKid(result_Ck22.get(0).getKid());
							battlekousei2.setKname(result_Ck22.get(0).getKname());

							battlekousei2.setLd(result_Ck22.get(0).getLd());
							battlekousei2.setMvg(result_Ck22.get(0).getMvg());
							battlekousei2.setTaff(result_Ck22.get(0).getTaff());
							battlekousei2.setUunz(result_Ck22.get(0).getUunz());
							battlekousei2.setZid(result_Ck22.get(0).getZid());

							//		battlekousei2.setCh(result_Ck22.get(i).getCh());
							//		battlekousei2.setDeath(0);

							kyori = kyori + result_Ck22.get(0).getMvg();

							EnId = result_Ck22.get(0).getId();
							EnTaff = result_Ck22.get(0).getTaff();
							EnSV1 = result_Ck22.get(0).getAsa();
							EnSV2 = result_Ck22.get(0).getAssp();
							EnUUNZ = result_Ck22.get(0).getUunz();

							//ここで検索してうまくできるか　テスト					

							//構成員の現在の能力値をとりだす
							List<Unitrekka> result_zid = findUnitrekkaRepository.search_zid(result_Bk1.get(i).getZid(),
									result_Bk1.get(i).getUunz());

							if (result_zid != null && result_zid.size() >= 1) {
								chargeF = 0;
								//射程内なら射撃 1-4
								if ((Findbuki.search_wno(result_Bk1.get(i).getWno()).get(0).getSyatei() >= kyori)
										|| (Findbuki.search_wno(result_Bk1.get(i).getWno2()).get(0)
												.getSyatei() >= kyori)
										|| (Findbuki.search_wno(result_Bk1.get(i).getWno3()).get(0)
												.getSyatei() >= kyori)
										|| (Findbuki.search_wno(result_Bk1.get(i).getWno4()).get(0)
												.getSyatei() >= kyori)) {
									chargeF = 1;
								}

								// 
								if ((chargeF == 1) && (formBattlemode.getBmode1() != 3)) {
									//1-4 でまわす なるべく簡潔化したい
									for (int kk = 0; kk <= 3; kk++) {

										if (kk == 0) {
											List<Bukim> Buki1 = Findbuki.search_wno(result_Bk1.get(i).getWno());
											syubetu = Buki1.get(0).getSyubetu();
											syatei = Buki1.get(0).getSyatei();
											at = Buki1.get(0).getAt();
											ap = Buki1.get(0).getAp();

											dmg = Buki1.get(0).getDmg();
											abl = Buki1.get(0).getAbl();
											hit = Buki1.get(0).getHit();
											sss = Buki1.size();
											Sks = Buki1.get(0).getKs();

											BUKIn = Buki1.get(0).getWname();

										} else if (kk == 1) {
											List<Bukim> Buki2 = Findbuki.search_wno(result_Bk1.get(i).getWno2());
											syubetu = Buki2.get(0).getSyubetu();
											syatei = Buki2.get(0).getSyatei();
											at = Buki2.get(0).getAt();
											ap = Buki2.get(0).getAp();

											dmg = Buki2.get(0).getDmg();
											abl = Buki2.get(0).getAbl();
											hit = Buki2.get(0).getHit();
											sss = Buki2.size();
											Sks = Buki2.get(0).getKs();
											BUKIn = Buki2.get(0).getWname();
										} else if (kk == 2) {
											List<Bukim> Buki3 = Findbuki.search_wno(result_Bk1.get(i).getWno3());
											syubetu = Buki3.get(0).getSyubetu();
											syatei = Buki3.get(0).getSyatei();
											at = Buki3.get(0).getAt();
											ap = Buki3.get(0).getAp();

											dmg = Buki3.get(0).getDmg();
											abl = Buki3.get(0).getAbl();
											hit = Buki3.get(0).getHit();
											sss = Buki3.size();
											Sks = Buki3.get(0).getKs();
											BUKIn = Buki3.get(0).getWname();
										} else {
											List<Bukim> Buki4 = Findbuki.search_wno(result_Bk1.get(i).getWno4());
											syubetu = Buki4.get(0).getSyubetu();
											syatei = Buki4.get(0).getSyatei();
											at = Buki4.get(0).getAt();
											ap = Buki4.get(0).getAp();

											dmg = Buki4.get(0).getDmg();
											abl = Buki4.get(0).getAbl();
											hit = Buki4.get(0).getHit();
											sss = Buki4.size();
											Sks = Buki4.get(0).getKs();
											BUKIn = Buki4.get(0).getWname();
										}

										//ここで武器ごとに射程確認
										if ((sss >= 1) && (syatei >= kyori)) {

											if (Sks.equals("1D")) {
												ks = random.nextInt(5) + 1;
											} else if (Sks.equals("2D")) {
												ks = random.nextInt(5) + random.nextInt(5) + 2;
											} else if (Sks.equals("3D")) {
												ks = random.nextInt(5) + random.nextInt(5) + random.nextInt(5) + 3;
											} else {
												ks = Integer.parseInt(Sks);
											}

											if ((syubetu.equals("ラピッド")) && ((syatei / 2) >= kyori)) {
												ks = (ks * 2);
											}

											//武器の射撃回数分まわす
											for (int sbki = 0; sbki <= ks - 1; sbki++) {

												//相手が存在するなら
												if (Skeizoku == 1) {
													damageF = 0;
													SVRF = 0;

													//命中判定
													//	int randomValue1 = random.nextInt(5);
													// result_zid
													// result_Ck22.get().getMvg();

													//		int hit1 = 0;
													//		int hit2 = 0;

													//		int DMGC1 = 0;
													//		int DMGC2 = 0;
													if (BUKIn != "装備無し") {
														if (result_zid.get(0).getScs() <= 1 + hit + random.nextInt(5)) {

															hit1 = hit1 + 1;

															int rr = random.nextInt(5) + 1;

															//ダメージ判定
															if ((at >= EnTaff * 2) && (rr >= 2)) {
																damageF = 1;
															} else if ((at >= EnTaff) && (rr >= 3)) {
																damageF = 1;
															} else if ((at == EnTaff) && (rr >= 4)) {
																damageF = 1;
															} else if ((at < EnTaff) && (rr >= 5)) {
																damageF = 1;
															} else if ((at * 2 <= EnTaff) && (rr >= 6)) {
																damageF = 1;
															} else {
																damageF = 0;
															}

														}

														// List<Unitrekka> result_zid =

														//致傷量
														if (damageF == 1) { //ダメージ判定 成功なら

															DMGC1 = DMGC1 + 1;

															//相手側のセーヴィング

															int SVR = random.nextInt(5) + 1;

															//スペシャルセーブなし
															if (EnSV2 == 0) {
																if ((SVR >= (EnSV1 + ap))) {
																	SVRF = 1;
																} else {
																	SVRF = 0;
																}
															} else {
																if (((EnSV1 + ap) >= EnSV2) && (SVR >= EnSV2)) {
																	SVRF = 1;
																} else if (((EnSV1 + ap) < EnSV2)
																		&& (SVR >= (EnSV1 + ap))) {
																	SVRF = 1;
																} else {
																	SVRF = 0;
																}
															}
														}

														//		int UUNZ1 = 0;
														//		int UUNZ2 = 0;	
														//		Gekiha1 = 0;
														//撃破数
														if ((SVRF == 0) && (damageF == 1)) {

															if (dmg.equals("1D")) {
																DMGM = random.nextInt(5) + 1;
															} else if (dmg.equals("2D")) {
																DMGM = random.nextInt(5) + random.nextInt(5) + 2;
															} else if (dmg.equals("3D")) {
																DMGM = random.nextInt(5) + random.nextInt(5)
																		+ random.nextInt(5)
																		+ 3;
															} else {
																DMGM = Integer.parseInt(dmg);
															}

															UUNZ1 = UUNZ1 + DMGM;

															//ウーンズを減らし、死亡の場合はレコード削除
															if ((EnUUNZ - DMGM) > 0) {
																//	result_Ck22.get().getId();

																//
																//	battlekousei2.setUunz(result_Ck22.get().getUunz() - DMGM);

																//		Battlekousei2 battlekousei2 = new Battlekousei2();

																battlekousei2.setId(battlekousei2.getId());
																battlekousei2.setAbl(battlekousei2.getAbl());
																battlekousei2.setAk(battlekousei2.getAk());
																battlekousei2.setAsa(battlekousei2.getAsa());
																battlekousei2.setAssp(battlekousei2.getAssp());
																battlekousei2.setAt(battlekousei2.getAt());
																battlekousei2.setBid(battlekousei2.getBid());
																battlekousei2.setWno(battlekousei2.getWno());
																battlekousei2.setWno2(battlekousei2.getWno2());
																battlekousei2.setWno3(battlekousei2.getWno3());
																battlekousei2.setWno4(battlekousei2.getWno4());
																battlekousei2.setWno5(battlekousei2.getWno5());
																battlekousei2.setWno6(battlekousei2.getWno6());
																battlekousei2.setWno7(battlekousei2.getWno7());

																battlekousei2.setKid(battlekousei2.getKid());
																battlekousei2.setKname(battlekousei2.getKname());

																battlekousei2.setLd(battlekousei2.getLd());
																battlekousei2.setMvg(battlekousei2.getMvg());
																battlekousei2.setTaff(battlekousei2.getTaff());
																battlekousei2.setUunz(battlekousei2.getUunz() - DMGM);

																//	battlekousei2.setCh(0);
																//	battlekousei2.setDeath(0);

																EnUUNZ = (EnUUNZ - DMGM);

																battlekousei2.setZid(battlekousei2.getZid());

																repoBattlekousei2.saveAndFlush(battlekousei2);

															} else {
																//相手が死亡する場合　相手を削除して　次を抽出する

																Gekiha1 = Gekiha1 + 1;

																TimeUnit.MICROSECONDS.sleep(100);

																repoBattlekousei2.deleteById(EnId);

																//後攻が全滅している場合を想定して
																List<Battlekousei2> result_Ck23 = repoBattlekousei2
																		.findAll();
																if (result_Ck23 != null && result_Ck23.size() >= 1) {

																	//ランダムにひとり取り出して距離をとる
																	//	Optional<Battlekousei2> result_Ck23 = selectOneRandomId2();

																	battlekousei2.setId(result_Ck23.get(0).getId());
																	battlekousei2.setAbl(result_Ck23.get(0).getAbl());
																	battlekousei2.setAk(result_Ck23.get(0).getAk());
																	battlekousei2.setAsa(result_Ck23.get(0).getAsa());
																	battlekousei2.setAssp(result_Ck23.get(0).getAssp());
																	battlekousei2.setAt(result_Ck23.get(0).getAt());
																	battlekousei2.setBid(result_Ck23.get(0).getBid());
																	battlekousei2.setWno(result_Ck23.get(0).getWno());
																	battlekousei2.setWno2(result_Ck23.get(0).getWno2());
																	battlekousei2.setWno3(result_Ck23.get(0).getWno3());
																	battlekousei2.setWno4(result_Ck23.get(0).getWno4());
																	battlekousei2.setWno5(result_Ck23.get(0).getWno5());
																	battlekousei2.setWno6(result_Ck23.get(0).getWno6());
																	battlekousei2.setWno7(result_Ck23.get(0).getWno7());

																	battlekousei2.setKid(result_Ck23.get(0).getKid());
																	battlekousei2
																			.setKname(result_Ck23.get(0).getKname());

																	battlekousei2.setLd(result_Ck23.get(0).getLd());
																	battlekousei2.setMvg(result_Ck23.get(0).getMvg());
																	battlekousei2.setTaff(result_Ck23.get(0).getTaff());
																	battlekousei2.setUunz(result_Ck23.get(0).getUunz());
																	battlekousei2.setZid(result_Ck23.get(0).getZid());

																	//		battlekousei2.setCh(0);
																	battlekousei2.setDeath(1);

																	kyori = result_Bk1.get(i).getMvg()
																			+ result_Ck23.get(0).getMvg();

																	EnId = result_Ck23.get(0).getId();
																	EnTaff = result_Ck23.get(0).getTaff();
																	EnSV1 = result_Ck23.get(0).getAsa();
																	EnSV2 = result_Ck23.get(0).getAssp();
																	EnUUNZ = result_Ck23.get(0).getUunz();

																} else {
																	BTComment = "後攻が全滅";
																	Skeizoku = 0;
																	//	break;
																}
															}

														}

													}
												}
											}

										}

									}

								} else if (formBattlemode.getBmode1() != 1) {

									//追加移動分の処理

									//	List<Battlekousei1> result_Bk11 = repoBattlekousei1.findAll(Sort.by(Sort.Direction.DESC, "id"));
									if (result_Bk1 != null && result_Bk1.size() >= 1) {

										Battlekousei1 battlekousei1 = new Battlekousei1();

										battlekousei1.setId(result_Bk1.get(i).getId());
										battlekousei1.setAbl(result_Bk1.get(i).getAbl());
										battlekousei1.setAk(result_Bk1.get(i).getAk());
										battlekousei1.setAsa(result_Bk1.get(i).getAsa());
										battlekousei1.setAssp(result_Bk1.get(i).getAssp());
										battlekousei1.setAt(result_Bk1.get(i).getAt());
										battlekousei1.setBid(result_Bk1.get(i).getBid());
										battlekousei1.setWno(result_Bk1.get(i).getWno());
										battlekousei1.setWno2(result_Bk1.get(i).getWno2());
										battlekousei1.setWno3(result_Bk1.get(i).getWno3());
										battlekousei1.setWno4(result_Bk1.get(i).getWno4());
										battlekousei1.setWno5(result_Bk1.get(i).getWno5());
										battlekousei1.setWno6(result_Bk1.get(i).getWno6());
										battlekousei1.setWno7(result_Bk1.get(i).getWno7());

										battlekousei1.setKid(result_Bk1.get(i).getKid());
										battlekousei1.setKname(result_Bk1.get(i).getKname());

										battlekousei1.setLd(result_Bk1.get(i).getLd());

										//		battlekousei1.setCh(result_Bk1.get(i).getCh());
										//		battlekousei1.setDeath(0);

										if ((result_Bk1.get(i).getMvg() - ((random.nextInt(5) + 1))) < 0) {

											battlekousei1.setMvg(0);
										} else {
											battlekousei1
													.setMvg(result_Bk1.get(i).getMvg() - ((random.nextInt(5) + 1)));
										}

										battlekousei1.setTaff(result_Bk1.get(i).getTaff());
										battlekousei1.setUunz(result_Bk1.get(i).getUunz());
										battlekousei1.setZid(result_Bk1.get(i).getZid());

										repoBattlekousei1.saveAndFlush(battlekousei1);
									} else {
										Skeizoku = 0;
									}

								}

								/////////

							}
						} else {
							BTComment = "後攻が全滅";
							Skeizoku = 0;
						}
					}
				}
			} else {
				//先攻が全滅した時の処理を後で追加すること

			}

			Asult();

			//後攻側 移動処理
			IDOUF = 0;
			if (formBattlemode.getBmode2() == 1) {
			} else {
				IDOUF = 1;
			}
			//移動処理が存在する場合、移動する
			if (IDOUF == 1) {
				List<Battlekousei2> result_Bk11 = repoBattlekousei2.findAll(Sort.by(Sort.Direction.DESC, "id"));
				if (result_Bk11 != null && result_Bk11.size() >= 1) {
					for (int i = 0; i <= result_Bk11.size() - 1; i++) {

						if (result_Bk11.get(i).getMvg() == 0) {
						} else {

							//構成員の現在の能力値をとりだす
							List<Unitrekka> result_zid = findUnitrekkaRepository.search_zid(result_Bk11.get(i).getZid(),
									result_Bk11.get(i).getUunz());

							if (result_zid != null && result_zid.size() >= 1) {

								Battlekousei2 battlekousei2 = new Battlekousei2();

								battlekousei2.setId(result_Bk11.get(i).getId());
								battlekousei2.setAbl(result_Bk11.get(i).getAbl());
								battlekousei2.setAk(result_Bk11.get(i).getAk());
								battlekousei2.setAsa(result_Bk11.get(i).getAsa());
								battlekousei2.setAssp(result_Bk11.get(i).getAssp());
								battlekousei2.setAt(result_Bk11.get(i).getAt());
								battlekousei2.setBid(result_Bk11.get(i).getBid());
								battlekousei2.setWno(result_Bk11.get(i).getWno());
								battlekousei2.setWno2(result_Bk11.get(i).getWno2());
								battlekousei2.setWno3(result_Bk11.get(i).getWno3());
								battlekousei2.setWno4(result_Bk11.get(i).getWno4());
								battlekousei2.setWno5(result_Bk11.get(i).getWno5());
								battlekousei2.setWno6(result_Bk11.get(i).getWno6());
								battlekousei2.setWno7(result_Bk11.get(i).getWno7());

								battlekousei2.setKid(result_Bk11.get(i).getKid());
								battlekousei2.setKname(result_Bk11.get(i).getKname());

								battlekousei2.setLd(result_Bk11.get(i).getLd());

								//		battlekousei2.setCh(result_Bk1.get(i).getCh());
								//		battlekousei2.setDeath(0);

								if ((result_Bk11.get(i).getMvg() - result_zid.get(0).getMv()) < 0) {

									battlekousei2.setMvg(0);
								} else {
									battlekousei2.setMvg(result_Bk11.get(i).getMvg() - result_zid.get(0).getMv());
								}

								battlekousei2.setTaff(result_Bk11.get(i).getTaff());
								battlekousei2.setUunz(result_Bk11.get(i).getUunz());
								battlekousei2.setZid(result_Bk11.get(i).getZid());

								repoBattlekousei2.saveAndFlush(battlekousei2);

							}
						}
					}
				}

			}

			Skeizoku = 0;

			///後攻側
			///後攻側
			///後攻側
			///後攻側
			//後攻側を一体ずつ射撃で処理
			List<Battlekousei2> result_Bk2 = repoBattlekousei2.findAll(Sort.by(Sort.Direction.DESC, "id"));

			if (result_Bk2 != null && result_Bk2.size() >= 1) {
				for (int i = 0; i <= result_Bk2.size() - 1; i++) {

					if (result_Bk2.get(i).getMvg() == 0) {
					} else {

						kyori = result_Bk2.get(i).getMvg();

						//先攻が全滅している場合を想定して
						List<Battlekousei1> result_Ck22 = repoBattlekousei1.findAll();
						if (result_Ck22 != null && result_Ck22.size() >= 1) {

							Skeizoku = 1;
							//ランダムにひとり取り出して距離をとる
							//		Optional<Battlekousei1> result_Ck22 = selectOneRandomId1();

							Battlekousei1 battlekousei1 = new Battlekousei1();

							battlekousei1.setId(result_Ck22.get(0).getId());
							battlekousei1.setAbl(result_Ck22.get(0).getAbl());
							battlekousei1.setAk(result_Ck22.get(0).getAk());
							battlekousei1.setAsa(result_Ck22.get(0).getAsa());
							battlekousei1.setAssp(result_Ck22.get(0).getAssp());
							battlekousei1.setAt(result_Ck22.get(0).getAt());
							battlekousei1.setBid(result_Ck22.get(0).getBid());
							battlekousei1.setWno(result_Ck22.get(0).getWno());
							battlekousei1.setWno2(result_Ck22.get(0).getWno2());
							battlekousei1.setWno3(result_Ck22.get(0).getWno3());
							battlekousei1.setWno4(result_Ck22.get(0).getWno4());
							battlekousei1.setWno5(result_Ck22.get(0).getWno5());
							battlekousei1.setWno6(result_Ck22.get(0).getWno6());
							battlekousei1.setWno7(result_Ck22.get(0).getWno7());

							battlekousei1.setKid(result_Ck22.get(0).getKid());
							battlekousei1.setKname(result_Ck22.get(0).getKname());

							battlekousei1.setLd(result_Ck22.get(0).getLd());
							battlekousei1.setMvg(result_Ck22.get(0).getMvg());
							battlekousei1.setTaff(result_Ck22.get(0).getTaff());
							battlekousei1.setUunz(result_Ck22.get(0).getUunz());
							battlekousei1.setZid(result_Ck22.get(0).getZid());

							//	battlekousei1.setCh(result_Ck22.get(i).getCh());
							//	battlekousei1.setDeath(0);

							kyori = kyori + result_Ck22.get(0).getMvg();

							EnId = result_Ck22.get(0).getId();
							EnTaff = result_Ck22.get(0).getTaff();
							EnSV1 = result_Ck22.get(0).getAsa();
							EnSV2 = result_Ck22.get(0).getAssp();
							EnUUNZ = result_Ck22.get(0).getUunz();

							//ここで検索してうまくできるか　テスト					

							//構成員の現在の能力値をとりだす
							List<Unitrekka> result_zid = findUnitrekkaRepository.search_zid(result_Bk2.get(i).getZid(),
									result_Bk2.get(i).getUunz());

							if (result_zid != null && result_zid.size() >= 1) {

								chargeF = 0;
								//射程内なら射撃 1-4
								if ((Findbuki.search_wno(result_Bk2.get(i).getWno()).get(0).getSyatei() >= kyori)
										|| (Findbuki.search_wno(result_Bk2.get(i).getWno2()).get(0)
												.getSyatei() >= kyori)
										|| (Findbuki.search_wno(result_Bk2.get(i).getWno3()).get(0)
												.getSyatei() >= kyori)
										|| (Findbuki.search_wno(result_Bk2.get(i).getWno4()).get(0)
												.getSyatei() >= kyori)) {
									chargeF = 1;
								}

								// 
								if ((chargeF == 1) && (formBattlemode.getBmode2() != 3)) {
									//1-4 でまわす なるべく簡潔化したい
									for (int kk = 0; kk <= 3; kk++) {

										if (Skeizoku == 1) {
											if (kk == 0) {
												List<Bukim> Buki1 = Findbuki.search_wno(result_Bk2.get(i).getWno());
												syubetu = Buki1.get(0).getSyubetu();
												syatei = Buki1.get(0).getSyatei();
												at = Buki1.get(0).getAt();
												ap = Buki1.get(0).getAp();

												dmg = Buki1.get(0).getDmg();
												abl = Buki1.get(0).getAbl();
												hit = Buki1.get(0).getHit();
												sss = Buki1.size();
												Sks = Buki1.get(0).getKs();

												BUKIn = Buki1.get(0).getWname();

											} else if (kk == 1) {
												List<Bukim> Buki2 = Findbuki.search_wno(result_Bk2.get(i).getWno2());
												syubetu = Buki2.get(0).getSyubetu();
												syatei = Buki2.get(0).getSyatei();
												at = Buki2.get(0).getAt();
												ap = Buki2.get(0).getAp();

												dmg = Buki2.get(0).getDmg();
												abl = Buki2.get(0).getAbl();
												hit = Buki2.get(0).getHit();
												sss = Buki2.size();
												Sks = Buki2.get(0).getKs();
												BUKIn = Buki2.get(0).getWname();
											} else if (kk == 2) {
												List<Bukim> Buki3 = Findbuki.search_wno(result_Bk2.get(i).getWno3());
												syubetu = Buki3.get(0).getSyubetu();
												syatei = Buki3.get(0).getSyatei();
												at = Buki3.get(0).getAt();
												ap = Buki3.get(0).getAp();

												dmg = Buki3.get(0).getDmg();
												abl = Buki3.get(0).getAbl();
												hit = Buki3.get(0).getHit();
												sss = Buki3.size();
												Sks = Buki3.get(0).getKs();
												BUKIn = Buki3.get(0).getWname();
											} else {
												List<Bukim> Buki4 = Findbuki.search_wno(result_Bk2.get(i).getWno4());
												syubetu = Buki4.get(0).getSyubetu();
												syatei = Buki4.get(0).getSyatei();
												at = Buki4.get(0).getAt();
												ap = Buki4.get(0).getAp();

												dmg = Buki4.get(0).getDmg();
												abl = Buki4.get(0).getAbl();
												hit = Buki4.get(0).getHit();
												sss = Buki4.size();
												Sks = Buki4.get(0).getKs();
												BUKIn = Buki4.get(0).getWname();
											}

											//ここで武器ごとに射程確認
											if ((sss >= 1) && (syatei >= kyori)) {

												if (Sks.equals("1D")) {
													ks = random.nextInt(5) + 1;
												} else if (Sks.equals("2D")) {
													ks = random.nextInt(5) + random.nextInt(5) + 2;
												} else if (Sks.equals("3D")) {
													ks = random.nextInt(5) + random.nextInt(5) + random.nextInt(5) + 3;
												} else if (Sks.equals("4D")) {
													ks = random.nextInt(5) + random.nextInt(5) + random.nextInt(5)
															+ +random.nextInt(5) + 4;
												} else {
													ks = Integer.parseInt(Sks);
												}

												// (syubetu.equals("ラピッド")) {
												if ((syubetu.equals("ラピッド")) && ((syatei / 2) >= kyori)) {
													ks = (ks * 2);
												}

												//武器の射撃回数分まわす
												for (int sbki = 0; sbki <= ks - 1; sbki++) {

													damageF = 0;
													SVRF = 0;

													//命中判定
													//	int randomValue1 = random.nextInt(5);
													// result_zid
													// result_Ck22.get().getMvg();

													//		int hit1 = 0;
													//		int hit2 = 0;

													//		int DMGC1 = 0;
													//		int DMGC2 = 0;
													if (BUKIn != "装備無し") {
														if (result_zid.get(0).getScs() <= 1 + hit + random.nextInt(5)) {

															hit2 = hit2 + 1;

															int rr = random.nextInt(5) + 1;

															//ダメージ判定
															if ((at >= EnTaff * 2) && (rr >= 2)) {
																damageF = 1;
															} else if ((at >= EnTaff) && (rr >= 3)) {
																damageF = 1;
															} else if ((at == EnTaff) && (rr >= 4)) {
																damageF = 1;
															} else if ((at < EnTaff) && (rr >= 5)) {
																damageF = 1;
															} else if ((at * 2 <= EnTaff) && (rr >= 6)) {
																damageF = 1;
															} else {
																damageF = 0;
															}

														}

														// List<Unitrekka> result_zid =

														//致傷量
														if (damageF == 1) { //ダメージ判定 成功なら

															DMGC2 = DMGC2 + 1;

															//相手側のセーヴィング

															int SVR = random.nextInt(5) + 1;

															//スペシャルセーブなし
															if (EnSV2 == 0) {
																if ((SVR >= (EnSV1 + ap))) {
																	SVRF = 1;
																} else {
																	SVRF = 0;
																}
															} else {
																if (((EnSV1 + ap) >= EnSV2) && (SVR >= EnSV2)) {
																	SVRF = 1;
																} else if (((EnSV1 + ap) < EnSV2)
																		&& (SVR >= (EnSV1 + ap))) {
																	SVRF = 1;
																} else {
																	SVRF = 0;
																}
															}
														}

														//		int UUNZ1 = 0;
														//		int UUNZ2 = 0;	
														//		Gekiha1 = 0;
														//撃破数
														if ((SVRF == 0) && (damageF == 1)) {

															if (dmg.equals("1D")) {
																DMGM = random.nextInt(5) + 1;
															} else if (dmg.equals("2D")) {
																DMGM = random.nextInt(5) + random.nextInt(5) + 2;
															} else if (dmg.equals("3D")) {
																DMGM = random.nextInt(5) + random.nextInt(5)
																		+ random.nextInt(5)
																		+ 3;
															} else if (dmg.equals("4D")) {
																DMGM = random.nextInt(5) + random.nextInt(5)
																		+ random.nextInt(5)
																		+ random.nextInt(5)
																		+ 4;

															} else {
																DMGM = Integer.parseInt(dmg);
															}

															UUNZ2 = UUNZ2 + DMGM;

															//ウーンズを減らし、死亡の場合はレコード削除
															if ((EnUUNZ - DMGM) > 0) {
																//	result_Ck22.get().getId();

																//
																//	battlekousei2.setUunz(result_Ck22.get().getUunz() - DMGM);

																//		Battlekousei2 battlekousei2 = new Battlekousei2();

																battlekousei1.setId(battlekousei1.getId());
																battlekousei1.setAbl(battlekousei1.getAbl());
																battlekousei1.setAk(battlekousei1.getAk());
																battlekousei1.setAsa(battlekousei1.getAsa());
																battlekousei1.setAssp(battlekousei1.getAssp());
																battlekousei1.setAt(battlekousei1.getAt());
																battlekousei1.setBid(battlekousei1.getBid());
																battlekousei1.setWno(battlekousei1.getWno());
																battlekousei1.setWno2(battlekousei1.getWno2());
																battlekousei1.setWno3(battlekousei1.getWno3());
																battlekousei1.setWno4(battlekousei1.getWno4());
																battlekousei1.setWno5(battlekousei1.getWno5());
																battlekousei1.setWno6(battlekousei1.getWno6());
																battlekousei1.setWno7(battlekousei1.getWno7());

																battlekousei1.setKid(battlekousei1.getKid());
																battlekousei1.setKname(battlekousei1.getKname());

																battlekousei1.setLd(battlekousei1.getLd());
																battlekousei1.setMvg(battlekousei1.getMvg());
																battlekousei1.setTaff(battlekousei1.getTaff());
																battlekousei1.setUunz(battlekousei1.getUunz() - DMGM);

																//	battlekousei1.setCh(0);
																//	battlekousei1.setDeath(0);

																EnUUNZ = (EnUUNZ - DMGM);

																battlekousei1.setZid(battlekousei1.getZid());

																repoBattlekousei1.saveAndFlush(battlekousei1);

															} else {
																//相手が死亡する場合　相手を削除して　次を抽出する

																Gekiha2 = Gekiha2 + 1;
																TimeUnit.MICROSECONDS.sleep(100);
																repoBattlekousei1.deleteById(EnId);

																//先攻が全滅している場合を想定して
																List<Battlekousei1> result_Ck23 = repoBattlekousei1
																		.findAll();
																if (result_Ck23 != null && result_Ck23.size() >= 1) {

																	//ランダムにひとり取り出して距離をとる
																	//	Optional<Battlekousei1> result_Ck23 = selectOneRandomId1();

																	battlekousei1.setId(result_Ck23.get(0).getId());
																	battlekousei1.setAbl(result_Ck23.get(0).getAbl());
																	battlekousei1.setAk(result_Ck23.get(0).getAk());
																	battlekousei1.setAsa(result_Ck23.get(0).getAsa());
																	battlekousei1.setAssp(result_Ck23.get(0).getAssp());
																	battlekousei1.setAt(result_Ck23.get(0).getAt());
																	battlekousei1.setBid(result_Ck23.get(0).getBid());
																	battlekousei1.setWno(result_Ck23.get(0).getWno());
																	battlekousei1.setWno2(result_Ck23.get(0).getWno2());
																	battlekousei1.setWno3(result_Ck23.get(0).getWno3());
																	battlekousei1.setWno4(result_Ck23.get(0).getWno4());
																	battlekousei1.setWno5(result_Ck23.get(0).getWno5());
																	battlekousei1.setWno6(result_Ck23.get(0).getWno6());
																	battlekousei1.setWno7(result_Ck23.get(0).getWno7());

																	battlekousei1.setKid(result_Ck23.get(0).getKid());
																	battlekousei1
																			.setKname(result_Ck23.get(0).getKname());

																	battlekousei1.setLd(result_Ck23.get(0).getLd());
																	battlekousei1.setMvg(result_Ck23.get(0).getMvg());
																	battlekousei1.setTaff(result_Ck23.get(0).getTaff());
																	battlekousei1.setUunz(result_Ck23.get(0).getUunz());
																	battlekousei1.setZid(result_Ck23.get(0).getZid());

																	//	battlekousei1.setCh(0);
																	//		battlekousei1.setDeath(0);

																	kyori = result_Bk2.get(i).getMvg()
																			+ result_Ck23.get(0).getMvg();

																	EnId = result_Ck23.get(0).getId();
																	EnTaff = result_Ck23.get(0).getTaff();
																	EnSV1 = result_Ck23.get(0).getAsa();
																	EnSV2 = result_Ck23.get(0).getAssp();
																	EnUUNZ = result_Ck23.get(0).getUunz();
																} else {
																	BTComment = "先攻が全滅";
																	Skeizoku = 0;
																	//	break;
																}
															}

														}

													}
												}

											}
										}
									}

								} else if (formBattlemode.getBmode2() != 1) {

									//追加移動分の処理

									//	List<Battlekousei1> result_Bk11 = repoBattlekousei1.findAll(Sort.by(Sort.Direction.DESC, "id"));
									if (result_Bk2 != null && result_Bk2.size() >= 1) {

										Battlekousei2 battlekousei2 = new Battlekousei2();

										battlekousei2.setId(result_Bk2.get(i).getId());
										battlekousei2.setAbl(result_Bk2.get(i).getAbl());
										battlekousei2.setAk(result_Bk2.get(i).getAk());
										battlekousei2.setAsa(result_Bk2.get(i).getAsa());
										battlekousei2.setAssp(result_Bk2.get(i).getAssp());
										battlekousei2.setAt(result_Bk2.get(i).getAt());
										battlekousei2.setBid(result_Bk2.get(i).getBid());
										battlekousei2.setWno(result_Bk2.get(i).getWno());
										battlekousei2.setWno2(result_Bk2.get(i).getWno2());
										battlekousei2.setWno3(result_Bk2.get(i).getWno3());
										battlekousei2.setWno4(result_Bk2.get(i).getWno4());
										battlekousei2.setWno5(result_Bk2.get(i).getWno5());
										battlekousei2.setWno6(result_Bk2.get(i).getWno6());
										battlekousei2.setWno7(result_Bk2.get(i).getWno7());

										battlekousei2.setKid(result_Bk2.get(i).getKid());
										battlekousei2.setKname(result_Bk2.get(i).getKname());

										battlekousei2.setLd(result_Bk2.get(i).getLd());

										//			battlekousei2.setCh(result_Bk2.get(i).getCh());
										//			battlekousei2.setDeath(0);

										if ((result_Bk2.get(i).getMvg() - ((random.nextInt(5) + 1))) < 0) {

											battlekousei2.setMvg(0);
										} else {
											battlekousei2
													.setMvg(result_Bk2.get(i).getMvg() - ((random.nextInt(5) + 1)));
										}

										battlekousei2.setTaff(result_Bk2.get(i).getTaff());
										battlekousei2.setUunz(result_Bk2.get(i).getUunz());
										battlekousei2.setZid(result_Bk2.get(i).getZid());

										repoBattlekousei2.saveAndFlush(battlekousei2);

									}

								}

							}
							BTComment = "距離" + kyori + "";
						} else {
							BTComment = "先攻が全滅";
							Skeizoku = 0;
						}
					}
				}
			} else {
				//先攻が全滅した時の処理を後で追加すること

			}

			
			
			Asult();
			
	

			Battlereport battlereport = new Battlereport();

			battlereport.setSno(1);
			battlereport.setTno(tn + 1);

			//射撃での成果
			battlereport.setHit1(hit1);
			battlereport.setHit2(hit2);

			battlereport.setDamage1(DMGC1);
			battlereport.setDamage2(DMGC2);

			battlereport.setUunz1(UUNZ1);
			battlereport.setUunz2(UUNZ2);

			battlereport.setKia1(Gekiha1);
			battlereport.setKia2(Gekiha2);

			//白兵での成果
			battlereport.setAhit1(ahit1);
			battlereport.setAhit2(ahit2);

			battlereport.setAdamage1(aDMGC1);
			battlereport.setAdamage2(aDMGC2);

			battlereport.setAuunz1(aUUNZ1);
			battlereport.setAuunz2(aUUNZ2);

			battlereport.setAkia1(akia1);
			battlereport.setAkia2(akia2);

			battlereport.setComme(BTComment);

			repoBattlereport.saveAndFlush(battlereport);

			if ((BTComment.equals("先攻が全滅")) || (BTComment.equals("後攻が全滅"))) {

				break;

			}

			if ((BTComment.equals("白兵戦で先攻が全滅")) || (BTComment.equals("白兵戦で後攻が全滅"))) {

				break;

			}
		}

		//		List<Unitjyouhou> result = search_t(unitjyouhou.getSid(), unitjyouhou.getYid());

		//		model.addAttribute("chapter1List", repoChapter_unit1.findAll(Sort.by(Sort.Direction.ASC, "id")));
		//		model.addAttribute("kouseiList", repoChapter_kousei1.findAll(Sort.by(Sort.Direction.ASC, "id")));

		//		model.addAttribute("jyouhouList", result);

		// reportList
		model.addAttribute("reportList", repoBattlereport.findAll(Sort.by(Sort.Direction.ASC, "id")));

		//	formBattlemode.setBmvc(20);
		//	formBattlemode.setTrik(1);
		return "redirect:/battle/index";

	}

	public void Asult() throws InterruptedException {

		Random random = new Random();

		/////////////
		/////////////
		/////////////白兵戦闘の処理 START

		Skeizoku = 0;

		List<Battlekousei1> result_AS10 = findBK1Repo.search_LIVE(0);
		if (result_AS10 != null && result_AS10.size() >= 1) {
			List<Battlekousei2> result_AS20 = findBK2Repo.search_LIVE(0);
			if (result_AS20 != null && result_AS20.size() >= 1) {

				Skeizoku = 2;

				//白兵用のサブテーブルをデータ全削除
				repoBattlekousei10.deleteAll();
				repoBattlekousei20.deleteAll();

				for (int i = 0; i <= result_AS10.size() - 1; i++) {

					Battlekousei10 battlekousei10 = new Battlekousei10();

					battlekousei10.setAbl(result_AS10.get(i).getAbl());
					battlekousei10.setAk(result_AS10.get(i).getAk());
					battlekousei10.setAsa(result_AS10.get(i).getAsa());
					battlekousei10.setAssp(result_AS10.get(i).getAssp());
					battlekousei10.setAt(result_AS10.get(i).getAt());
					battlekousei10.setBid(result_AS10.get(i).getBid());
					battlekousei10.setWno(result_AS10.get(i).getWno());
					battlekousei10.setWno2(result_AS10.get(i).getWno2());
					battlekousei10.setWno3(result_AS10.get(i).getWno3());
					battlekousei10.setWno4(result_AS10.get(i).getWno4());
					battlekousei10.setWno5(result_AS10.get(i).getWno5());
					battlekousei10.setWno6(result_AS10.get(i).getWno6());
					battlekousei10.setWno7(result_AS10.get(i).getWno7());

					battlekousei10.setKid(result_AS10.get(i).getKid());
					battlekousei10.setKname(result_AS10.get(i).getKname());

					battlekousei10.setLd(result_AS10.get(i).getLd());
					battlekousei10.setMvg(0);
					battlekousei10.setTaff(result_AS10.get(i).getTaff());
					battlekousei10.setUunz(result_AS10.get(i).getUunz());
					battlekousei10.setZid(result_AS10.get(i).getZid());

					//	battlekousei1.setCh(0);

					//	battlekousei1.setDeath(0);

					if (i < result_AS10.size() - 1) {
						repoBattlekousei10.save(battlekousei10);
					} else {
						repoBattlekousei10.save(battlekousei10);
						repoBattlekousei10.flush();
					}

				}

				for (int i = 0; i <= result_AS20.size() - 1; i++) {

					Battlekousei20 battlekousei20 = new Battlekousei20();

					battlekousei20.setAbl(result_AS20.get(i).getAbl());
					battlekousei20.setAk(result_AS20.get(i).getAk());
					battlekousei20.setAsa(result_AS20.get(i).getAsa());
					battlekousei20.setAssp(result_AS20.get(i).getAssp());
					battlekousei20.setAt(result_AS20.get(i).getAt());
					battlekousei20.setBid(result_AS20.get(i).getBid());
					battlekousei20.setWno(result_AS20.get(i).getWno());
					battlekousei20.setWno2(result_AS20.get(i).getWno2());
					battlekousei20.setWno3(result_AS20.get(i).getWno3());
					battlekousei20.setWno4(result_AS20.get(i).getWno4());
					battlekousei20.setWno5(result_AS20.get(i).getWno5());
					battlekousei20.setWno6(result_AS20.get(i).getWno6());
					battlekousei20.setWno7(result_AS20.get(i).getWno7());

					battlekousei20.setKid(result_AS20.get(i).getKid());
					battlekousei20.setKname(result_AS20.get(i).getKname());

					battlekousei20.setLd(result_AS20.get(i).getLd());
					battlekousei20.setMvg(0);
					battlekousei20.setTaff(result_AS20.get(i).getTaff());
					battlekousei20.setUunz(result_AS20.get(i).getUunz());
					battlekousei20.setZid(result_AS20.get(i).getZid());

					//	battlekousei1.setCh(0);

					//	battlekousei1.setDeath(0);

					if (i < result_AS20.size() - 1) {
						repoBattlekousei20.save(battlekousei20);
					} else {
						repoBattlekousei20.save(battlekousei20);
						repoBattlekousei20.flush();
					}

				}
			}
		}

		//両方に白兵戦ができるユニット構成員が存在する場合
		if (Skeizoku == 2) {



			Skeizoku = 0;

			//先攻		

			List<Battlekousei10> result_AS1 = repoBattlekousei10.findAll();
			if (result_AS1 != null && result_AS1.size() >= 1) {
				for (int i = 0; i <= result_AS1.size() - 1; i++) {

					Battlekousei1 battlekousei1 = new Battlekousei1();
					battlekousei1.setId(result_AS1.get(0).getId());
					battlekousei1.setAbl(result_AS1.get(0).getAbl());
					battlekousei1.setAk(result_AS1.get(0).getAk());
					battlekousei1.setAsa(result_AS1.get(0).getAsa());
					battlekousei1.setAssp(result_AS1.get(0).getAssp());
					battlekousei1.setAt(result_AS1.get(0).getAt());
					battlekousei1.setBid(result_AS1.get(0).getBid());
					battlekousei1.setWno(result_AS1.get(0).getWno());
					battlekousei1.setWno2(result_AS1.get(0).getWno2());
					battlekousei1.setWno3(result_AS1.get(0).getWno3());
					battlekousei1.setWno4(result_AS1.get(0).getWno4());
					battlekousei1.setWno5(result_AS1.get(0).getWno5());
					battlekousei1.setWno6(result_AS1.get(0).getWno6());
					battlekousei1.setWno7(result_AS1.get(0).getWno7());

					battlekousei1.setKid(result_AS1.get(0).getKid());
					battlekousei1.setKname(result_AS1.get(0).getKname());

					battlekousei1.setLd(result_AS1.get(0).getLd());
					battlekousei1.setMvg(result_AS1.get(0).getMvg());
					battlekousei1.setTaff(result_AS1.get(0).getTaff());
					battlekousei1.setUunz(result_AS1.get(0).getUunz());
					battlekousei1.setZid(result_AS1.get(0).getZid());

					//battlekousei1.setDeath(result_AS1.get(0).getDeath());
					//		battlekousei1.setCh(1);

					//	repoBattlekousei1.saveAndFlush(battlekousei1);

					//構成員の現在の能力値をとりだす
					List<Unitrekka> result_zid = findUnitrekkaRepository.search_zid(result_AS1.get(i).getZid(),
							result_AS1.get(i).getUunz());

					if (result_zid != null && result_zid.size() >= 1) {
						//		chargeF = 0;

						//		if ((Findbuki.search_wno(result_Bk1.get(i).getWno()).get(0).getSyatei() >= kyori)
						//				|| (Findbuki.search_wno(result_Bk1.get(i).getWno2()).get(0).getSyatei() >= kyori)
						//				|| (Findbuki.search_wno(result_Bk1.get(i).getWno3()).get(0).getSyatei() >= kyori)
						//				|| (Findbuki.search_wno(result_Bk1.get(i).getWno4()).get(0).getSyatei() >= kyori)) {
						//			chargeF = 1;
						//		}

						kyori = result_AS1.get(i).getMvg();

						//後攻が全滅している場合を想定して
						List<Battlekousei2> result_Ck22 = findBK2Repo.search_LIVE(0);
						if (result_Ck22 != null && result_Ck22.size() >= 1) {

							Skeizoku = 1;

							Battlekousei2 battlekousei2 = new Battlekousei2();

							battlekousei2.setId(result_Ck22.get(0).getId());
							battlekousei2.setAbl(result_Ck22.get(0).getAbl());
							battlekousei2.setAk(result_Ck22.get(0).getAk());
							battlekousei2.setAsa(result_Ck22.get(0).getAsa());
							battlekousei2.setAssp(result_Ck22.get(0).getAssp());
							battlekousei2.setAt(result_Ck22.get(0).getAt());
							battlekousei2.setBid(result_Ck22.get(0).getBid());
							battlekousei2.setWno(result_Ck22.get(0).getWno());
							battlekousei2.setWno2(result_Ck22.get(0).getWno2());
							battlekousei2.setWno3(result_Ck22.get(0).getWno3());
							battlekousei2.setWno4(result_Ck22.get(0).getWno4());
							battlekousei2.setWno5(result_Ck22.get(0).getWno5());
							battlekousei2.setWno6(result_Ck22.get(0).getWno6());
							battlekousei2.setWno7(result_Ck22.get(0).getWno7());

							battlekousei2.setKid(result_Ck22.get(0).getKid());
							battlekousei2.setKname(result_Ck22.get(0).getKname());

							battlekousei2.setLd(result_Ck22.get(0).getLd());
							battlekousei2.setMvg(result_Ck22.get(0).getMvg());
							battlekousei2.setTaff(result_Ck22.get(0).getTaff());
							battlekousei2.setUunz(result_Ck22.get(0).getUunz());
							battlekousei2.setZid(result_Ck22.get(0).getZid());

							//	battlekousei2.setDeath(0);
							//	battlekousei2.setCh(1);

							//	repoBattlekousei2.saveAndFlush(battlekousei2);

							kyori = kyori + result_Ck22.get(0).getMvg();

							EnId = result_Ck22.get(0).getId();
							EnTaff = result_Ck22.get(0).getTaff();
							EnSV1 = result_Ck22.get(0).getAsa();
							EnSV2 = result_Ck22.get(0).getAssp();
							EnUUNZ = result_Ck22.get(0).getUunz();

							for (int kk = 0; kk <= 2; kk++) {

								if (Skeizoku == 1) {
									if (kk == 0) {
										List<Bukim> Buki1 = Findbuki.search_wno(result_AS1.get(i).getWno5());
										syubetu = Buki1.get(0).getSyubetu();
										syatei = Buki1.get(0).getSyatei();
										at = Buki1.get(0).getAt();
										ap = Buki1.get(0).getAp();

										dmg = Buki1.get(0).getDmg();
										abl = Buki1.get(0).getAbl();
										hit = Buki1.get(0).getHit();
										sss = Buki1.size();
										Sks = Buki1.get(0).getKs();

										BUKIn = Buki1.get(0).getWname();

									} else if (kk == 1) {
										List<Bukim> Buki2 = Findbuki.search_wno(result_AS1.get(i).getWno6());
										syubetu = Buki2.get(0).getSyubetu();
										syatei = Buki2.get(0).getSyatei();
										at = Buki2.get(0).getAt();
										ap = Buki2.get(0).getAp();

										dmg = Buki2.get(0).getDmg();
										abl = Buki2.get(0).getAbl();
										hit = Buki2.get(0).getHit();
										sss = Buki2.size();
										Sks = Buki2.get(0).getKs();
										BUKIn = Buki2.get(0).getWname();
									} else if (kk == 2) {
										List<Bukim> Buki3 = Findbuki.search_wno(result_AS1.get(i).getWno7());
										syubetu = Buki3.get(0).getSyubetu();
										syatei = Buki3.get(0).getSyatei();
										at = Buki3.get(0).getAt();
										ap = Buki3.get(0).getAp();

										dmg = Buki3.get(0).getDmg();
										abl = Buki3.get(0).getAbl();
										hit = Buki3.get(0).getHit();
										sss = Buki3.size();
										Sks = Buki3.get(0).getKs();
										BUKIn = Buki3.get(0).getWname();

									}

									sss = 0;
									// (BUKIn.equals("装備無し")) {
									if (BUKIn.equals("装備無し")) {
										//白兵武器1だけ特別に装備として処理
										if (kk == 0) {
											at = result_AS1.get(0).getAt();
											dmg = "1";
											ks = result_AS1.get(0).getAk();
											sss = 1;

										} else {

											sss = 0;

										}
									} else {
										//名前がある白兵武器の処理

										if (at == 20) {
											// X2 のパターン
											at = (at * 2);
										} else {
											//武器攻撃力に装備者の攻撃力を加算
											at = at + result_AS1.get(0).getAt();
										}

										if (kk == 0) {
											ks = result_AS1.get(0).getAk();
										} else {
											ks = Integer.parseInt(Sks);
										}

										sss = 1;
									}

									//////

									if ((sss >= 1) && (Skeizoku == 1)) {
										for (int sbki = 0; sbki <= ks - 1; sbki++) { // 2*
											if (result_zid.get(0).getScs() <= 1 + hit + random.nextInt(5)) {

												ahit1 = ahit1 + 1;

												int rr = random.nextInt(5) + 1;

												//ダメージ判定
												if ((at >= EnTaff * 2) && (rr >= 2)) {
													damageF = 1;
												} else if ((at >= EnTaff) && (rr >= 3)) {
													damageF = 1;
												} else if ((at == EnTaff) && (rr >= 4)) {
													damageF = 1;
												} else if ((at < EnTaff) && (rr >= 5)) {
													damageF = 1;
												} else if ((at * 2 <= EnTaff) && (rr >= 6)) {
													damageF = 1;
												} else {
													damageF = 0;
												}

											}

											// List<Unitrekka> result_zid =

											//致傷量
											if (damageF == 1) { //ダメージ判定 成功なら

												aDMGC1 = aDMGC1 + 1;

												//相手側のセーヴィング

												int SVR = random.nextInt(5) + 1;

												//スペシャルセーブなし
												if (EnSV2 == 0) {
													if ((SVR >= (EnSV1 + ap))) {
														SVRF = 1;
													} else {
														SVRF = 0;
													}
												} else {
													if (((EnSV1 + ap) >= EnSV2) && (SVR >= EnSV2)) {
														SVRF = 1;
													} else if (((EnSV1 + ap) < EnSV2) && (SVR >= (EnSV1 + ap))) {
														SVRF = 1;
													} else {
														SVRF = 0;
													}
												}
											}

											//撃破数
											if ((SVRF == 0) && (damageF == 1)) {

												if (dmg.equals("1D")) {
													DMGM = random.nextInt(5) + 1;
												} else if (dmg.equals("2D")) {
													DMGM = random.nextInt(5) + random.nextInt(5) + 2;
												} else if (dmg.equals("3D")) {
													DMGM = random.nextInt(5) + random.nextInt(5) + random.nextInt(5)
															+ 3;
												} else {
													DMGM = Integer.parseInt(dmg);
												}

												aUUNZ1 = aUUNZ1 + DMGM;

												//ウーンズを減らし、死亡の場合はレコード削除
												if ((EnUUNZ - DMGM) > 0) {

													battlekousei2.setId(battlekousei2.getId());
													battlekousei2.setAbl(battlekousei2.getAbl());
													battlekousei2.setAk(battlekousei2.getAk());
													battlekousei2.setAsa(battlekousei2.getAsa());
													battlekousei2.setAssp(battlekousei2.getAssp());
													battlekousei2.setAt(battlekousei2.getAt());
													battlekousei2.setBid(battlekousei2.getBid());
													battlekousei2.setWno(battlekousei2.getWno());
													battlekousei2.setWno2(battlekousei2.getWno2());
													battlekousei2.setWno3(battlekousei2.getWno3());
													battlekousei2.setWno4(battlekousei2.getWno4());
													battlekousei2.setWno5(battlekousei2.getWno5());
													battlekousei2.setWno6(battlekousei2.getWno6());
													battlekousei2.setWno7(battlekousei2.getWno7());

													battlekousei2.setKid(battlekousei2.getKid());
													battlekousei2.setKname(battlekousei2.getKname());

													battlekousei2.setLd(battlekousei2.getLd());
													battlekousei2.setMvg(battlekousei2.getMvg());
													battlekousei2.setTaff(battlekousei2.getTaff());
													battlekousei2.setUunz(battlekousei2.getUunz() - DMGM);

													//	battlekousei2.setDeath(0);
													//		battlekousei2.setCh(1);

													EnUUNZ = (EnUUNZ - DMGM);

													battlekousei2.setZid(battlekousei2.getZid());

													repoBattlekousei2.saveAndFlush(battlekousei2);

												} else {

													akia1 = akia1 + 1;
													TimeUnit.MICROSECONDS.sleep(100);
													repoBattlekousei2.deleteById(battlekousei2.getId());

													List<Battlekousei2> result_Ck25 = findBK2Repo.search_LIVE(0);
													if (result_Ck25 != null && result_Ck25.size() >= 1) {

														//Battlekousei2 battlekousei2 = new Battlekousei2();

														battlekousei2.setId(result_Ck25.get(0).getId());
														battlekousei2.setAbl(result_Ck25.get(0).getAbl());
														battlekousei2.setAk(result_Ck25.get(0).getAk());
														battlekousei2.setAsa(result_Ck25.get(0).getAsa());
														battlekousei2.setAssp(result_Ck25.get(0).getAssp());
														battlekousei2.setAt(result_Ck25.get(0).getAt());
														battlekousei2.setBid(result_Ck25.get(0).getBid());
														battlekousei2.setWno(result_Ck25.get(0).getWno());
														battlekousei2.setWno2(result_Ck25.get(0).getWno2());
														battlekousei2.setWno3(result_Ck25.get(0).getWno3());
														battlekousei2.setWno4(result_Ck25.get(0).getWno4());
														battlekousei2.setWno5(result_Ck25.get(0).getWno5());
														battlekousei2.setWno6(result_Ck25.get(0).getWno6());
														battlekousei2.setWno7(result_Ck25.get(0).getWno7());

														battlekousei2.setKid(result_Ck25.get(0).getKid());
														battlekousei2.setKname(result_Ck25.get(0).getKname());

														battlekousei2.setLd(result_Ck25.get(0).getLd());
														battlekousei2.setMvg(result_Ck25.get(0).getMvg());
														battlekousei2.setTaff(result_Ck25.get(0).getTaff());
														battlekousei2.setUunz(result_Ck25.get(0).getUunz());
														battlekousei2.setZid(result_Ck25.get(0).getZid());

														//			battlekousei2.setDeath(0);
														//	battlekousei2.setCh(1);

														kyori = kyori + result_Ck25.get(0).getMvg();

														EnId = result_Ck25.get(0).getId();
														EnTaff = result_Ck25.get(0).getTaff();
														EnSV1 = result_Ck25.get(0).getAsa();
														EnSV2 = result_Ck25.get(0).getAssp();
														EnUUNZ = result_Ck25.get(0).getUunz();

													} else {
														Skeizoku = 0;
														//	BTComment = "後攻が全滅";
													}

												}
											}
										}
									}
								}

							}
						} else {

							Skeizoku = 0;
							//	BTComment = "後攻が全滅";
						}
					}

				}
			}

			//後攻

			//	List<Battlekousei2> result_AS2 = findBK2Repo.search_LIVE(0);
			List<Battlekousei20> result_AS2 = repoBattlekousei20.findAll();
			if (result_AS2 != null && result_AS2.size() >= 1) {
				for (int i = 0; i <= result_AS2.size() - 1; i++) {

					Battlekousei2 battlekousei2 = new Battlekousei2();
					battlekousei2.setId(result_AS2.get(0).getId());
					battlekousei2.setAbl(result_AS2.get(0).getAbl());
					battlekousei2.setAk(result_AS2.get(0).getAk());
					battlekousei2.setAsa(result_AS2.get(0).getAsa());
					battlekousei2.setAssp(result_AS2.get(0).getAssp());
					battlekousei2.setAt(result_AS2.get(0).getAt());
					battlekousei2.setBid(result_AS2.get(0).getBid());
					battlekousei2.setWno(result_AS2.get(0).getWno());
					battlekousei2.setWno2(result_AS2.get(0).getWno2());
					battlekousei2.setWno3(result_AS2.get(0).getWno3());
					battlekousei2.setWno4(result_AS2.get(0).getWno4());
					battlekousei2.setWno5(result_AS2.get(0).getWno5());
					battlekousei2.setWno6(result_AS2.get(0).getWno6());
					battlekousei2.setWno7(result_AS2.get(0).getWno7());

					battlekousei2.setKid(result_AS2.get(0).getKid());
					battlekousei2.setKname(result_AS2.get(0).getKname());

					battlekousei2.setLd(result_AS2.get(0).getLd());
					battlekousei2.setMvg(result_AS2.get(0).getMvg());
					battlekousei2.setTaff(result_AS2.get(0).getTaff());
					battlekousei2.setUunz(result_AS2.get(0).getUunz());
					battlekousei2.setZid(result_AS2.get(0).getZid());

					//	battlekousei2.setDeath(result_AS2.get(0).getDeath());
					//		battlekousei1.setCh(1);

					//	repoBattlekousei2.saveAndFlush(battlekousei2);

					//構成員の現在の能力値をとりだす
					List<Unitrekka> result_zid = findUnitrekkaRepository.search_zid(result_AS2.get(i).getZid(),
							result_AS2.get(i).getUunz());

					if (result_zid != null && result_zid.size() >= 1) {
						//		chargeF = 0;

						//		if ((Findbuki.search_wno(result_Bk1.get(i).getWno()).get(0).getSyatei() >= kyori)
						//				|| (Findbuki.search_wno(result_Bk1.get(i).getWno2()).get(0).getSyatei() >= kyori)
						//				|| (Findbuki.search_wno(result_Bk1.get(i).getWno3()).get(0).getSyatei() >= kyori)
						//				|| (Findbuki.search_wno(result_Bk1.get(i).getWno4()).get(0).getSyatei() >= kyori)) {
						//			chargeF = 1;
						//		}

						kyori = result_AS2.get(i).getMvg();

						//先攻が全滅している場合を想定して
						List<Battlekousei1> result_Ck22 = findBK1Repo.search_LIVE(0);
						if (result_Ck22 != null && result_Ck22.size() >= 1) {

							Skeizoku = 1;

							Battlekousei1 battlekousei1 = new Battlekousei1();

							battlekousei1.setId(result_Ck22.get(0).getId());
							battlekousei1.setAbl(result_Ck22.get(0).getAbl());
							battlekousei1.setAk(result_Ck22.get(0).getAk());
							battlekousei1.setAsa(result_Ck22.get(0).getAsa());
							battlekousei1.setAssp(result_Ck22.get(0).getAssp());
							battlekousei1.setAt(result_Ck22.get(0).getAt());
							battlekousei1.setBid(result_Ck22.get(0).getBid());
							battlekousei1.setWno(result_Ck22.get(0).getWno());
							battlekousei1.setWno2(result_Ck22.get(0).getWno2());
							battlekousei1.setWno3(result_Ck22.get(0).getWno3());
							battlekousei1.setWno4(result_Ck22.get(0).getWno4());
							battlekousei1.setWno5(result_Ck22.get(0).getWno5());
							battlekousei1.setWno6(result_Ck22.get(0).getWno6());
							battlekousei1.setWno7(result_Ck22.get(0).getWno7());

							battlekousei1.setKid(result_Ck22.get(0).getKid());
							battlekousei1.setKname(result_Ck22.get(0).getKname());

							battlekousei1.setLd(result_Ck22.get(0).getLd());
							battlekousei1.setMvg(result_Ck22.get(0).getMvg());
							battlekousei1.setTaff(result_Ck22.get(0).getTaff());
							battlekousei1.setUunz(result_Ck22.get(0).getUunz());
							battlekousei1.setZid(result_Ck22.get(0).getZid());

							//	battlekousei2.setDeath(0);
							//	battlekousei2.setCh(1);

							//	repoBattlekousei1.saveAndFlush(battlekousei1);

							kyori = kyori + result_Ck22.get(0).getMvg();

							EnId = result_Ck22.get(0).getId();
							EnTaff = result_Ck22.get(0).getTaff();
							EnSV1 = result_Ck22.get(0).getAsa();
							EnSV2 = result_Ck22.get(0).getAssp();
							EnUUNZ = result_Ck22.get(0).getUunz();

							for (int kk = 0; kk <= 2; kk++) {

								if (Skeizoku == 1) {
									if (kk == 0) {
										List<Bukim> Buki1 = Findbuki.search_wno(result_AS2.get(i).getWno5());
										syubetu = Buki1.get(0).getSyubetu();
										syatei = Buki1.get(0).getSyatei();
										at = Buki1.get(0).getAt();
										ap = Buki1.get(0).getAp();

										dmg = Buki1.get(0).getDmg();
										abl = Buki1.get(0).getAbl();
										hit = Buki1.get(0).getHit();
										sss = Buki1.size();
										Sks = Buki1.get(0).getKs();

										BUKIn = Buki1.get(0).getWname();

									} else if (kk == 1) {
										List<Bukim> Buki2 = Findbuki.search_wno(result_AS2.get(i).getWno6());
										syubetu = Buki2.get(0).getSyubetu();
										syatei = Buki2.get(0).getSyatei();
										at = Buki2.get(0).getAt();
										ap = Buki2.get(0).getAp();

										dmg = Buki2.get(0).getDmg();
										abl = Buki2.get(0).getAbl();
										hit = Buki2.get(0).getHit();
										sss = Buki2.size();
										Sks = Buki2.get(0).getKs();
										BUKIn = Buki2.get(0).getWname();
									} else if (kk == 2) {
										List<Bukim> Buki3 = Findbuki.search_wno(result_AS2.get(i).getWno7());
										syubetu = Buki3.get(0).getSyubetu();
										syatei = Buki3.get(0).getSyatei();
										at = Buki3.get(0).getAt();
										ap = Buki3.get(0).getAp();

										dmg = Buki3.get(0).getDmg();
										abl = Buki3.get(0).getAbl();
										hit = Buki3.get(0).getHit();
										sss = Buki3.size();
										Sks = Buki3.get(0).getKs();
										BUKIn = Buki3.get(0).getWname();

									}

									sss = 0;
									// (BUKIn.equals("装備無し")) {
									if (BUKIn.equals("装備無し")) {
										//白兵武器1だけ特別に装備として処理
										//最初の白兵武器のみ
										if (kk == 0) {
											at = result_AS2.get(0).getAt();
											dmg = "1";
											ks = result_AS2.get(0).getAk();
											sss = 1;

										} else {

											sss = 0;

										}
									} else {
										//名前がある白兵武器の処理

										if (at == 20) {
											// X2 のパターン
											at = (at * 2);
										} else {
											//武器攻撃力に装備者の攻撃力を加算
											at = at + result_AS2.get(0).getAt();
										}

										if (kk == 0) {
											ks = result_AS2.get(0).getAk();
										} else {
											ks = Integer.parseInt(Sks);
										}

										sss = 1;
									}

									//////

									if ((sss >= 1)) {
										for (int sbki = 0; sbki <= ks - 1; sbki++) { // 2*

											if (result_zid.get(0).getScs() <= 1 + hit + random.nextInt(5)) {

												ahit2 = ahit2 + 1;

												int rr = random.nextInt(5) + 1;

												//ダメージ判定
												if ((at >= EnTaff * 2) && (rr >= 2)) {
													damageF = 1;
												} else if ((at >= EnTaff) && (rr >= 3)) {
													damageF = 1;
												} else if ((at == EnTaff) && (rr >= 4)) {
													damageF = 1;
												} else if ((at < EnTaff) && (rr >= 5)) {
													damageF = 1;
												} else if ((at * 2 <= EnTaff) && (rr >= 6)) {
													damageF = 1;
												} else {
													damageF = 0;
												}

											}

											// List<Unitrekka> result_zid =

											//致傷量
											if (damageF == 1) { //ダメージ判定 成功なら

												aDMGC2 = aDMGC2 + 1;

												//相手側のセーヴィング

												int SVR = random.nextInt(5) + 1;

												//スペシャルセーブなし
												if (EnSV2 == 0) {
													if ((SVR >= (EnSV1 + ap))) {
														SVRF = 1;
													} else {
														SVRF = 0;
													}
												} else {
													if (((EnSV1 + ap) >= EnSV2) && (SVR >= EnSV2)) {
														SVRF = 1;
													} else if (((EnSV1 + ap) < EnSV2) && (SVR >= (EnSV1 + ap))) {
														SVRF = 1;
													} else {
														SVRF = 0;
													}
												}
											}

											//撃破数
											if ((SVRF == 0) && (damageF == 1)) {

												if (dmg.equals("1D")) {
													DMGM = random.nextInt(5) + 1;
												} else if (dmg.equals("2D")) {
													DMGM = random.nextInt(5) + random.nextInt(5) + 2;
												} else if (dmg.equals("3D")) {
													DMGM = random.nextInt(5) + random.nextInt(5) + random.nextInt(5)
															+ 3;
												} else {
													DMGM = Integer.parseInt(dmg);
												}

												aUUNZ2 = aUUNZ2 + DMGM;

												//ウーンズを減らし、死亡の場合はレコード削除
												if ((EnUUNZ - DMGM) > 0) {

													battlekousei1.setId(battlekousei1.getId());
													battlekousei1.setAbl(battlekousei1.getAbl());
													battlekousei1.setAk(battlekousei1.getAk());
													battlekousei1.setAsa(battlekousei1.getAsa());
													battlekousei1.setAssp(battlekousei1.getAssp());
													battlekousei1.setAt(battlekousei1.getAt());
													battlekousei1.setBid(battlekousei1.getBid());
													battlekousei1.setWno(battlekousei1.getWno());
													battlekousei1.setWno2(battlekousei1.getWno2());
													battlekousei1.setWno3(battlekousei1.getWno3());
													battlekousei1.setWno4(battlekousei1.getWno4());
													battlekousei1.setWno5(battlekousei1.getWno5());
													battlekousei1.setWno6(battlekousei1.getWno6());
													battlekousei1.setWno7(battlekousei1.getWno7());

													battlekousei1.setKid(battlekousei1.getKid());
													battlekousei1.setKname(battlekousei1.getKname());

													battlekousei1.setLd(battlekousei1.getLd());
													battlekousei1.setMvg(battlekousei1.getMvg());
													battlekousei1.setTaff(battlekousei1.getTaff());
													battlekousei1.setUunz(battlekousei1.getUunz() - DMGM);

													//	battlekousei2.setDeath(0);
													//		battlekousei2.setCh(1);

													EnUUNZ = (EnUUNZ - DMGM);

													battlekousei1.setZid(battlekousei1.getZid());

													repoBattlekousei1.saveAndFlush(battlekousei1);

												} else {

													akia2 = akia2 + 1;
													/*
																											battlekousei1.setId(battlekousei1.getId());
																											battlekousei1.setAbl(battlekousei1.getAbl());
																											battlekousei1.setAk(battlekousei1.getAk());
																											battlekousei1.setAsa(battlekousei1.getAsa());
																											battlekousei1.setAssp(battlekousei1.getAssp());
																											battlekousei1.setAt(battlekousei1.getAt());
																											battlekousei1.setBid(battlekousei1.getBid());
																											battlekousei1.setWno(battlekousei1.getWno());
																											battlekousei1.setWno2(battlekousei1.getWno2());
																											battlekousei1.setWno3(battlekousei1.getWno3());
																											battlekousei1.setWno4(battlekousei1.getWno4());
																											battlekousei1.setWno5(battlekousei1.getWno5());
																											battlekousei1.setWno6(battlekousei1.getWno6());
																											battlekousei1.setWno7(battlekousei1.getWno7());
													
																											battlekousei1.setKid(battlekousei1.getKid());
																											battlekousei1.setKname(battlekousei1.getKname());
													
																											battlekousei1.setLd(battlekousei1.getLd());
																											battlekousei1.setMvg(battlekousei1.getMvg());
																											battlekousei1.setTaff(battlekousei1.getTaff());
																											battlekousei1.setUunz(battlekousei1.getUunz() - DMGM);
													
																											battlekousei1.setZid(battlekousei1.getZid());
													
																											battlekousei1.setDeath(1);
																											//		battlekousei2.setCh(1);
													*/

													TimeUnit.MICROSECONDS.sleep(100);
													repoBattlekousei1.deleteById(battlekousei1.getId());

													List<Battlekousei1> result_Ck25 = findBK1Repo.search_LIVE(0);
													if (result_Ck25 != null && result_Ck25.size() >= 1) {

														//Battlekousei2 battlekousei2 = new Battlekousei2();

														battlekousei1.setId(result_Ck25.get(0).getId());
														battlekousei1.setAbl(result_Ck25.get(0).getAbl());
														battlekousei1.setAk(result_Ck25.get(0).getAk());
														battlekousei1.setAsa(result_Ck25.get(0).getAsa());
														battlekousei1.setAssp(result_Ck25.get(0).getAssp());
														battlekousei1.setAt(result_Ck25.get(0).getAt());
														battlekousei1.setBid(result_Ck25.get(0).getBid());
														battlekousei1.setWno(result_Ck25.get(0).getWno());
														battlekousei1.setWno2(result_Ck25.get(0).getWno2());
														battlekousei1.setWno3(result_Ck25.get(0).getWno3());
														battlekousei1.setWno4(result_Ck25.get(0).getWno4());
														battlekousei1.setWno5(result_Ck25.get(0).getWno5());
														battlekousei1.setWno6(result_Ck25.get(0).getWno6());
														battlekousei1.setWno7(result_Ck25.get(0).getWno7());

														battlekousei1.setKid(result_Ck25.get(0).getKid());
														battlekousei1.setKname(result_Ck25.get(0).getKname());

														battlekousei1.setLd(result_Ck25.get(0).getLd());
														battlekousei1.setMvg(result_Ck25.get(0).getMvg());
														battlekousei1.setTaff(result_Ck25.get(0).getTaff());
														battlekousei1.setUunz(result_Ck25.get(0).getUunz());
														battlekousei1.setZid(result_Ck25.get(0).getZid());

														//			battlekousei2.setDeath(0);
														//	battlekousei2.setCh(1);

														kyori = kyori + result_Ck25.get(0).getMvg();

														EnId = result_Ck25.get(0).getId();
														EnTaff = result_Ck25.get(0).getTaff();
														EnSV1 = result_Ck25.get(0).getAsa();
														EnSV2 = result_Ck25.get(0).getAssp();
														EnUUNZ = result_Ck25.get(0).getUunz();

													} else {
														Skeizoku = 0;
														//		BTComment = "先攻が全滅";
													}

												}
											}
										}

									}
								}
							}
						} else {
							//		Skeizoku = 0;
							//		BTComment = "先攻が全滅";
						}

					}

				}
			}

			List<Battlekousei1> result_Bk11 = repoBattlekousei1.findAll(Sort.by(Sort.Direction.DESC, "id"));
			if (result_Bk11 != null && result_Bk11.size() >= 1) {
			} else {
				BTComment = "白兵戦で先攻が全滅";
			}

			List<Battlekousei2> result_Bk12 = repoBattlekousei2.findAll(Sort.by(Sort.Direction.DESC, "id"));
			if (result_Bk12 != null && result_Bk12.size() >= 1) {
			} else {
				BTComment = "白兵戦で後攻が全滅";
			}
			TimeUnit.MICROSECONDS.sleep(100);
		}

	}

	/**
	public Optional<Battlekousei1> selectOneRandomId1() {
		Integer randId = randomBK1.getRandomId();
	
		if (randId == null) {
			return Optional.empty();
		}
		return randomBK1.findById(randId);
	}
	
	public Optional<Battlekousei2> selectOneRandomId2() {
		Integer randId = randomBK2.getRandomId();
	
		if (randId == null) {
			return Optional.empty();
		}
		return randomBK2.findById(randId);
	}
	***/

}
