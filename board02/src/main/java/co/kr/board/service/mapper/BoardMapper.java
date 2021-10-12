package co.kr.board.service.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import co.kr.board.vo.BoardVO;

@Mapper
public interface BoardMapper {

	List<BoardVO> boardList(HashMap<String, Object> hashMap);

	int total();

	//int noticeTotal(String type, String keyword);

	int noticeTotal(HashMap<String, Object> noticeTotal);

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
