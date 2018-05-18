package com.expense.manager.controller;

import com.expense.manager.bo.Category;
import com.expense.manager.cache.CategoryCache;
import com.expense.manager.dto.Response;
import com.expense.manager.exception.CategoryNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    private CategoryCache categoryCache = CategoryCache.getInstance();

    /*@RequestMapping(value = "/get/{name}", method = RequestMethod.GET)
    public Response getCategoryByName(@PathVariable String name) throws CategoryNotFoundException {
        Category category = categoryCache.getByName(name);
        if(category == null)
            throw new CategoryNotFoundException("Unable to find category");
        Response response = new Response(200, null, null, category);
        return response;
    }*/

    @RequestMapping(value = "/get/{type}", method = RequestMethod.GET)
    public Response getCategoryByType(@PathVariable String type) throws CategoryNotFoundException {
        List<Category> categoryList = categoryCache.getByTypes(type);
        Response response = new Response(200, null, null, categoryList);
        return response;
    }
}
