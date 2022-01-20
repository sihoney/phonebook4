package com.javaex.dao;

import java.util.List;

import com.javaex.vo.PersonVo;

public class TestDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> list = phoneDao.selectPerson();
		
		for(PersonVo pvo : list) {
			System.out.println(pvo);
		}
	}

}
