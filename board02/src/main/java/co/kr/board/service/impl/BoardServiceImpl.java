package co.kr.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.kr.board.service.BoardService;
import co.kr.board.service.mapper.BoardMapper;
import co.kr.board.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	public BoardMapper mapper;

	@Override
	public List<BoardVO> boardList() {
		// TODO Auto-generated method stub
		return mapper.boardList();
	}
}
