package com.springbook.biz.board;

import java.util.List;

public interface BoardService {
	void insertBoard(BoardVO bVo);
	void updateBoard(BoardVO bVo);
	void deleteBoard(BoardVO bVo);
	BoardVO getBoard(BoardVO bVo);
	List<BoardVO> getBoardList(BoardVO bVo);
}
