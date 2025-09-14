package com.ecommerce.payment.service;


import com.ecommerce.payment.dto.PaymentResponse;
import com.ecommerce.payment.model.Payment;

public interface PaymentService {

	public PaymentResponse processPayment(Long orderId, Double amount);
	public PaymentResponse getPaymentByOrder(Long orderId);
}
