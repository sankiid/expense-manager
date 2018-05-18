package com.expense.manager;

import com.expense.manager.cache.CategoryCache;
import com.expense.manager.cache.RoleCache;
import com.expense.manager.service.ICategoryService;
import com.expense.manager.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ExpenseManagerApplication extends WebMvcConfigurerAdapter{
    private static final Logger logger = LoggerFactory.getLogger(ExpenseManagerApplication.class);

    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IUserService userService;

    @PostConstruct
    public void init(){
        CategoryCache.getInstance().init(categoryService);
        RoleCache.getInstance().init(userService);
        logger.info("cache init successful.");
    }

    public static void main(String[] args) {
        SpringApplication.run(ExpenseManagerApplication.class, args);

        CategoryCache.getInstance().loadAll();
        RoleCache.getInstance().loadAll();
        logger.info("server in up and running...");
    }
}
