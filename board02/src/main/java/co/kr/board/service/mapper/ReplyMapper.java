package co.kr.board.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import co.kr.board.vo.BoardVO;
import co.kr.board.vo.ReplyVO;

@Mapper
public interface ReplyMapper {

	int insert(BoardVO boardVO);

	List<ReplyVO> list(int bno);

	void create(ReplyVO vo);

	void reply_update(ReplyVO vo);

	void delete(int rno);

}
