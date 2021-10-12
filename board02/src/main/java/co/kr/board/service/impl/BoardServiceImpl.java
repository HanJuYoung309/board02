package co.kr.board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<BoardVO> boardList(int page,String keyword,String type) {
		// String -> 보내줄값의이름
		// Object-> 넣어줄값의이름
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		// 한페이지에서 10개의 게시물을 보여줄거라고 선언해준다
		int amount = 10;

		hashMap.put("startRn", (page - 1) * amount);
		hashMap.put("endRn", page * amount);
		hashMap.put("type", type);
		hashMap.put("keyword", keyword);

		return mapper.boardList(hashMap);
		
	}

	@Override
	public int total() {
		// TODO Auto-generated method stub
		return mapper.total();
	}

	@Override
	public int noticeTotal(String type, String keyword) {
		HashMap<String, Object> noticeTotal = new HashMap<String,Object>();
		noticeTotal.put("type", type);
		noticeTotal.put("keyword", keyword);

		return mapper.noticeTotal(noticeTotal);
	}

	@Override
	public BoardVO getBoard(int bnum) {
		// TODO Auto-generated method stub
		return mapper.getBoard(bnum);
	}

	@Override
	public int maxUpdate() {
		// TODO Auto-generated method stub
		return mapper.maxUpdate();
	}

	@Override
	public int insert(BoardVO boardVO) {
		// TODO Auto-generated method stub
		return mapper.insert(boardVO);
	}

	@Override
	public Map<String, Object> likecheck(Map<String, Object> idxMap) {
		// TODO Auto-generated method stub
		return mapper.likecheck(idxMap);
	}

	@Override
	public void insertLikeBtn(Map<String, Object> commandMap) {
		
		mapper.insertLikeBtn(commandMap);
		
	}

	@Override
	public void updateLikeCntPlus(Map<String, Object> commandMap) {


		mapper.updateLikeCntPlus(commandMap);
		
	}

	@Override
	public void updateLikeCheck(Map<String, Object> commandMap) {


		mapper.updateLikeCheck(commandMap);
		
	}

	@Override
	public void updateLikeCntMinus(Map<String, Object> commandMap) {


		mapper.updateLikeCntMinus(commandMap);
		
	}

	@Override
	public Map<String, Object> getLikeCnt(Map<String, Object> commandMap) {
		// TODO Auto-generated method stub
		return mapper.getLikeCnt(commandMap);
	}

	
}
