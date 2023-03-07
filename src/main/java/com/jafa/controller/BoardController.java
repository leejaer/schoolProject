package com.jafa.controller;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jafa.domain.ApprovalVO;
import com.jafa.domain.AttachVO;
import com.jafa.domain.AttendVO;
import com.jafa.domain.Criteria;
import com.jafa.domain.ExpendDTO;
import com.jafa.domain.ExpendVO;
import com.jafa.domain.MemberDetail;
import com.jafa.domain.MemberVO;
import com.jafa.domain.ModifySnoS;
import com.jafa.domain.Pagination;
import com.jafa.domain.PeopleVO;
import com.jafa.domain.Reply_boardVO;
import com.jafa.domain.SchoolVO;
import com.jafa.domain.TestVO;
import com.jafa.exception.monthException;
import com.jafa.service.ApprovalService;
import com.jafa.service.AttachService;
import com.jafa.service.AttendService;
import com.jafa.service.BoardService;
import com.jafa.service.MemberService;
import com.jafa.service.PeopleService;
import com.jafa.service.TestService;

import lombok.extern.log4j.Log4j;
import oracle.jdbc.proxy.annotation.Pre;

@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {
	
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	AttachService attachService; 
	
	@Autowired
	PeopleService peopleService;
	
	@Autowired
	ApprovalService approvalService;
	
	@Autowired
	AttendService attendService;
	
	@Autowired
	TestService testService;
	
	@Autowired
	MemberService memberService;
	
	
	//카멜 sno를 카멜케이스 사용 오류 모든 링크를 하나로 할려고 했는데 세큐리티 적용 때문에 분리함
	@GetMapping(value = {"/school/{kind}/{pageName}","/school/{kind}/{pageName}/{month}"})
	public String school(Model model,@PathVariable String kind,
			@PathVariable(required = false) String pageName,
			@PathVariable(required = false) String month,
			String type,
			@ModelAttribute("cri") Criteria criteria,
			String search) {
		
		
		model.addAttribute("cateList",boardService.list(kind));
		
		if( pageName!=null && pageName !="") {
			model.addAttribute("board",boardService.getBoard(kind, pageName,criteria))
				.addAttribute("p", new Pagination(criteria, boardService.getToalCount(kind, pageName,criteria)));;
		}	

		//intro만의 attachList
		if( pageName!=null && pageName.equals("intro")) {
			SchoolVO vo = boardService.detail(1L);
			if(vo.getAttachFileCnt()>=1) {
				List<AttachVO> attachList=attachService.list(1L);
				model.addAttribute("attachList",attachList);
			}
		};
		
		
		//급식 메뉴에 달을 넣어서 표시해야하는데 오늘 달을 검색하게 하기 or 직접 입력하기 
		if( pageName!=null && pageName.equals("cafe")) {
			Calendar calendar = Calendar.getInstance();
			int today = calendar.get(Calendar.MONTH)+1;
			
			if(month != null && month!="") {
				int monthInt = Integer.parseInt(month);
				if(monthInt>12) monthInt %= 12;
				if(monthInt<1) monthInt += 12;
				if(monthInt<1 || monthInt > 13 ) {
					throw new monthException("잘못된 값입니다");
				}
				today = monthInt; 
			};
			
			SchoolVO vo	= boardService.serchCafeInfo(pageName,today);
			
			model.addAttribute("today",today);
			
			if(vo.getAttachFileCnt()>=1) {
				List<AttachVO> attachList=attachService.list(vo.getSno());
				model.addAttribute("attachList",attachList);
			}
		};

		return "board/schoolInfo";
	}
	
	//교사
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
	@GetMapping(value = {"/teacherBoard/{kind}/{pageName}"})
	public String teacher(Model model,@PathVariable(required = false) String kind,
			@PathVariable String pageName,
			String type,
			@ModelAttribute("cri") Criteria criteria,
			String search) {
		
		model.addAttribute("cateList",boardService.list(kind));
		
		if( pageName!=null && pageName !="") {
			model.addAttribute("board",boardService.getBoard(kind, pageName,criteria))
				.addAttribute("p", new Pagination(criteria, boardService.getToalCount(kind, pageName,criteria)));;
		}

		if(pageName.equals("record")) {
			List<PeopleVO> StudentList = peopleService.studentList();
			List<TestVO> testList = testService.getList();
			model.addAttribute("test",testList);
			model.addAttribute("StudentList",StudentList);
		}
		if(pageName.equals("attend")) {
			List<PeopleVO> studentList = peopleService.studentList();
			List<AttendVO> attendList =  attendService.getList();
			model.addAttribute("StudentList",studentList);
			model.addAttribute("attendList",attendList);
		}
		
		if(pageName.equals("tBoard")) {
			model.addAttribute("board",boardService.getBoard(kind, pageName,criteria))
			.addAttribute("p", new Pagination(criteria, boardService.getToalCount(kind, pageName,criteria)));;
		}

		
		
		return "board/schoolInfo";
	}

	//학생
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
	@GetMapping(value = {"/studentBoard/{kind}/{pageName}"})
	public String student(Model model,@PathVariable(required = false) String kind, Authentication  auth,
			@PathVariable String pageName,
			String type,
			@ModelAttribute("cri") Criteria criteria,
			String search) {
		model.addAttribute("cateList",boardService.list(kind));
		
		MemberDetail principal = (MemberDetail) auth.getPrincipal();
		MemberVO vo = null;
		
		if(principal !=null) vo = memberService.getPno(principal.getUsername());
		
		PeopleVO peopleVO = peopleService.getOne(vo.getTel());
		
		

		if( pageName!=null && pageName !="") {
			model.addAttribute("board",boardService.getBoard(kind, pageName,criteria))
				.addAttribute("p", new Pagination(criteria, boardService.getToalCount(kind, pageName,criteria)));;
		}
		
		if(pageName.equals("score")) {
			if(peopleVO != null) {
				List<TestVO> test = testService.getOne(peopleVO.getPno());
				model.addAttribute("test" , test);
			}
		}

		
		if(pageName.equals("sAttedent")) {
			if(peopleVO != null) {
				AttendVO attend = attendService.getVO(peopleVO.getPno());
				model.addAttribute("attend" , attend);
			}
		}
		

		
		
		if(pageName.equals("sBoard")) {
			model.addAttribute("board",boardService.getBoard(kind, pageName,criteria))
				.addAttribute("p", new Pagination(criteria, boardService.getToalCount(kind, pageName,criteria)));;
		}
		
		return "board/schoolInfo";
	}

	
	//행정
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINIST')")
	@GetMapping(value = {"/adminst/{kind}/{pageName}"})
	public String adminst(Model model,@PathVariable(required = false) String kind,
			@PathVariable String pageName,
			String type,
			@ModelAttribute("cri") Criteria criteria,
			String search) {
		model.addAttribute("cateList",boardService.list(kind));
		
		if( pageName!=null && pageName !="") {
		}
		
		if(pageName.equals("event_in")) {
			List<ApprovalVO> list = approvalService.eventList();
			model.addAttribute("event",list);
		}
		
		if(pageName.equals("expend")) {
			List<ExpendVO> expendList = approvalService.getExpend(pageName);
			model.addAttribute("expend",expendList);
		}
		
		if(pageName.equals("construction")) {
			List<ExpendVO> expendList = approvalService.getExpend(pageName);
			model.addAttribute("expend",expendList);
		}
		
		
		return "board/schoolInfo";
	}
	
	//교감
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_VICEP')")
	@GetMapping(value = {"/vicep/{kind}/{pageName}"})
	public String vicep(Model model,@PathVariable(required = false) String kind,
			@PathVariable String pageName,
			String type,
			@ModelAttribute("cri") Criteria criteria,
			String search) {
		model.addAttribute("cateList",boardService.list(kind));
		
		
		if(pageName.equals("join")) {
			List<PeopleVO> list = peopleService.list();
			model.addAttribute("list",list);
		}
		
		if(pageName.equals("event_sign") && kind.equals("vicep")) {
			List<ApprovalVO> list = approvalService.eventList();
			model.addAttribute("event",list);
		}
		
		if(pageName.equals("sign")) {
			List<ExpendVO> signList = approvalService.getSignExpend();
			model.addAttribute("sign",signList);
		}
		
		return "board/schoolInfo";
	}
	//교장
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PRINCIPAL')")
	@GetMapping(value = {"/principal/{kind}/{pageName}"})
	public String principal(Model model,@PathVariable(required = false) String kind,
			@PathVariable String pageName,
			String type,
			@ModelAttribute("cri") Criteria criteria,
			String search) {
		
		model.addAttribute("cateList",boardService.list(kind));
		
		if(pageName.equals("p_event_sign")) {
			List<ApprovalVO> list = approvalService.eventListP();
			model.addAttribute("event",list);
		}
		if(pageName.equals("p_join")) {
			List<ApprovalVO> list = approvalService.list();
			model.addAttribute("list",list);
		}

		
		
		if(pageName.equals("p_sign")) {
			List<ExpendVO> signList = approvalService.getSignExpend();
			model.addAttribute("sign",signList);
		}
	
		return "board/schoolInfo";
	}
	
	
	
	@PostMapping("/write")
	public String write(@Valid SchoolVO vo, Errors errors, RedirectAttributes rttr,Model model,
			@RequestParam("attachFile") List<MultipartFile> multipartFiles,String kind, String cate_id) {
		
		if(errors.hasErrors()) {
			model.addAttribute("cateList",boardService.list(kind));
			model.addAttribute("kind",kind);
			model.addAttribute("pageName",cate_id);
			model.addAttribute("vo",vo);
			return "/board/writeForm";
		}
		
		boardService.write(vo, multipartFiles);
		
		return "redirect:/board/"+kind+"/"+kind+"/"+cate_id;
	}
	
	
	/*
	 * 메인 사진 게시판과 게시판 나누기 
	 * 메인은 사진만 올라가고 위치가 고정이여서 따로 만듬
	 * */
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINIST','ROLE_PRINCIPAL','ROLE_VICEP')")
	@PostMapping("/pictureUpdate")
	public String pictureUpdate(SchoolVO vo,RedirectAttributes rttr,
			@RequestParam("attachFile") List<MultipartFile> multipartFile) {
		
		boardService.pictureUpdate(vo, multipartFile);
		return "redirect:/board/school/school/Intro";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINIST','ROLE_PRINCIPAL','ROLE_VICEP')")
	@GetMapping(value = {"/write/{kind}/{pageName}"})
	public String ownWriteForm(Model model,@PathVariable String kind, @PathVariable String pageName) {
		model.addAttribute("cateList",boardService.list(kind));
		
		return "/board/writeForm";
	}
	
	@GetMapping(value = {"/freeWrite/{kind}/{pageName}"})
	public String writeForm(Model model,@PathVariable String kind, @PathVariable String pageName) {
		model.addAttribute("cateList",boardService.list(kind));
		
		return "/board/freeWriteForm";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINIST')")
	@GetMapping(value = {"/eWrite/{kind}/{pageName}"})
	public String eventApprovalForm(Model model,@PathVariable String kind, @PathVariable String pageName) {
		model.addAttribute("cateList",boardService.list(kind));
		
		return "/board/administ/eventWriteForm";
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINIST')")
	@GetMapping(value = {"/construction/{kind}/{pageName}"})
	public String constructionForm(Model model,@PathVariable String kind, @PathVariable String pageName) {
		model.addAttribute("cateList",boardService.list(kind));
		
		return "/board/administ/constructionForm";
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINIST')")
	@GetMapping(value = {"/expend/{kind}/{pageName}"})
	public String expendForm(Model model,@PathVariable String kind, @PathVariable String pageName) {
		model.addAttribute("cateList",boardService.list(kind));
		
		return "/board/administ/"+pageName+"Form";
	}
	

	
	@GetMapping("/detail/{sno}")
	public String detail(Model model,@PathVariable Long sno) {
		
		List<Reply_boardVO> replyList = boardService.findReply(sno);
		
		SchoolVO detail = boardService.detail(sno);
		
		if(detail.getAttachFileCnt()>=1) {
			List<AttachVO> attachList=attachService.list(sno);
			model.addAttribute("attachList",attachList);
		}
		
		model.addAttribute("reply",replyList);
		model.addAttribute("cateList",boardService.list(detail.getKind()));
		model.addAttribute("d",detail);
		
		return "/board/detail";
	}
	
	@PostMapping("/delete")
	public String remove(Long sno,Model model,RedirectAttributes rttr,String kind,String pageName) {
		SchoolVO detail = boardService.detail(sno);
		model.addAttribute("cateList",boardService.list(detail.getKind()));
		boardService.remove(sno);
		
		return "redirect:/board/"+kind+"/"+kind+"/"+pageName;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINIST')")
	@PostMapping("/expendWrite")
	public String expendWrite(@Valid @ModelAttribute("expendDTO") ExpendDTO expendDTO,Errors errors,
			RedirectAttributes rttr,String kind,String pageName,Model model) {
		
		if(errors.hasErrors()) {
			model.addAttribute("cateList",boardService.list(kind));
			model.addAttribute("kind",kind);
			model.addAttribute("pageName",pageName);
			return "/board/administ/expendForm";
		}
		
		approvalService.expendAdd(expendDTO);
		return "redirect:/board/"+kind+"/"+kind+"/"+pageName;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINIST')")
	@PostMapping("/constructionWrite")
	public String constructionWrite(@Valid ExpendVO vo,Errors errors,
		RedirectAttributes rttr,String k,String pageName,Model model) {
		
		if(errors.hasErrors()) {
			model.addAttribute("cateList",boardService.list(k));
			model.addAttribute("kind",k);
			model.addAttribute("pageName",pageName);
			return "/board/administ/constructionForm";
		}
		
		approvalService.constructionAdd(vo);
		return "redirect:/board/"+k+"/"+k+"/"+pageName;
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_NUTRI')")
	@PostMapping("/cafeUpdate")
	public String cate(SchoolVO vo,RedirectAttributes rttr,Model model
			,@RequestParam("attachFile") List<MultipartFile> multipartFile) {
		model.addAttribute("cateList",boardService.list(vo.getKind()));
		
		boardService.cafeUpdate(vo, multipartFile);
		
		
		return "redirect:/board/list/school/cafe";
	}
	
	@GetMapping("/modify")
	public String modifyForm(Long sno, Model model,String pageName) {
		SchoolVO vo = boardService.detail(sno);
		model.addAttribute("cateList",boardService.list(vo.getKind()));
		if(vo.getAttachFileCnt()>=1) {
			List<AttachVO> attachList=attachService.list(sno);
			model.addAttribute("attachList",attachList);
		}		
		model.addAttribute("pageName",pageName);
		model.addAttribute("d",vo);
		
		return "/board/modifyForm";
	}
	
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINIST','ROLE_PRINCIPAL','ROLE_VICEP','ROLE_NUTRI','ROLE_TEACHER','ROLE_SAFE','ROLE_STUDENT')")
	@PostMapping("/modify")
	public String modify(ModifySnoS delCheck,SchoolVO vo,RedirectAttributes rttr,
			@RequestParam("attachFile") List<MultipartFile> multipartFiles) {
		boardService.modifyBoard(delCheck, vo, multipartFiles);
		return "redirect:/board/detail/"+vo.getSno();
	}
	
}
