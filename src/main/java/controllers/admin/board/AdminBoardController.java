package controllers.admin.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import models.admin.board.AdminBoardDao;
import models.admin.board.AdminBoardDto;
import models.admin.board.AdminBoardRequest;
import models.admin.board.service.AdminBoardDeleteService;
import models.admin.board.service.AdminBoardService;
import models.admin.board.service.AdminBoardUpdateService;

@Controller
@RequestMapping("/admin")
public class AdminBoardController {

	@Autowired
	private AdminBoardService adminBoardService;

	@Autowired
	private AdminBoardUpdateService adminBoardUpdateService;

	@Autowired
	private AdminBoardDeleteService adminBoardDeleteService;

	@Autowired
	private AdminBoardDao adminBoardDao;

	@GetMapping("/board")
	public String adminBoard(Model model) {
		AdminBoardRequest adminBoardRequest = new AdminBoardRequest();
		model.addAttribute("adminBoardRequest", adminBoardRequest);

		List<AdminBoardDto> list = adminBoardDao.gets();

		model.addAttribute("list", list);
		model.addAttribute("addCss", new String[] { "/board/admin/adminBoard" });

		return "admin/board/adminBoard";
	}

	@PostMapping("/board")
	public String adminBoardWrite(@Valid AdminBoardRequest adminBoardRequest, Errors errors, Model model) {
		List<AdminBoardDto> list = adminBoardDao.gets();

		model.addAttribute("list", list);
		model.addAttribute("addCss", new String[] { "/board/admin/adminBoard" });

		if (errors.hasErrors()) {
			return "admin/board/adminBoard";
		}

		adminBoardService.write(adminBoardRequest, errors);

		if (errors.hasErrors()) {
			return "admin/board/adminBoard";
		}

		return "redirect:/admin/board";
	}

	@PostMapping("/list")
	public String adminBoardList(HttpServletRequest req, Model model,
			@RequestParam(value = "mode", required = false) String mode, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		List<AdminBoardDto> list = adminBoardDao.gets();

		model.addAttribute("list", list);
		model.addAttribute("addCss", new String[] { "/board/admin/adminBoard" });

		if (mode.equals("update")) {

			try {
				/** 게시판 수정 메서드 */
				adminBoardUpdateService.update(req);
				out.println("<script>parent.location.reload();</script>");
			} catch (RuntimeException e) {
				out.println("<script>alert('" + e.getMessage() + "')</script>");
				out.println("<script>parent.location.reload();</script>");
				
				return "admin/board/adminBoard";
			}

		} else if (mode.equals("delete")) {

			try {
				/** 게시판 삭제 메서드 */
				adminBoardDeleteService.delete(req);
				out.println("<script>parent.location.reload();</script>");
			} catch (RuntimeException e) {

				out.println("<script>alert('" + e.getMessage() + "')</script>");
				out.println("<script>parent.location.reload();</script>");

				return "admin/board/adminBoard";
			}
		}
		
		return "admin/board/adminBoard";
	}
}
