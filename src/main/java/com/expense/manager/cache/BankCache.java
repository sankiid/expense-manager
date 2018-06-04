package com.expense.manager.cache;

import com.expense.manager.bo.Bank;
import com.expense.manager.service.IBankService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankCache {

    private Map<Long, Bank> idToBankCache = new HashMap<>();
    private IBankService bankService = null;
    private static BankCache bankCache = new BankCache();

    private BankCache(){
    }

    public static BankCache getInstance(){
        return bankCache;
    }

    public void loadAll() {
        List<Bank> banks = bankService.getAllBanks();
        banks.forEach(b -> {
            setValues(b);
        });
    }

    public void setValues(Bank b) {
        idToBankCache.put(b.getId(), b);
    }

    public Bank getById(long id){
        Bank bank = idToBankCache.get(id);
        if(bank == null){
            Bank _bank = bankService.getBankById(id);
            if(_bank != null) {
                setValues(_bank);
                bank = idToBankCache.get(id);
            }
        }
        return bank;
    }

    public void init(IBankService bankService) {
        if(this.bankService == null){
            this.bankService = bankService;
        }
    }
}
