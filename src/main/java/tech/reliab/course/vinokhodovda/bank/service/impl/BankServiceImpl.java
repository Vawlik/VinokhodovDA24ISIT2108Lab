package tech.reliab.course.vinokhodovda.bank.service.impl;

import tech.reliab.course.vinokhodovda.bank.entity.Bank;
import tech.reliab.course.vinokhodovda.bank.service.BankService;

import java.util.Random;

public class BankServiceImpl implements BankService {
    /**
     * Creates a bank object with the specified parameters.
     *
     * @param id the unique identifier of the bank
     * @param name the name of the bank
     * @return the created bank object
     */

    @Override
    public Bank createBank(Long id, String name) {
        Bank bank = new Bank();
        bank.setId(id);
        bank.setName(name);
        bank.setBankRating(new Random().nextInt(101));
        bank.setTotalMoneyInBank(new Random().nextDouble(1000000));
        bank.setInterestRate(20 - bank.getBankRating() * 0.2);
        return bank;
    }

    @Override
    public Bank readBank(Long id) {
        return null;
    }

    @Override
    public void updateBank(Long id, Bank bank) {
    }

    @Override
    public void deleteBank(Long id, Bank bank) {
    }
}
