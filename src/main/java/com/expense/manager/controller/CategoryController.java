package com.expense.manager.controller;

import com.expense.manager.bo.Category;
import com.expense.manager.dto.Response;
import com.expense.manager.exception.CategoryNotFoundException;
import com.expense.manager.service.ICategoryService;
import com.expense.manager.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "/get/{type}", method = RequestMethod.GET)
    public Response getCategoryByType(@PathVariable String type) throws CategoryNotFoundException {
        List<Category> categoryList = categoryService.getCategoryByType(type);
        Response response = new Response(200, null, null, categoryList);
        return response;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public Response addCategory(HttpServletRequest request, @RequestBody Category category){
        long id = categoryService.save(category);
        Response response = new Response(201, "category add successfully", null, id);
        return response;
    }

}
