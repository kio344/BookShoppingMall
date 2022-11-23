package controllers.kakao;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import models.user.UserDao;
import models.user.UserDto;

@Controller
@RequestMapping("/api/kakao")
public class KakaoController {
	
	@Autowired
	private UserDao userDao;

	@GetMapping("/oauth")
	public String kakaoConnect(HttpServletRequest request) {
		
		StringBuffer url = new StringBuffer();
		url.append("https://kauth.kakao.com/oauth/authorize?");
		url.append("client_id=");
		url.append("709858348209ad29c9ca0fe8f0f7acbc");
		url.append("&redirect_uri=http://localhost:3000" + request.getContextPath() + "/api/kakao/callback");
		url.append("&response_type=code");
		
		return "redirect:" + url;
	}
	
	@RequestMapping(value = "/callback", produces = "application/json", method = {RequestMethod.GET, RequestMethod.POST})
	public String kakaoLogin(@RequestParam("code") String code, HttpSession session, HttpServletRequest request) {
		
		String accessToken = getKakaoAccessToken(code, request);
		session.setAttribute("access_token", accessToken);
		
		String url = getKakaoUserInfo(accessToken, session);
		
		return url;
	}
	
	private String getKakaoAccessToken(String code, HttpServletRequest request) {
		//카카오에 보낼 api
		WebClient webClient = WebClient.builder()
				.baseUrl("https://kauth.kakao.com")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
		
		//카카오 서버에 요청 보내기 & 응답 받기
		JSONObject response = webClient.post()
				.uri(uriBulider -> uriBulider
						.path("/oauth/token")
						.queryParam("grant_type", "authorization_code")
						.queryParam("client_id", "709858348209ad29c9ca0fe8f0f7acbc")
						.queryParam("redirect_uri", "http://localhost:3000" + request.getContextPath() + "/api/kakao/callback")
						.queryParam("code", code).build())
				.retrieve().bodyToMono(JSONObject.class).block();
		
		return (String) response.get("access_token");
	}
	
	private String getKakaoUserInfo(String accessToken, HttpSession session) {
		//카카오에 요청 보내기 및 응답 받기
		WebClient webClient = WebClient.builder()
				.baseUrl("https://kapi.kakao.com")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
		
		JSONObject response = webClient.post()
				.uri(uriBulider -> uriBulider
						.path("/v2/user/me")
						.build())
				.header("Authorization", "Bearer " + accessToken)
				.retrieve().bodyToMono(JSONObject.class).block();
		
		//받은 응답에서 원하는 정보 추출 하기
		Long id = (Long)response.get("id");
		Map<String, Object> map = (Map<String, Object>)response.get("kakao_account");
		Map<String, Object> profile = (Map<String, Object>)map.get("profile");
		String name = (String)profile.get("nickname");
		String url = null;
		
		UserDto user = (UserDto)session.getAttribute("user");
		if(user != null) {
			UserDto dto = userDao.kakaoLink(user, id);
			session.setAttribute("user", dto);
			url = "redirect:/";
		} else {
			url = userMath(id, session);
		}
		
		return url;
	}
	
	private String userMath(Long id, HttpSession session) {
		
		UserDto user = userDao.kakaoMatching(id);
		if(user == null) {
			return "redirect:/user/join?kakaoId=" + id;
		} else {
			session.setAttribute("user", user);
			return "redirect:/";
		}
		
	}
	
	
}
