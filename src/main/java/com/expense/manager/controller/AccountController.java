package com.expense.manager.controller;

import com.expense.manager.bo.Income;
import com.expense.manager.bo.User;
import com.expense.manager.bo.UserAccountInfo;
import com.expense.manager.dto.Response;
import com.expense.manager.exception.ValidationException;
import com.expense.manager.service.IAccountService;
import com.expense.manager.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public Response add(HttpServletRequest request, @RequestBody UserAccountInfo accountInfo) throws ValidationException {
        User user = UserUtils.getUser(request);
        long id = accountService.save(accountInfo, user.getUserId());
        accountInfo.setId(id);
        Response response = new Response(201, "", null, accountInfo);
        return response;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response update(HttpServletRequest request, @RequestBody UserAccountInfo accountInfo) throws ValidationException {
        User user = UserUtils.getUser(request);
        accountService.update(accountInfo, user.getUserId());
        Response response = new Response(200, "", null, accountInfo);
        return response;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Response get(HttpServletRequest request, @PathVariable long id) throws ValidationException {
        User user = UserUtils.getUser(request);
        UserAccountInfo userAccountInfo = accountService.get(id, user.getUserId());
        Response response = new Response(200, null, null, userAccountInfo);
        return response;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public Response getAllAccount(HttpServletRequest request) throws ValidationException {
        User user = UserUtils.getUser(request);
        List<UserAccountInfo> userAccountInfos = accountService.getAll(user.getUserId());
        Response response = new Response(200, null, null, userAccountInfos);
        return response;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Response delete(HttpServletRequest request, @PathVariable long id) throws ValidationException {
        User user = UserUtils.getUser(request);
        accountService.delete(id, user.getUserId());
        Response response = new Response(200, "", null, id);
        return response;
    }
}
