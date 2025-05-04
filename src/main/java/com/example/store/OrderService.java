package com.example.store;

public class OrderService {
    private PaymentService paymentService;

    //    we receive a payment service object here in this constructor
//    public OrderService(PaymentService paymentService){
//        this.paymentService=paymentService;
//    }
    public void placeOrder(){
        paymentService.processPayment(10);
    }
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
