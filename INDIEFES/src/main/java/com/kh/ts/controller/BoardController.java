package com.kh.ts.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.imgscalr.Scalr.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.ks.domain.UserInfoVo;
import com.kh.ks.service.IUserInfoService;
import com.kh.ts.domain.BoardVo;
import com.kh.ts.domain.PaginationDto;
import com.kh.ts.domain.PagingDto;
import com.kh.ts.service.IBoardService;

@Controller
@RequestMapping(value="/board/*")
public class BoardController {

	@Inject
	IBoardService boardService;
	
	@Inject
	private IUserInfoService userInfoService;
	
	int bno;
	
	// 글목록
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void boardList(Model model, PagingDto pagingDto) throws Exception {
		System.out.println("BoardController, list, pagingDto:" + pagingDto);
		List<BoardVo> list = boardService.selectAll(pagingDto);
		System.out.println("boardList");
		model.addAttribute("list", list);
		PaginationDto paginationDto = new PaginationDto();
		
		paginationDto.setPagingDto(pagingDto);
		int count = boardService.listCount(pagingDto);
		System.out.println("count:" + count);
		paginationDto.setTotalCount(count);
		model.addAttribute("paginationDto", paginationDto);
	}
	
	// 글조회
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public void read(@RequestParam("board_number")int board_number, Model model, 
			HttpSession session, PagingDto pagingDto)throws Exception {
		System.out.println("BoardController, read, board_number:" + board_number);
		BoardVo boardVo = boardService.select(board_number);
		// 회원아이디
		String user_id = boardVo.getUser_id();
		UserInfoVo userInfoVo = userInfoService.readWith(user_id);
//		System.out.println("userInfoVo:" + userInfoVo);
		String login_id = userInfoVo.getUser_id();
		// 지정된회원
		System.out.println("user_id:" + user_id);
		System.out.println("login_id:" + login_id);
		if (! user_id.equals(login_id)) {
			System.out.println("update");
			
		}
		
		// int bno 전역변수를 하나 정해서 board_number를 저장
		// 현재 호출된 board_number와 같으면 updateViewcnt를 실행하지 않게 함
		if (bno != board_number) {
				boardService.updateViewcnt(board_number);
			bno = board_number;
		}
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("pagingDto", pagingDto);
	}
	
	// 글쓰기 폼 - /indiefes/board/regist(GET 불러오기)
	@RequestMapping(value="/regist", method=RequestMethod.GET)
	public void registGet() throws Exception {
		System.out.println("registGet()");
		// /WEB-INF/indifes/board/regist.jsp
	}
	
	// 글쓰기 처리 - /indiefes/board/regist(Post 불러오기)
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public String registPost(BoardVo boardVo, RedirectAttributes rttr, HttpSession session)throws Exception {
		System.out.println("boardVo:" + boardVo);
		System.out.println("BoardController, registPost, boardVo:" + boardVo);
		boardService.insert(boardVo);
		rttr.addFlashAttribute("message","success_insert");
		
		return "redirect:/board/list";
	}
	
	// 글수정 폼 - /indiefes/board/update(GET 불러오기)
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public void updateGet(@RequestParam("board_number")int board_number, Model model,
			@ModelAttribute PagingDto pagingDto)throws Exception {
		BoardVo boardVo = boardService.select(board_number);
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("pagingDto", pagingDto);
	}
	
	// 글수정처리 - /indiefes/board/update(Post 불러오기)
	@RequestMapping(value="/update", method= RequestMethod.POST)
	public String updatePost(BoardVo boardVo, RedirectAttributes rttr,@RequestParam(value="pagingDto", required=false) 
		PagingDto pagingDto)throws Exception {
		boardService.update(boardVo);
		rttr.addFlashAttribute("message", "success_update");
		rttr.addAttribute("pagingDto", pagingDto);
		
		return "redirect:/board/read?board_number=" + boardVo.getBoard_number();
	}
	// 글삭제 폼 - /indiefes/board/update(GET 처리)
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public void deleteGet(int board_number, Model model)throws Exception {
		
	}
	// 글삭제처리- /indiefes/board/delete(Post 처리)
	@RequestMapping(value="/delete-run", method=RequestMethod.POST)
	public String deletePost(@RequestParam("board_number")int board_number,
			RedirectAttributes rttr)throws Exception {
		try {
			boardService.delete(board_number);
			rttr.addFlashAttribute("message", "success_delete");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/board/list?board_number=" + board_number;
	}
	// 첨부파일목록
	@RequestMapping(value="/getAttach/{board_number}")
	@ResponseBody
	public List<String> getAttach(@PathVariable("board_number")int board_number)throws Exception {
		List<String> list = boardService.getAttach(board_number);
		System.out.println("BoardController, getAttach, list:" + list);
		return list;
	}
}