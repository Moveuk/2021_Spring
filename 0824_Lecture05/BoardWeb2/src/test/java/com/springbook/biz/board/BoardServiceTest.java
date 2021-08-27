package com.springbook.biz.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

// 테스트용 클래스
public class BoardServiceTest {

	public static void main(String[] args) throws IOException {
		System.out.println("Test 작동");
		
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		BoardService boardService = (BoardService) factory.getBean("boardService");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BoardVO bVo = new BoardVO();
		
		System.out.println("1.삽입 2.수정 3.삭제 4.정보보기");
		String key = br.readLine();
		
		switch (key) {
		case "1":
			// Insert
//			System.out.println("번호를 입력해 주세요.");
//			bVo.setSeq(Integer.parseInt(br.readLine()));
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
			break;
			
		case "2":
			// Update
			System.out.println("수정할 번호를 입력해 주세요.");
			bVo.setSeq(Integer.parseInt(br.readLine()));
			System.out.println("수정할 제목을 입력해 주세요.");
			bVo.setTitle(br.readLine());
			System.out.println("수정할 내용을 입력해 주세요.");
			bVo.setContent(br.readLine());
			
			if (bVo.getSeq() == 0) {
				System.out.println("수정할 번호가 없으면 안됩니다.");
			} else {
				boardService.updateBoard(bVo);
			}
			break;
			
		case "3":
			// Delete
			System.out.println("삭제할 번호를 입력해 주세요.");
			bVo.setSeq(Integer.parseInt(br.readLine()));
			
			if (bVo.getSeq() == 0) {
				System.out.println("삭제할 번호가 없으면 안됩니다.");
			} else {
				boardService.deleteBoard(bVo);
			}
			
			break;
			
		case "4":
			// Delete
			System.out.println("확인할 번호를 입력해 주세요.");
			bVo.setSeq(Integer.parseInt(br.readLine()));
			
			if (bVo.getSeq() == 0) {
				System.out.println("삭제할 번호가 없으면 안됩니다.");
			} else {
				boardService.getBoard(bVo);
			}
			
			break;	
			
		default:
			break;
		}
		
		
		// List 출력
		List<BoardVO> boardList = boardService.getBoardList(bVo);
		for (BoardVO board : boardList) {
			System.out.println("--->" + board.toString());
		}
		
		factory.close();
	}

}
