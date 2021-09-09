//package com.springbook.view.board;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//import com.springbook.biz.board.BoardVO;
//import com.springbook.biz.board.impl.BoardDAO;
//
//@Controller
//@SessionAttributes("board")
//public class BoardController_old {
//	
//	@RequestMapping(value="/insertBoard.do")
//	public String insertBoard(BoardVO vo,BoardDAO boardDAO) {
//		System.out.println("글 등록 처리");
//		boardDAO.insertBoard(vo);
//		
//		return "redirect:getBoardList.do";
//	}
//
//	
//	@RequestMapping("/updateBoard.do")
//	public String updateBoard(BoardVO vo, BoardDAO boardDAO) {
//		//System.out.println("작성자 이름 : " + vo.getWriter());
//		boardDAO.updateBoard(vo);
//		return "getBoardList.do";
//	}
//	
////	@RequestMapping("/updateBoard.do")
////	public String updateBoard(@ModelAttribute("board") BoardVO vo, BoardDAO boardDAO) {
////		System.out.println("번호 : " + vo.getSeq());
////		System.out.println("제목 : " + vo.getTitle());
////		System.out.println("작성자 : " + vo.getWriter());
////		System.out.println("내용 : " + vo.getContent());
////		System.out.println("등록일 : " + vo.getRegDate());
////		System.out.println("조회수 : " + vo.getCnt());
////		
////		boardDAO.updateBoard(vo);
////		return "getBoardList.do";
////	}
//
//	
//	@RequestMapping("/deleteBoard.do")
//	public String deleteBoard(BoardVO vo, BoardDAO boardDAO) {
//		boardDAO.deleteBoard(vo);
//		return "getBoardList.do";
//	}
//
//	
////	@RequestMapping("/getBoard.do")
////	public ModelAndView getBoard(BoardVO vo, BoardDAO boardDAO, ModelAndView mav) {
////		mav.addObject("board", boardDAO.getBoard(vo)); // Model 정보 저장
////		mav.setViewName("getBoard.jsp"); // View 정보 저장
////		return mav;
////	}
//	
//	@RequestMapping("/getBoard.do")
//	public String getBoard(BoardVO vo, BoardDAO boardDAO, Model model) {
//		model.addAttribute("board",boardDAO.getBoard(vo));
//		return "getBoard.jsp";
//	}
//
//	
////	@RequestMapping("/getBoardList.do")
////	public ModelAndView getBoardList(BoardVO vo, BoardDAO boardDAO, ModelAndView mav) {
////		mav.addObject("boardList", boardDAO.getBoardList(vo)); // Model 정보 저장
////		mav.setViewName("getBoardList.jsp"); // View 정보 저장
////		return mav;
////	}	
//	
//	@ModelAttribute("conditionMap")
//	public Map<String,String> searchConditonMap(){
//		Map<String,String> conditionMap = new HashMap<String,String>();
//		conditionMap.put("제목", "TITLE");
//		conditionMap.put("내용", "CONTENT");
//		return conditionMap;
//	}
//	
//	
//	@RequestMapping("/getBoardList.do")
//	public String getBoardList(BoardVO vo, BoardDAO boardDAO, Model model) {
//		model.addAttribute("boardList", boardDAO.getBoardList(vo)); // Model 정보 저장
//		return "getBoardList.jsp";  //View 이름 리턴
//	}
//	
//	
////	@RequestMapping("/getBoardList.do")
////	public String getBoardList(
////			@RequestParam(value = "searchCondition", defaultValue = "TITLE", required = false) String condition,
////			@RequestParam(value = "searchKeyword", defaultValue = "", required = false) String keyword,
////			BoardDAO boardDAO, Model model) {
////		System.out.println("검색 조건 : " + condition);
////		System.out.println("검색 단어 : " + keyword);
////		//model.addAttribute("boardList", boardDAO.getBoardList(vo)); // Model 정보 저장
////		return "getBoardList.jsp";  //View 이름 리턴
////	}
//}
//
//
//
//
