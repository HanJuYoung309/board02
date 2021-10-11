package co.kr.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.kr.board.service.ReplyService;
import co.kr.board.vo.BoardVO;
import co.kr.board.vo.ReplyVO;

@RestController
public class ReplyController {

	
	@Autowired
	public ReplyService service;
	
	
	@RequestMapping(value = "/replyInsert.do", method = RequestMethod.POST)
	public String insert(ReplyVO replyVO, BoardVO boardVO) {
		
		
			service.insert(boardVO);
		
		return "redirect:getBoard.do?bnum="+boardVO.getBnum();
		
		
	}
}
