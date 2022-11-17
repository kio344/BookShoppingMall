package controllers.kakao;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
@RequestMapping("/api/kakao")
public class KakaoController {

	@GetMapping("/oauth")
	public String kakaoConnect() {
		
		StringBuffer url = new StringBuffer();
		url.append("https://kauth.kakao.com/oauth/authorize?");
		url.append("client_id=");
		url.append("709858348209ad29c9ca0fe8f0f7acbc");
		url.append("&redirect_uri=http://localhost:8080/bookShoppingMall/api/kakao/callback");
		url.append("&response_type=code");
		
		return "redirect:" + url;
	}
	
	@RequestMapping(value = "/callback", produces = "application/json", method = {RequestMethod.GET, RequestMethod.POST})
	public void kakaoLogin(@RequestParam("code") String code, HttpSession session) {
		
		String accessToken = getKakaoAccessToken(code);
		session.setAttribute("access_token", accessToken);
		
		getKakaoUserInfo(accessToken);
	}
	
	private String getKakaoAccessToken(String code) {
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
						.queryParam("redirect_uri", "http://localhost:8080/bookShoppingMall/api/kakao/callback")
						.queryParam("code", code).build())
				.retrieve().bodyToMono(JSONObject.class).block();
		
		return (String) response.get("access_token");
	}
	
	private void getKakaoUserInfo(String accessToken) {
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
		System.out.println(name);
		System.out.println(id);
		
	}
	
	
}
