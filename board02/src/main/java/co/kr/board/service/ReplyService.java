package co.kr.board.service;

import java.util.List;

import co.kr.board.vo.BoardVO;
import co.kr.board.vo.ReplyVO;

public interface ReplyService {

	int insert(BoardVO boardVO);

	List<ReplyVO> list(int bno);

	void create(ReplyVO vo);

	void reply_update(ReplyVO vo);

	void delete(int rno);

}
