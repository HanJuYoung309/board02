package co.kr.board.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import co.kr.board.vo.BoardVO;

@Mapper
public interface BoardMapper {
   ////////
	List<BoardVO> boardList();

}
