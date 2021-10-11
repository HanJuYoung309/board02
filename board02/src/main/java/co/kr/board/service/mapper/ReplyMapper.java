package co.kr.board.service.mapper;

import org.apache.ibatis.annotations.Mapper;

import co.kr.board.vo.BoardVO;

@Mapper
public interface ReplyMapper {

	int insert(BoardVO boardVO);

}
