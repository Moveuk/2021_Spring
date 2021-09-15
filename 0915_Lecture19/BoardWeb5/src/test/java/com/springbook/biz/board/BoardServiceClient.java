package com.springbook.biz.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.springbook.biz.board.impl.BoardDAO;

public class BoardServiceClient {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BoardDAO boardDAO = new BoardDAO();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		BoardVO bVo = null;
		int num = 0;

		while (true) {
			List<BoardVO> boardList = null;
			System.out.println();
			System.out.println("1.글추가 2.전체글조회 3.특정글조회 4.글정보수정 5.글삭제 6.프로그램종료");
			System.out.print("메뉴 선택 >> ");
			num = Integer.parseInt(br.readLine());

			switch (num) {
			case 1:
				bVo = new BoardVO();
				System.out.println("등록할 글 정보를 입력하세요.");
				System.out.print("글제목 : ");
				bVo.setTitle(br.readLine());
				System.out.print("작성자 : ");
				bVo.setWriter(br.readLine());
				System.out.print("글내용 : ");
				bVo.setContent(br.readLine());
				boardDAO.insertBoard(bVo);
				break;

			case 2:
				bVo = new BoardVO();
				bVo.setSearchCondition("TITLE");
				bVo.setSearchKeyword("");
				boardList = boardDAO.getBoardList(bVo);

				for (BoardVO board : boardList) {
					System.out.println(board.toString());
				}
				break;

			case 3:
				bVo = new BoardVO();
				System.out.print("조회 글 번호 입력 : ");
				bVo.setSeq(Integer.parseInt(br.readLine()));
				BoardVO boardVO = boardDAO.getBoard(bVo);
				System.out.print("--->" + boardVO.toString());
				System.out.print("\n");
				break;

			case 4:
				bVo = new BoardVO();
				System.out.print("수정 글 번호 입력 : ");
				bVo.setSeq(Integer.parseInt(br.readLine()));
				System.out.print("글제목 : ");
				bVo.setTitle(br.readLine());
				System.out.print("글내용 : ");
				bVo.setContent(br.readLine());
				boardDAO.updateBoard(bVo);

				bVo.setSearchCondition("TITLE");
				bVo.setSearchKeyword("");
				boardList = boardDAO.getBoardList(bVo);
				for (BoardVO boardVO2 : boardList) {
					System.out.print("--->" + boardVO2.toString() + "\n");
				}
				break;

			case 5:
				bVo = new BoardVO();
				System.out.print("삭제 글 번호 입력 : ");
				bVo.setSeq(Integer.parseInt(br.readLine()));
				boardDAO.deleteBoard(bVo);
				
				bVo.setSearchCondition("TITLE");
				bVo.setSearchKeyword("");
				boardList = (ArrayList<BoardVO>) boardDAO.getBoardList(bVo);
				for (BoardVO boardVO3 : boardList) {
					System.out.print("--->" + boardVO3.toString() + "\n");
				}
				break;

			case 6:
				System.out.print("6번 선택\n\n");
				System.out.print("프로그램종료");
				return;

			default:
				System.out.println("숫자 1~6 사이값만 입력해주세요.");
				break;
			}
		}
	}
}
