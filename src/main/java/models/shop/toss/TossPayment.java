package models.shop.toss;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TossPayment extends TossData{
	
	private String productNm;
	private Long productPrice;
	private int productCount;
	private String scUrl;
	private String failUrl;
	private String callBackUrl;
	
	
	
	/**
	 * 
	 * @param productNm			제품명
	 * @param productPrice		제품가격
	 * @param productCount		제품 개수
	 * @param scUrl				결제 성공 시 이동할 url
	 * @param failUrl			결제 실패(취소) 시 이동할 url
	 */
	public TossPayment(String productNm, Long productPrice, int productCount, String scUrl, String failUrl,String callBackUrl) {
		super();
		this.productNm = productNm;
		this.productPrice = productPrice;
		this.productCount = productCount;
		this.scUrl = scUrl;
		this.failUrl = failUrl;
		this.callBackUrl=callBackUrl;
	}


	public TossData process() {
		

		URL url = null;
		URLConnection connection = null;
		StringBuilder responseBody = new StringBuilder();
		Long orderNo=System.currentTimeMillis();
		System.out.println(orderNo);
		System.out.println(callBackUrl);
		
		try {
			url = new URL("https://pay.toss.im/api/v2/payments");
			connection = url.openConnection();
			
			connection.addRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			org.json.simple.JSONObject jsonBody = new JSONObject();
			jsonBody.put("orderNo", orderNo);
			jsonBody.put("amount", this.productCount * this.productPrice);
			jsonBody.put("amountTaxFree", 0);
			jsonBody.put("productDesc", productNm);
			jsonBody.put("apiKey", "sk_test_w5lNQylNqa5lNQe013Nq");
			jsonBody.put("autoExecute", true);
			jsonBody.put("resultCallback", this.callBackUrl);
			jsonBody.put("callbackVersion", "V2");
			jsonBody.put("retUrl", this.scUrl);
			jsonBody.put("retCancelUrl", this.failUrl);

			BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
			bos.write(jsonBody.toJSONString().getBytes(StandardCharsets.UTF_8));
			bos.flush();
			bos.close();

			BufferedReader br = new BufferedReader(
					new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
			String line = null;
			while ((line = br.readLine()) != null) {
				responseBody.append(line);
			}
			br.close();
		} catch (Exception e) {
			responseBody.append(e);
		}


		ObjectMapper objectMapper = new ObjectMapper();
		TossData tossData = null;
		try {
			tossData = objectMapper.readValue(responseBody.toString(), TossData.class);
		} catch (JsonProcessingException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 return tossData;
	}


	@Override
	public String toString() {
		return "TossPayment [productNm=" + productNm + ", productPrice=" + productPrice + ", productCount="
				+ productCount + ", scUrl=" + scUrl + ", failUrl=" + failUrl + ", callBackUrl=" + callBackUrl + "]";
	}
	
	

	
}
