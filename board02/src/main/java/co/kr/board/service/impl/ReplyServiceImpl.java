package co.kr.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.kr.board.service.ReplyService;
import co.kr.board.service.mapper.ReplyMapper;
import co.kr.board.vo.BoardVO;
import co.kr.board.vo.ReplyVO;

@Service
public class ReplyServiceImpl  implements ReplyService{
	
	@Autowired
	public ReplyMapper mapper;

	@Override
	public int insert(ReplyVO replyvo) {
		// TODO Auto-generated method stub
		return mapper.insert(replyvo);
	}

	@Override
	public List<ReplyVO> list(int bno) {
		// TODO Auto-generated method stub
		return mapper.list(bno);
	}



	@Override
	public void reply_update(ReplyVO vo) {

       mapper.reply_update(vo);
		
	}

	@Override
	public void delete(int rno) {
		
		mapper.delete(rno);
		
	}

}
