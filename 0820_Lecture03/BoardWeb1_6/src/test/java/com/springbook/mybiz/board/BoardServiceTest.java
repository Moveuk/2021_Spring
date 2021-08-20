package com.springbook.mybiz.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

// 테스트용 클래스
public class BoardServiceTest {

	public static void main(String[] args) throws IOException {
		System.out.println("Test 작동");
		
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		BoardService boardService = (BoardService) factory.getBean("boardService");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BoardVO bVo = new BoardVO();
		
		System.out.println("제목을 입력해 주세요.");
		bVo.setTitle(br.readLine());
		System.out.println("글쓴이를 입력해 주세요.");
		bVo.setWriter(br.readLine());
		System.out.println("내용을 입력해 주세요.");
		bVo.setContent(br.readLine());

		if (bVo.getTitle().isBlank()) bVo.setTitle("빈값이라 새 제목");
		if (bVo.getWriter().isBlank()) bVo.setWriter("미상");
		if (bVo.getContent().isBlank()) bVo.setContent("빈 내용");
		
		boardService.insertBoard(bVo);
		factory.close();
	}

}
