package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping("/phone")
public class PhoneController {
	
	///////////////////////
	// field
	///////////////////////
	@Autowired
	private PhoneDao phoneDao;  
	
	///////////////////////
	// constructor
	///////////////////////
	
	
	///////////////////////
	// method
	/////////////////////// 
	@RequestMapping(value="list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Model  model) {
		
		// 다오에서 리스트를 가져온다.
		List<PersonVo> personList = phoneDao.selectPerson();
		
		// 컨트롤러 --> DS 데이터를 보낸다. (model)
		model.addAttribute("personList", personList);
		
		// jsp정보를 리턴한다 (view)
		return "list"; // viewResolver
	}
	
	@RequestMapping(value="writeForm", method=RequestMethod.GET)
	public String writeForm() {
		
		return "writeForm";
	}
	
	
	@RequestMapping(value="write", method=RequestMethod.GET)
	public String write(@ModelAttribute PersonVo pvo) {
		// @ModelAttribute: param과 일치하는 setter를 찾아 값을 넣어준다. (default 생성자를 꼭 정의!)	
		
		phoneDao.insertPerson(pvo);
		
		return "redirect:/phone/list";
	}
	
	
	@RequestMapping(value="delete", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam int personId) {
		
		phoneDao.deletePerson(personId);
		
		return "redirect:/phone/list";
	}
	
	
	@RequestMapping("updateForm")
	public String updateForm(Model model, @RequestParam int personId) {
		
		PersonVo pvo = phoneDao.getPerson(personId);
		
		model.addAttribute("pvo", pvo);
		
		return "updateForm";
	}
	
	
	@RequestMapping("update")
	public String udpate(@ModelAttribute PersonVo pvo) {
		
		phoneDao.updatePerson(pvo);

		return "redirect:/phone/list";
	}
	
	@RequestMapping(value="/view", method= {RequestMethod.GET, RequestMethod.POST})
	public String view(@RequestParam(value="no", required=false, defaultValue="-1") int no) {
		System.out.println(no + "번 글 가져오기");
		
		return "redirect:/phone/list";
	}
	
	@RequestMapping(value="/{no}/{num}/view", method={RequestMethod.GET, RequestMethod.POST})
	public String view(@PathVariable(value="no") int no, @PathVariable("num") int num) {
		System.out.println(no + "번 글입니다." + num + "개 검색되었습니다.");
		
		return "redirect:/phone/list";
	}
}
