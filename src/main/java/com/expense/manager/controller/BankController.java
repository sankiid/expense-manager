package com.expense.manager.controller;

import com.expense.manager.bo.Bank;
import com.expense.manager.dto.Response;
import com.expense.manager.service.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/bank")
public class BankController {

    @Autowired
    private IBankService bankService;

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public Response getBanks() {
        List<Bank> banks = bankService.getAllBanks();
        Response response = new Response(200, null, null, banks);
        return response;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public Response addBank(@RequestBody Bank bank){
        bankService.save(bank);
        Response response = new Response(201, null, null, null);
        return response;
    }


}
