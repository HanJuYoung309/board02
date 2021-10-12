package co.kr.board.service;

import java.util.List;
import java.util.Map;

import co.kr.board.vo.BoardVO;

public interface BoardService {

	List<BoardVO> boardList(int page, String keyword, String type);

	int total();
	
	int noticeTotal(String type, String keyword);

	BoardVO getBoard(int bnum);

	int maxUpdate();

	int insert(BoardVO boardVO);

	Map<String, Object> likecheck(Map<String, Object> idxMap);

	void insertLikeBtn(Map<String, Object> commandMap);

	void updateLikeCntPlus(Map<String, Object> commandMap);

	void updateLikeCheck(Map<String, Object> commandMap);

	void updateLikeCntMinus(Map<String, Object> commandMap);

	Map<String, Object> getLikeCnt(Map<String, Object> commandMap);
	

}
