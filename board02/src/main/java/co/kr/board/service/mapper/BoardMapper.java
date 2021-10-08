package co.kr.board.service.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import co.kr.board.vo.BoardVO;

@Mapper
public interface BoardMapper {

	List<BoardVO> boardList(HashMap<String, Object> hashMap);

	int total();

	//int noticeTotal(String type, String keyword);

	int noticeTotal(HashMap<String, Object> noticeTotal);

}
