package co.kr.board;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.kr.board.service.BoardService;
import co.kr.board.service.ReplyService;
import co.kr.board.vo.BoardVO;
import co.kr.board.vo.ReplyVO;

@Controller
public class BoardController {

	@Autowired
	public BoardService service;
	
	@Autowired
	public ReplyService replyService;
	
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
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.GET)
	public String insertForm(Model model)
	{
		System.out.println("글 추가페이지이동");
		int max= service.maxUpdate();
		model.addAttribute("max",max+1);

		return "board/insert";
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public ModelAndView insert(Model model,BoardVO boardVO)
	{
		ModelAndView json= new ModelAndView("jsonView");
		
		service.insert(boardVO);


		return json;
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
	
	
	@RequestMapping(value = "/getBoard.do", method = RequestMethod.GET)
	public String getBoard(int bnum,Model model,HttpServletRequest request,HttpServletResponse
			response, HttpSession session ) {
        BoardVO boardVO = service.getBoard(bnum);
		
	    model.addAttribute("board",boardVO);
	    
//		Map<String,Object> resultMap = null;
//	    Map<String,Object> idxMap = new HashMap<>();
//	    int bbsidx = bnum;
//	    int useridx = 0;
//	
//	   
//	    try {
//	        Map<String,Object> userInfo = (Map<String, Object>) session.getAttribute("userInfo");
//	
//	        resultMap = (Map<String, Object>) service.getBoard(bbsidx); // 게시판 상세정보 가져옴
//	        idxMap.put("bbsidx", bbsidx);
//	        idxMap.put("useridx", useridx);
//	 
//	        Map<String,Object> likecheckMap = service.likecheck(idxMap);
//	        //like 테이블 에서 사용자가 해당 게시글에 대해서 좋아요를 눌렀는지 확인
//	        if(likecheckMap == null) {
//	        //사용자가 좋아요를 한번도 누른적이 없으면 
//	       //like테이블에 데이터가 없으므로 null반환
//	            model.addAttribute("likecheck",0);
//	        }
//	        else {
//	            model.addAttribute("likecheck",likecheckMap.get("likecheck"));
//	        }
//	 
//	        model.addAttribute("likecnt",resultMap.get("likecnt"));
//	        model.addAttribute("bbsidx",bbsidx);
//	        model.addAttribute("useridx",useridx);
//	                /* 생략 */
//	    } catch (Exception e) {
//	       System.out.println(e.getMessage());
//	    }
		
		
		return "board/getBoard";
		
	}
	
	/* Controller */
    //좋아요 눌렀을때
    @RequestMapping("/clickLike")
    @ResponseBody
    public Map<String,Object> clickLike(@RequestParam Map<String,Object> commandMap){
      
        System.out.println("request: /clickLike");
        int resultCode = 1;
        int likecheck = 1;
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> likecntMap = new HashMap<>();
        Map<String,Object> resultMap = new HashMap<>();
        try {
            map = service.likecheck(commandMap);
            if(map == null) {
                //처음 좋아요 누른것 likecheck=1, 좋아요 빨간색이 되야됨
                service.insertLikeBtn(commandMap); //좋아요 테이블 인서트
                service.updateLikeCntPlus(commandMap); //bbs 테이블 업데이트 +1
                resultCode = 1;
            }
            else if(Integer.parseInt(map.get("likecheck").toString()) == 0) {
                //좋아요 처음은 아니고 취소했다가 다시 눌렀을때 likecheck=1, 좋아요 빨간색 되야됨
                commandMap.put("likecheck",likecheck);
                service.updateLikeCheck(commandMap); //좋아요 테이블 업데이트
                service.updateLikeCntPlus(commandMap); // bbs테이블 +1
                resultCode = 1;
            }
            else {
                //좋아요 취소한거 likecheck=0, 빈하트 되야됨
                likecheck = 0;
                commandMap.put("likecheck", likecheck);
                service.updateLikeCheck(commandMap);
                service.updateLikeCntMinus(commandMap);
                resultCode = 0;
            }
            resultMap = service.getLikeCnt(commandMap); 
            // 해당 게시글 테이블의 likecnt칼럼  update가 끝난후 likecnt값 가져옴
            resultMap.put("likecheck", likecheck);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            resultCode = -1;
        }
        
        resultMap.put("resultCode", resultCode);
        //resultCode가 1이면 빨간하트 0이면 빈하트
        return resultMap;
    }

	
    //댓글 생성
    @RequestMapping(value="reply_insert.do", method = RequestMethod.POST)    //세부적인 url pattern
    public void insert (ReplyVO vo, HttpSession session,
             @RequestParam(value="replytext") String replytext,
             @RequestParam(value="bno") int bno ,Model model) {
    	
    	System.out.println("댓글생성 컨트롤러...");
    	
    	model.addAttribute("vo",vo);  
        vo.setReplytext(replytext);
        vo.setBno(bno);
        System.out.println(vo);
        //댓글이 테이블에 저장된다
        replyService.create(vo);
        
    }
    //댓글 수정
    @RequestMapping("/board/reply_update.do")    //세부적인 url pattern
    public String reply_update (@RequestParam(value="rno") int rno, 
    		@RequestParam(value="replytext") String replytext
    		,@RequestParam(value="bno")int bno, ReplyVO vo) throws Exception{
        
    	BoardVO boardvo= new BoardVO();
         vo.setRno(rno);
         vo.setReplytext(replytext);
       
        System.out.println("dto에 있는값들 출력함"+vo);
 
        replyService.reply_update(vo);
        
        
        return "redirect:getBoard.do?bnum="+boardvo.getBnum();
    }
    
    
    //댓글 삭제
    @RequestMapping(value = "/board/reply_delete.do" , method = {RequestMethod.GET, RequestMethod.POST} )    //세부적인 url pattern
    public String reply_delete (@RequestParam(value="rno") int rno,
    		ReplyVO vo, @RequestParam(value="bno") int bno
    		,BoardVO boardvo) throws Exception{
 
 
    	replyService.delete(rno);
       
        return "redirect:getBoard.do?bnum="+boardvo.getBnum();
        
    }
}
