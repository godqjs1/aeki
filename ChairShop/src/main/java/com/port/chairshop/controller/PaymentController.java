package com.port.chairshop.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@Controller
public class PaymentController {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    private final IamportClient iamportClient;

    public PaymentController() {
        this.iamportClient = new IamportClient("3666458475531312",
                "gDBIeNj4uNQnPIHudjqXRa33XieGQJznJP5uIiC9k6J0kaOG3tfuoJwc4QS03C3VU4oZJbFLmfPQNVh2");
        		logger.info("111111111111111111111111111111111111111");
    }

    @ResponseBody
    @PostMapping("/verify/{imp_uid}")
    public IamportResponse<Payment> paymentByImpUid(@PathVariable("imp_uid") String imp_uid)
            throws IamportResponseException, IOException {
    	logger.info("22222222222222222222222222222222222222222");
        return iamportClient.paymentByImpUid(imp_uid);
    }

}