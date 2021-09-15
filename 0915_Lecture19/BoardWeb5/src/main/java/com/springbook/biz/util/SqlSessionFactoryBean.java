package com.springbook.biz.util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryBean {

	public static SqlSessionFactory sessionFactory = null;
	
	// 초기화 블록 (static을 초기화 할 수 있음)
	static {
		// sql 세션 객체를 받아오기 위한 프레임
		try {
			if (sessionFactory == null) {
				// reader를 이용하여 db 정보와 관련 매핑 값을 받아옴
				Reader reader = Resources.getResourceAsReader("sql-map-config.xml");
				// db정리를 빌더에 넣어서 sql session 객체를 생성
				sessionFactory = new SqlSessionFactoryBuilder().build(reader);
			}
		} catch (Exception e) {
			
		}
	}
	
	// 외부에서 방금 생성한 세션을 사용할 수 있도록 싱글턴 패턴으로 생성
	public static SqlSession getSqlSessionInstance() {
		return sessionFactory.openSession();
	}
}
