package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {

	/////////////////////
	// field
	/////////////////////
	@Autowired
	private SqlSession sqlSession;

	Connection conn;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/////////////////////
	// constructor
	/////////////////////
	public PhoneDao() {

	}

	/////////////////////
	// method
	/////////////////////
	public List<PersonVo> selectPerson() {
		System.out.println("PhoneDao > selectPerson()");

		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList");
		return personList;
	}
	
	public void insertPerson(PersonVo pvo) {
		System.out.println("PhoneDao > insertPerson()");
		
		int count = sqlSession.insert("phonebook.insert", pvo);
		System.out.println(count + "건이 추가되었습니다.");
	}
	
	public void deletePerson(int personId) {
		System.out.println("PhoneDao > deletePerson()");
		
		int count = sqlSession.delete("phonebook.delete", personId);
		System.out.println(count + "건이 삭제되었습니다.");
	}
	
	public PersonVo getPerson(int personId) {
		System.out.println("PhoneDao > getPerson()");
		
		PersonVo pvo = sqlSession.selectOne("phonebook.selectOne", personId);
		return pvo;
	}

	public void updatePerson(PersonVo pvo) {
		System.out.println("PersonDao > updatePerson()");
		
		int count = sqlSession.update("phonebook.update", pvo);
		System.out.println(count + "건이 수정되었습니다.");
	}
}
