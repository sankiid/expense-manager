package com.expense.manager.controller;

import com.expense.manager.bo.Income;
import com.expense.manager.bo.User;
import com.expense.manager.dto.Response;
import com.expense.manager.exception.ValidationException;
import com.expense.manager.service.IIncomeService;
import com.expense.manager.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/income")
public class IncomeController {
    private static final Logger logger = LoggerFactory.getLogger(IncomeController.class);

    @Autowired
    private IIncomeService incomeService;
    @Value("${income.create.message}")
    private String incomeCreateMessage;
    @Value("${income.update.message}")
    private String incomeUpdateMessage;
    @Value("${income.delete.message}")
    private String incomeDeleteMessage;

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public Response addIncome(HttpServletRequest request, @RequestBody Income income) throws ValidationException {
        User user = UserUtils.getUser(request);
        long id = incomeService.save(income, user.getUserId());
        income.setId(id);
        Response response = new Response(201, incomeCreateMessage, null, income);
        return response;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response updateIncome(HttpServletRequest request, @RequestBody Income income) throws ValidationException {
        User user = UserUtils.getUser(request);
        incomeService.update(income, user.getUserId());
        Response response = new Response(200, incomeUpdateMessage, null, null);
        return response;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Response getIncome(HttpServletRequest request, @PathVariable long id) throws ValidationException {
        User user = UserUtils.getUser(request);
        Income income = incomeService.get(id, user.getUserId());
        Response response = new Response(200, null, null, income);
        return response;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Response deleteIncome(HttpServletRequest request, @PathVariable long id) throws ValidationException {
        User user = UserUtils.getUser(request);
        incomeService.delete(id, user.getUserId());
        Response response = new Response(200, incomeDeleteMessage, null, id);
        return response;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(value = "/get/{start}/{end}", method = RequestMethod.GET)
    public Response getIncomeByDate(HttpServletRequest request, @PathVariable long start, @PathVariable long end) throws ValidationException {
        User user = UserUtils.getUser(request);

        List<Income> incomes = incomeService.getByDate(new Date(start), new Date(end), user.getUserId());
        Response response = new Response(200, null, null, incomes);
        return response;
    }


}
