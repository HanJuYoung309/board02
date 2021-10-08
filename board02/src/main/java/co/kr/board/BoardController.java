package co.kr.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.kr.board.service.BoardService;
import co.kr.board.vo.BoardVO;

@Controller
public class BoardController {
	
	//첫번째커밋
	@Autowired
	public BoardService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home()
	{
		System.out.println("홈 이동..");
		return "home";
	}
	
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView boardList()
	{
		ModelAndView json= new ModelAndView("jsonView");
		
		List<BoardVO> list= service.boardList();
		
		System.out.println("홈 이동..");
		////////
		
		
		return json;
	}
	

}
