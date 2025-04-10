package com.woolf.project.product.client;


import com.woolf.project.product.dto.PaymentClientDTO;
import com.woolf.project.product.exceptions.PaymentClientException;

public interface PaymentServiceClient {
    PaymentClientDTO createPaymentOrder(String invoiceNumber, String currency, Double amount) throws PaymentClientException;
    PaymentClientDTO getPaymentStatus(String paymentOrderId) throws PaymentClientException;
    PaymentClientDTO processRefund(String paymentOrderId) throws PaymentClientException;
}
