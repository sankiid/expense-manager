package com.expense.manager.controller;

import com.expense.manager.bo.Expense;
import com.expense.manager.bo.User;
import com.expense.manager.dto.Response;
import com.expense.manager.exception.ValidationException;
import com.expense.manager.service.IExpenseService;
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
@RequestMapping(value = "/api/expense")
public class ExpenseController {
    private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);

    @Autowired
    private IExpenseService expenseService;
    @Value("${expense.create.message}")
    private String expenseCreateMessage;
    @Value("${expense.update.message}")
    private String expenseUpdateMessage;
    @Value("${expense.delete.message}")
    private String expenseDeleteMessage;

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public Response addIncome(HttpServletRequest request, @RequestBody Expense expense) throws ValidationException {
        User user = UserUtils.getUser(request);
        long id = expenseService.save(expense, user.getUserId());
        expense.setId(id);
        Response response = new Response(201, expenseCreateMessage, null, expense);
        return response;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response updateIncome(HttpServletRequest request, @RequestBody Expense expense) throws ValidationException {
        User user = UserUtils.getUser(request);
        expenseService.update(expense, user.getUserId());
        Response response = new Response(200, expenseUpdateMessage, null, null);
        return response;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Response getIncome(HttpServletRequest request, @PathVariable long id) throws ValidationException {
        User user = UserUtils.getUser(request);
        Expense expense = expenseService.get(id, user.getUserId());
        Response response = new Response(200, null, null, expense);
        return response;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Response deleteIncome(HttpServletRequest request, @PathVariable long id) throws ValidationException {
        User user = UserUtils.getUser(request);
        expenseService.delete(id, user.getUserId());
        Response response = new Response(200, expenseDeleteMessage, null, id);
        return response;
    }

    /**
     *
     * @param request
     * @param start time is milli sec
     * @param end time is milli sec
     * @return
     * @throws ValidationException
     */
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(value = "/get/{start}/{end}", method = RequestMethod.GET)
    public Response getIncomeByDate(HttpServletRequest request, @PathVariable long start, @PathVariable long end) throws ValidationException {
        User user = UserUtils.getUser(request);
        List<Expense> expenses = expenseService.getByDate(new Date(start), new Date(end), user.getUserId());
        Response response = new Response(200, null, null, expenses);
        return response;
    }
}
