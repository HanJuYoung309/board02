package co.kr.board;

import java.text.SimpleDateFormat;
import java.util.Date;
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

	@Autowired
	public BoardService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home()
	{
		System.out.println("홈 이동..");
		return "home";
	}
	
	@RequestMapping(value = "/list2.do", method = RequestMethod.GET)
	public String boardListForm()
	{
		System.out.println("List PAGE...");
		return "board/list";
	}
	
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView boardList(int page, String keyword,String type)
	{
		ModelAndView json= new ModelAndView("jsonView");
		
		List<BoardVO> list= service.boardList(page,keyword,type);
		SimpleDateFormat simple = new SimpleDateFormat("yyyy년 MM월 dd일");
		Date date = new Date();

		for (int i = 0; i < list.size(); i++) {
			list.get(i).setBdate(simple.format(date));
		}
		
		
		System.out.println("홈 이동..");
		
		// 소수점까지 나오기위해 1.0을곱한다
		// 끝페이지
		int endPage = (int) (Math.ceil(page * 1.0 / 5) * 5);

		// 처음
		int startPage = endPage - 4;

		if (startPage <= 0) {
			startPage = 1;
		}

		// 총 게시글 갯수
		int total = service.noticeTotal(type, keyword);

		// 전체페이지
		int totalPage = (int) Math.ceil(total * 1.0 / 10);

		if (endPage > totalPage) {
			endPage = totalPage;
		}
		// 이전
		boolean prev = page > 1;
		// 다음
		boolean next = page < endPage;

		json.addObject("list", list);
		json.addObject("endPage", endPage);
		json.addObject("startPage", startPage);
		json.addObject("prev", prev);
		json.addObject("next", next);
			
		return json;
	}
	

}
