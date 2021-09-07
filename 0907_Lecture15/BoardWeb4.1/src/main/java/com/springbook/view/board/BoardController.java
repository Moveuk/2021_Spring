package com.springbook.view.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
@SessionAttributes("board")
public class BoardController {

	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardVO bVo, BoardDAO boardDAO) { // view에 대한 처리만 필요.
		System.out.println("UpdateBoardController 실행");
		System.out.println("작성자 이름 : "+bVo.getWriter());
		boardDAO.updateBoard(bVo);

		return "getBoardList.do";
	}

	@RequestMapping(value = "/insertBoard.do")
	public String insertBoard(BoardVO bVo, BoardDAO boardDAO) {

		System.out.println("InsertBoardControoler 실행");

		boardDAO.insertBoard(bVo);

		return "redirect:getBoardList.do";

	}

	// 모델 객체를 사용하여 뷰 분리하기
//	@RequestMapping("/getBoard.do")
//	public ModelAndView getBoard(BoardVO bVo, BoardDAO boardDAO,ModelAndView mav) {
//		System.out.println("GetBoardController 실행");
//		
//		mav.addObject("board",boardDAO.getBoard(bVo));
//		mav.setViewName("getBoard.jsp");
//		
//		return mav;
//	}


	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO bVo, BoardDAO boardDAO, Model model) {
		System.out.println("GetBoardController 실행");

		model.addAttribute("board", boardDAO.getBoard(bVo));

		return "getBoard.jsp";
	}

	// 모델과 뷰 버전
//	@RequestMapping("/getBoardList.do")
//	public ModelAndView getBoardList(BoardVO bVo,BoardDAO boardDAO,ModelAndView mav){
//		System.out.println("GetBoardListController 실행");
//		
//		List<BoardVO> boardList = boardDAO.getBoardList(bVo);
//		mav.addObject("boardList",boardList);
//		mav.setViewName("getBoardList.jsp");
//		
//		return mav;
//	}	
	
	// 우리가 호출하는게 아니고 생명주기 내에서 컨트롤러 사용전 스캔하여 @ModelAttribute들을 모두 생성해둔다.
	
	@ModelAttribute("conditionMap")	// 이름은 원하는대로 작성 가능하다. 이름은 뷰(jsp)에서 사용한다.
	public Map<String,String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		
		return conditionMap;
	}
	
	// 모델과 뷰 분리 버전
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO bVo, BoardDAO boardDAO, Model model) {
		System.out.println("GetBoardListController 실행");

		model.addAttribute("boardList", boardDAO.getBoardList(bVo));
		
		return "getBoardList.jsp";	// 뷰 이름 리턴
	}

	// 검색 기능 구현 추가 버전
//	@RequestMapping("/getBoardList.do")
//	public String getBoardList(@RequestParam(value = "searchCondition",defaultValue = "TITLE", required = false)String condition,@RequestParam(value = "searchKeyword", defaultValue = "", required = false) String Keyword, BoardVO bVo, BoardDAO boardDAO, Model model) {
//		System.out.println("GetBoardListController 실행");
//		System.out.println("검색 조건 : " + condition);
//		System.out.println("검색 단어 : " + Keyword);
//		
//		model.addAttribute("boardList", boardDAO.getBoardList(bVo));
//		
//		return "getBoardList.jsp";
//	}
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO bVo, BoardDAO boardDAO) {
		System.out.println("DeleteBoardController 실행");

		boardDAO.deleteBoard(bVo);

		return "redirect:getBoardList.do";
	}
}
