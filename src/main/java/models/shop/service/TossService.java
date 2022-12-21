package models.shop.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

/**
 * 토스 api 콜백처리
 * 
 * @author 5563a
 *
 */
@Service
public class TossService {

	public void paymentCallBackSc(String orderId, String paymentKey, Long amount) {

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
				.header("Authorization", "Basic dGVzdF9za19PNkJZcTdHV1BWdk5sNW9kRFFtVk5FNXZibzFkOg==")
				.header("Content-Type", "application/json")
				.method("POST", HttpRequest.BodyPublishers.ofString("{\"paymentKey\":\"" + paymentKey + "\",\"amount\":"
						+ amount + ",\"orderId\":\"" + orderId + "\"}"))
				.build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(response.body());

	}

	public void paymentCallBackFail(String orderId, String paymentKey, Long amount) {

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
				.header("Authorization", "Basic dGVzdF9za19PNkJZcTdHV1BWdk5sNW9kRFFtVk5FNXZibzFkOg==")
				.header("Content-Type", "application/json")
				.method("POST", HttpRequest.BodyPublishers.ofString("{\"paymentKey\":\"" + paymentKey + "\",\"amount\":"
						+ amount + ",\"orderId\":\"" + orderId + "\"}"))
				.build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(response.body());

	}

}
