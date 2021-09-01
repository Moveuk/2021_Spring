package com.springbook.view.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

import oracle.net.aso.l;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;
	
	// 서블릿 생명주기중 맨처음 서블릿 생성될 때 실행되는 메소드
	@Override
	public void init() throws ServletException {
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		// 현재 폴더
		viewResolver.setPrefix("./");
		// .jsp 확장자 사용
		viewResolver.setSuffix(".jsp");
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));

		System.out.println(path);

		Controller ctrl = handlerMapping.getController(path);
		
		String viewName = ctrl.handlerRequest(request, response);
		
		String view = null;
		// .do가 포함되는지에 따라 처리방식 분기
		if(!viewName.contains(".do")) {
			view = viewResolver.getView(viewName);
		} else {
			view = viewName;
		}
		
		response.sendRedirect(view);
		
		
//		if (path.equals("/login.do")) {
//			System.out.println("로그인처리");
//
//			String id = request.getParameter("id");
//			String password = request.getParameter("password");
//
//			UserDAO userDAO = new UserDAO();
//			UserVO userVO = new UserVO();
//			userVO.setId(id);
//			userVO.setPassword(password);
//			UserVO user = userDAO.getUser(userVO);
//
//			if (user != null) {
//				response.sendRedirect("getBoardList.do");
//			} else {
//				response.sendRedirect("login.jsp");
//			}
//
//		} else if (path.equals("/getBoardList.do")) {
//			System.out.println("글 목록 검색 처리");
//
//			BoardVO vo = new BoardVO();
//			BoardDAO boardDAO = new BoardDAO();
//			List<BoardVO> boardList = boardDAO.getBoardList(vo);
//
//			HttpSession session = request.getSession();
//			session.setAttribute("boardList", boardList);
//
//			response.sendRedirect("getBoardList.jsp");
//		} else if (path.equals("/getBoard.do")) {
//			System.out.println("글 상세 처리");
//
//			String seq = request.getParameter("seq");
//			BoardVO bVo = new BoardVO();
//			bVo.setSeq(Integer.parseInt(seq));
//
//			BoardDAO bDao = new BoardDAO();
//			BoardVO board = bDao.getBoard(bVo);
//
//			HttpSession session = request.getSession();
//			session.setAttribute("bVo", board);
//			response.sendRedirect("getBoard.jsp");
//		} else if (path.equals("/insertBoard.do")) {
//			System.out.println("글 삽입 처리");
//
//			request.setCharacterEncoding("UTF-8");
//			BoardVO bVo = new BoardVO();
//			bVo.setTitle(request.getParameter("title"));
//			bVo.setWriter(request.getParameter("writer"));
//			bVo.setContent(request.getParameter("content"));
//
//			BoardDAO bDao = new BoardDAO();
//			bDao.insertBoard(bVo);
//			response.sendRedirect("getBoardList.do");
//		} else if (path.equals("/updateBoard.do")) {
//			System.out.println("글 수정 처리");
//
//			request.setCharacterEncoding("UTF-8");
//			BoardVO bVo = new BoardVO();
//			bVo.setSeq(Integer.parseInt(request.getParameter("seq")));
//			bVo.setTitle(request.getParameter("title"));
//			bVo.setContent(request.getParameter("content"));
//
//			BoardDAO bDao = new BoardDAO();
//			bDao.updateBoard(bVo);
//			response.sendRedirect("getBoardList.do");
//		} else if (path.equals("/deleteBoard.do")) {
//			System.out.println("글 삭제 처리");
//			String seq = request.getParameter("seq");
//			BoardVO bVo = new BoardVO();
//			bVo.setSeq(Integer.parseInt(seq));
//
//			BoardDAO bDao = new BoardDAO();
//			bDao.deleteBoard(bVo);
//			response.sendRedirect("getBoardList.do");
//			
//		} else if (path.equals("/logout.do")) {
//			System.out.println("로그아웃 처리");
//
//			HttpSession session = request.getSession();
//			session.invalidate();
//			response.sendRedirect("login.jsp");
//		}

	}

}
