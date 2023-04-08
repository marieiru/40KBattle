package com.example.KBattle.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.KBattle.entity.Unitjyouhou;
import com.example.KBattle.form.FormUnitjyouhou;
import com.example.KBattle.repository.FindunitjyouhouRepository;
import com.example.KBattle.repository.SlgmRepositorySendan;
import com.example.KBattle.repository.SlgmRepositoryUnitbunrui;
import com.example.KBattle.repository.SlgmRepositoryUnitjyouhou;
import com.example.KBattle.repository.SlgmRepositoryUnityakuwari;

@Controller
@RequestMapping("/unitjyouhou_f")
public class Unitjyouhou_fController {

	
	@Autowired
	SlgmRepositoryUnitjyouhou repository;
	
	@Autowired
	SlgmRepositorySendan categoryRepository;	
	

	@Autowired
	SlgmRepositoryUnitbunrui unitbunruiRepository;		
	
	@Autowired
	SlgmRepositoryUnityakuwari unityakuwariRepository;		

	@Autowired
	FindunitjyouhouRepository findunitjyouhou;			
	
    //検索結果の受け取り処理
    //@ModelAttributeでformからformModelを受け取り、
    //その型(BookData)と変数(bookdata)を指定する
    @GetMapping("/select")
    public String select(@ModelAttribute("formModel") Unitjyouhou unitjyouhou, Model model) {

        model.addAttribute("msg", "検索結果");
        //bookdataのゲッターで各値を取得する
        List<Unitjyouhou> result = search(unitjyouhou.getSid(),unitjyouhou.getYid());

		model.addAttribute("prefecturesList", categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));   
//		model.addAttribute("unitbunrui", unitbunruiRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
		model.addAttribute("unityakuwari", unityakuwariRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
      
		model.addAttribute("jyouhouList", result);

        return "unitjyouhou_f";
    }	
	
	
    //検索
    public List<Unitjyouhou> search(Integer sid, Integer yid){

        List<Unitjyouhou> result = new ArrayList<Unitjyouhou>();

        //すべてブランクだった場合は全件検索する
     //   if ("".equals(genre) && "".equals(author) && "".equals(title)){
        if ((sid)==0 && (yid)==0){
        	result = repository.findAll(Sort.by(Sort.Direction.ASC, "zid"));
        }
        else {
            //上記以外の場合、BookDataDaoImplのメソッドを呼び出す
            result = findunitjyouhou.search(sid,yid);
        }
        return result;
    }
    
    
//検索関連			
	
	
		
	
	
	@ModelAttribute
	public FormUnitjyouhou setForm() {
		return new FormUnitjyouhou();
	}

	@GetMapping("/index")
	public String unitjyouhou(FormUnitjyouhou formUnitjyouhou,Model model) {
		model.addAttribute("prefecturesList", categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
//		model.addAttribute("unitbunrui", unitbunruiRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
		model.addAttribute("unityakuwari", unityakuwariRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
		
		model.addAttribute("jyouhouList", repository.findAll(Sort.by(Sort.Direction.ASC, "zid")));
		formUnitjyouhou.setNewUnitjyouhou(true);
		return "unitjyouhou_f";
	}

	@GetMapping
	public String showList(FormUnitjyouhou formUnitjyouhou, Model model) {

		formUnitjyouhou.setNewUnitjyouhou(true);
		Iterable<Unitjyouhou> list = selectAll();

		model.addAttribute("list", list);
		model.addAttribute("title", "登録用フォーム");
		return "unitjyouhou_f";

	}



	@GetMapping("/{zid}")
	public String showUpdatet(FormUnitjyouhou formUnitjyouhou, @PathVariable Integer zid, Model model) {

		model.addAttribute("prefecturesList", categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
//		model.addAttribute("unitbunrui", unitbunruiRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
		model.addAttribute("unityakuwari", unityakuwariRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));		
		
		
		Optional<Unitjyouhou> unitjyouhouOpt = selectOneById(zid);

		Optional<FormUnitjyouhou> unitjyouhouFormOpt = unitjyouhouOpt.map(t -> makeFormUnitjyouhou(t));

		if (unitjyouhouFormOpt.isPresent()) {
			formUnitjyouhou = unitjyouhouFormOpt.get();
		}

		makeUpdateModel(formUnitjyouhou, model);
		return "unitjyouhou_f";

	}

	private void makeUpdateModel(FormUnitjyouhou formUnitjyouhou, Model model) {

		model.addAttribute("zid", formUnitjyouhou.getZid());
		formUnitjyouhou.setNewUnitjyouhou(false);
		model.addAttribute("formUnitjyouhou", formUnitjyouhou);
		model.addAttribute("title", "更新用フォーム");

	}


	@PostMapping("/delete")
	public String delete(@RequestParam("zid") String zid, Model model, RedirectAttributes redirectAttributes) {

		deleteUnitjyouhouById(Integer.parseInt(zid));
		redirectAttributes.addFlashAttribute("delcomplete", "削除が完了しました。");
		return "redirect:/unitjyouhou/index";

	}

	private Unitjyouhou makeUnitjyouhou(FormUnitjyouhou formUnitjyouhou) {

		Unitjyouhou unitjyouhou = new Unitjyouhou();
		
		unitjyouhou.setZid(formUnitjyouhou.getZid());

		unitjyouhou.setYuname(formUnitjyouhou.getYuname());
		
		unitjyouhou.setSid(formUnitjyouhou.getSid());
		
		unitjyouhou.setYid(formUnitjyouhou.getYid());		
		
		unitjyouhou.setPck(formUnitjyouhou.getPck());		
		unitjyouhou.setMck(formUnitjyouhou.getMck());	
		unitjyouhou.setNinnzuu(formUnitjyouhou.getNinnzuu());		
		unitjyouhou.setZouin(formUnitjyouhou.getZouin());
		unitjyouhou.setSousuu(formUnitjyouhou.getSousuu());
		unitjyouhou.setKbzouin(formUnitjyouhou.getKbzouin());
		unitjyouhou.setImg(formUnitjyouhou.getImg());		
		
		return unitjyouhou;

	}

	private FormUnitjyouhou makeFormUnitjyouhou(Unitjyouhou unitjyouhou) {

		FormUnitjyouhou form = new FormUnitjyouhou();

		form.setZid(unitjyouhou.getZid());
	
		form.setYuname(unitjyouhou.getYuname());
		
		form.setSid(unitjyouhou.getSid());
		
		form.setYid(unitjyouhou.getYid());	
		
		form.setPck(unitjyouhou.getPck());		
		form.setMck(unitjyouhou.getMck());	
		form.setNinnzuu(unitjyouhou.getNinnzuu());		
		form.setZouin(unitjyouhou.getZouin());
		form.setSousuu(unitjyouhou.getSousuu());
		form.setKbzouin(unitjyouhou.getKbzouin());
		form.setImg(unitjyouhou.getImg());				
		
		form.setNewUnitjyouhou(false);
		return form;

	}

	public void insertUnitjyouhou(Unitjyouhou unitjyouhou) {
		repository.saveAndFlush(unitjyouhou);
	}

	public void updateUnitjyouhou(Unitjyouhou unitjyouhou) {
		repository.saveAndFlush(unitjyouhou);
	}

	public void deleteUnitjyouhouById(Integer zid) {
		repository.deleteById(zid);
	}

	public Iterable<Unitjyouhou> selectAll() {

		return repository.findAll(Sort.by(Sort.Direction.ASC, "zid"));
	}

	public Optional<Unitjyouhou> selectOneById(Integer zid) {
		return repository.findById(zid);
	}	
		
	
	
	
}
