package co.kr.board.service;

import java.util.List;

import co.kr.board.vo.BoardVO;

public interface BoardService {

	List<BoardVO> boardList(int page, String keyword, String type);

	int total();
	
	int noticeTotal(String type, String keyword);

	BoardVO getBoard(int bnum);

	int maxUpdate();

	int insert(BoardVO boardVO);
	

}
