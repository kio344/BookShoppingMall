package controllers.user;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import models.user.UserDto;

@Controller
@RequestMapping("/user/logout")
public class UserLogoutController {

	@GetMapping
	public String form(HttpSession session) {

		UserDto user = (UserDto) session.getAttribute("user");

		String accessToken = (String) session.getAttribute("access_token");
		System.out.println(accessToken);
		if (accessToken != null) {
			// 카카오에 요청 보내기
			WebClient webClient = WebClient.builder().baseUrl("https://kapi.kakao.com")
					.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

			JSONObject response = webClient.post().uri(uriBuilder -> uriBuilder.path("/v1/user/logout").build())
					.header("Authorization", "Bearer " + accessToken).retrieve().bodyToMono(JSONObject.class).block();

			// 로그아웃하면서 만료된 토큰을 세션에서 삭제
			session.removeAttribute("access_token");
		}

		if (user != null) {
			session.invalidate();
		}

		return "redirect:/";
	}

}
