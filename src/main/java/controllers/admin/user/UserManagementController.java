package controllers.admin.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import common.page.Pagination_v2;
import models.admin.user.UserInfoDao;
import models.admin.user.service.AdminUserInfoService;
import models.admin.user.service.UserSearchService;
import models.user.UserDto;


@Controller
@RequestMapping("admin/")
public class UserManagementController {

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private UserSearchService searchService;
	
	@Autowired
	private AdminUserInfoService infoService;
	
	@GetMapping("user")
	public String userManager(@RequestParam(required = false, name = "search", defaultValue = "") String search,
			@RequestParam(required = false, name = "searchType", defaultValue = "") String searchType,
			@RequestParam(required = false, defaultValue = "1") int page, Model model) {
		
		List<UserDto> dto = userInfoDao.gets();
		int limit = 5;
		String link = "/admin/user?search=" + search + "&searchType=" + searchType;
		Pagination_v2 pagination = new Pagination_v2(page, limit, 0, limit, link);
		
		model.addAttribute("userInfo", dto);
		model.addAttribute("pagination", pagination);
		model.addAttribute("addCss", new String[] { "/board/admin/adminUser"});
		
		if(!searchType.isBlank()) {
			List<UserDto> user = searchService.search(search, searchType, (page - 1) * limit, limit);
			model.addAttribute("userInfo", user);
		}

		return "admin/user/user";
	}

	@PostMapping("user/userEdit")
	public String userInfoCng(@RequestParam(required = false, name = "mode")String mode, HttpServletRequest request) {
		
		if(mode.equals("edit")) {
			infoService.edit(request);
		}else {
			infoService.remove(request);
		}

		return "redirect:/admin/user";

	}

}
