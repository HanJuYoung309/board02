package co.kr.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import co.kr.board.service.ReplyService;
import co.kr.board.vo.BoardVO;
import co.kr.board.vo.ReplyVO;

@Controller
public class ReplyController {

	
	@Autowired
	public ReplyService service;
	
	   private static final Logger logger= LoggerFactory.getLogger(ReplyController.class);
	
	    //댓글 리스트 
	    @RequestMapping("reply_list.do")
	    public ModelAndView list(int bno, ModelAndView mav, ReplyVO vo) {
	        List<ReplyVO> list = service.list(bno);
	 
	        System.out.println("뷰에 전달할 데이터"+list);
	        Map<String,Object> map = new HashMap<>();    //리스트의 값을 저장하기 위해 map객체를 생성하고 그 안에 리스트를 저장
	        
	        map.put("list", list);
	        logger.info("뷰에 전달할 데이터"+map);
	        mav.addObject("map", map);    //뷰에 전달할 데이터 저장
	        
	        mav.setViewName("board/memberboardreply_list");    //뷰의 이름
	        
	        return mav;
	    }
	    //댓글 목록을 ArrayList로 리턴함
	    @RequestMapping("/reply_list_json.do")
	    public List<ReplyVO> list_json(int bno){
	        
	        return service.list(bno);
	        
	    }
	
	    
}
