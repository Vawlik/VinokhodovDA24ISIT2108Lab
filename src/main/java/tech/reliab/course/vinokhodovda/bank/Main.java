package tech.reliab.course.vinokhodovda.bank;

import tech.reliab.course.vinokhodovda.bank.entity.*;
import tech.reliab.course.vinokhodovda.bank.service.*;
import tech.reliab.course.vinokhodovda.bank.service.impl.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BankService bankService = new BankServiceImpl();
        AtmService bankAtmService = new AtmServiceImpl();
        BankOfficeService bankOfficeService = new BankOfficeServiceImpl();
        CreditAccountService creditAccountService = new CreditAccountServiceImpl();
        EmployeeService employeeService = new EmployeeServiceImpl();
        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl();
        UserService userService = new UserServiceImpl();

        Bank bank = bankService.createBank(
                1L,
                "МойБанк"
        );

        BankOffice bankOffice = bankOfficeService.createBankOffice(
                1L,
                "Главный офис",
                "ул. Центральная, 1",
                true,
                true,
                0,
                true,
                true,
                true,
                1.0,
                1.0,
                bank
        );
        Employee employee = employeeService.createEmployee(
                1L,
                "Иванов Иван Иванович",
                LocalDate.now(),
                "Менеджер по работе с клиентами",
                bank,
                true,
                bankOffice,
                true,
                5000.0);

        User user = userService.createUser(
                1L,
                "Иванов Петр Сергеевич",
                LocalDate.of(1990, Month.JANUARY, 15),
                "ООО Рога и Копыта",
                List.of(bank)
        );
        PaymentAccount paymentAccount = paymentAccountService.createPaymentAccount(
                1L,
                user,
                bank,
                1400.0
        );
        BankAtm bankAtm = bankAtmService.createBankAtm(
                1L,
                "Главный банкомат",
                true,
                bank,
                bankOffice,
                employee,
                true,
                1455.0,
                true,
                1.0
        );
        CreditAccount creditAccount = creditAccountService.createCreditAccount(
                1L,
                user,
                LocalDate.now(),
                LocalDate.now(),
                1,
                1.0,
                1.0,
                1.0,
                employee,
                paymentAccount,
                bank
        );
        System.out.println(bank);
        System.out.println();
        System.out.println(bankAtm);
        System.out.println();
        System.out.println(bankOffice);
        System.out.println();
        System.out.println(creditAccount);
        System.out.println();
        System.out.println(employee);
        System.out.println();
        System.out.println(paymentAccount);
        System.out.println();
        System.out.println(user);
    }
}
