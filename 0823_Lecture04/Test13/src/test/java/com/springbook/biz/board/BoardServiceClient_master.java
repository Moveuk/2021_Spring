package com.springbook.biz.board;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient_master {

	public static void main(String[] args) {
		// TODO 자동 생성된 메소드 스텁
		AbstractApplicationContext factory = 
				new GenericXmlApplicationContext("applicationContext.xml");
		
		BoardService boardService = (BoardService)factory.getBean("boardService");
		
		Scanner sc = new Scanner(System.in);
		int menu = 0;
		
		String title = "";
		String writer = "";
		String content = "";
		int seq = 0;
		
		BoardVO vo = null;
		
		while(true) {
			System.out.println("1.글추가 2.전체글조회 3.특정글조회 4.글정보수정 5.글삭제 6.프로그램종료");
			
			System.out.print("메뉴선택 >> ");
			
			menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				System.out.println("등록할 글 정보를 입력하세요.");
				System.out.print("글제목 : ");
				title = sc.nextLine();
				
				System.out.print("작성자 : ");
				writer = sc.nextLine();
				
				System.out.print("글내용 : ");
				content = sc.nextLine();
				
				vo = new BoardVO();
				vo.setTitle(title);
				vo.setWriter(writer);
				vo.setContent(content);
				
				boardService.insertBoard(vo);
				System.out.println();
				break;
			case 2:
				List<BoardVO> boardList = boardService.getBoardList(vo);
				for(BoardVO board : boardList) {
					System.out.println("--->"+board.toString());
				}
				factory.close();
				System.out.println();
				break;
				
			case 3:
				vo = new BoardVO();
				
				System.out.print("조회 글의 번호 입력 : ");
				seq = sc.nextInt();
				vo.setSeq(seq);
				BoardVO board = boardService.getBoard(vo);
				System.out.println("--->"+board.toString());
				System.out.println();
				
				break;
			
			case 4:
				System.out.print("수정할 글의 번호 입력 : ");
				seq = sc.nextInt();
				sc.nextLine();
				
				System.out.print("글제목 : ");
				title = sc.nextLine();
				
				System.out.print("글내용 : ");
				content = sc.nextLine();
				
				vo = new BoardVO();
				vo.setSeq(seq);
				vo.setTitle(title);
				vo.setContent(content);
				
				boardService.updateBoard(vo);
				List<BoardVO> boardListUpdate = boardService.getBoardList(vo);
				for(BoardVO boardUpdate : boardListUpdate) {
					System.out.println("--->"+boardUpdate.toString());
				}
				
				System.out.println();
				break;
				
			case 5:
				System.out.println("삭제 글의 번호 입력");
				seq = sc.nextInt();
				
				vo = new BoardVO();
				vo.setSeq(seq);
				
				boardService.deleteBoard(vo);
				List<BoardVO> boardListDelete = boardService.getBoardList(vo);
				for(BoardVO boardDelete : boardListDelete) {
					System.out.println("--->"+boardDelete.toString());
				}
				System.out.println();
				break;
			}	
			if(menu == 6) {
				System.out.println("6번 선택");
				System.out.println();
				System.out.println();
				System.out.println("프로그램종료");
				break;
			}
		}
	
	}

}
