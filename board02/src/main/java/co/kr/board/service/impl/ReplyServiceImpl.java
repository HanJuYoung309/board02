package co.kr.board.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.kr.board.service.ReplyService;
import co.kr.board.service.mapper.ReplyMapper;
import co.kr.board.vo.BoardVO;

@Service
public class ReplyServiceImpl  implements ReplyService{
	
	@Autowired
	public ReplyMapper mapper;

	@Override
	public int insert(BoardVO boardVO) {
		// TODO Auto-generated method stub
		return mapper.insert(boardVO);
	}

}
