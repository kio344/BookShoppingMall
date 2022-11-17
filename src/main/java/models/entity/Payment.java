package models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import models.shop.payment.PaymentProgress;

@Entity
public class Payment extends BaseEntity{
	
	@Id @GeneratedValue
	private Long num;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User user;
	
	@Column(nullable = false)
	private Integer count;
	
	@Column(nullable = false ,length = 100)
	private String recipient_name;
	
	@Column(nullable = false,length = 11)
	private String recipient_mobile;
	
	@Column(nullable = false)
	private String address;
	
	@Enumerated(EnumType.STRING)
	private PaymentProgress progress;

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getRecipient_name() {
		return recipient_name;
	}

	public void setRecipient_name(String recipient_name) {
		this.recipient_name = recipient_name;
	}

	public String getRecipient_mobile() {
		return recipient_mobile;
	}

	public void setRecipient_mobile(String recipient_mobile) {
		this.recipient_mobile = recipient_mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public PaymentProgress getProgress() {
		return progress;
	}

	public void setProgress(PaymentProgress progress) {
		this.progress = progress;
	}
	
	
	
}
