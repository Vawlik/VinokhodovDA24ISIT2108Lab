package tech.reliab.course.vinokhodovda.bank.utils;

import tech.reliab.course.vinokhodovda.bank.entity.*;
import tech.reliab.course.vinokhodovda.bank.service.*;
import tech.reliab.course.vinokhodovda.bank.service.impl.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BankGenerator {
    private static final BankService bankService = new BankServiceImpl();
    private static final BankOfficeService bankOfficeService = new BankOfficeServiceImpl();
    private static final EmployeeService employeeService = new EmployeeServiceImpl();
    private static final AtmService atmService = new AtmServiceImpl();
    private static final UserService userService = new UserServiceImpl();
    private static final PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl();
    private static final CreditAccountService creditAccountService = new CreditAccountServiceImpl();
    private static final String[] bankNames = {"СигмаБанк", "Збербанк", "ДиньДонБанк", "АТБ", "ГрузинскийСтандарт"};
    private static final String[] officeCities = {"Москва", "Санкт-Петербург", "Екатеринбург"};
    private static final String[] positions = {"Менеджер", "Бухгалтер", "Специалист по обслуживанию клиентов", "Финансовый консультант", "Кредитный специалист"};
    private static final String[] workplaces = {"Apple", "Google", "Microsoft", "Amazon", "Tesla"};
    public final List<Bank> banks = new ArrayList<>();
    private final Random random = new Random();

    public void generateBanks() {
        for (int i = 0; i < 5; i++) {
            banks.add(bankService.createBank((long) i, bankNames[i]));
        }
    }

    public void generateOffices() {
        long idCounter = 1L;
        for (Bank bank : banks) {
            for (int i = 0; i < 3; i++) {
                String randomCity = officeCities[random.nextInt(officeCities.length)];
                bank.getBankOfficeList().add(bankOfficeService.createBankOffice(idCounter++, "Офис " + (i + 1), randomCity, true,
                        true, true, true, true, 0.0, 5000.0, bank));
            }
        }
    }

    public void generateEmployees() {
        long idCounter = 1L;
        for (Bank bank : banks) {
            for (BankOffice bankOffice : bank.getBankOfficeList()) {
                for (int i = 0; i < 5; i++) {
                    String fullName = getRandomFullName();
                    String position = getRandomPosition();
                    bank.getEmployeeList().add(employeeService.createEmployee(idCounter++, fullName,
                            LocalDate.of(2004, 1, 1), position, bank, false, bankOffice, true, 100.0));
                }
            }
        }
    }

    public void generateATMs() {
        long idCounter = 1L;
        for (Bank bank : banks) {
            for (int i = 0; i < 3; i++) {
                bank.getBankAtmList().add(atmService.createBankAtm(idCounter++, "Банкомат " + (i + 1), true, bank,
                        getRandomOfficeOfBank(bank), getRandomEmployeeOfBank(bank),
                        true, 150.0, true, 10.0));
            }
        }
    }


    public void generateUsers() {
        long idCounter = 1L;
        for (Bank bank : banks) {
            for (int i = 0; i < 5; i++) {
                String workplace = getRandomWorkplace();
                String fullName = getRandomFullName();
                bank.getUserList().add(userService.createUser(idCounter++, fullName,
                        LocalDate.of(2005, 1, 1), workplace, List.of(bank)));
            }
        }
    }

    public void paymentAccount() {
        long idCounter = 1L;
        for (Bank bank : banks) {
            for (User user : bank.getUserList()) {
                for (int i = 0; i < 2; i++) {
                    user.getPaymentAccounts().add(paymentAccountService.createPaymentAccount(idCounter++, user, bank));
                }
            }
        }
    }

    public void creditAccount() {
        long idCounter = 1L;
        for (Bank bank : banks) {
            for (User user : bank.getUserList()) {
                for (int i = 0; i < 2; i++) {
                    user.getCreditAccounts().add(creditAccountService.createCreditAccount(idCounter++, user,
                            LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1),
                            12, 120.0, 10.0, bank.getInterestRate(), getRandomEmployeeOfBank(bank), getRandomPaymentAccount(user), bank));
                }
            }
        }
    }


    private PaymentAccount getRandomPaymentAccount(User user) {
        Random random = new Random();
        return user.getPaymentAccounts().get(random.nextInt(user.getPaymentAccounts().size()));
    }

    private BankOffice getRandomOfficeOfBank(Bank bank) {
        Random random = new Random();
        return bank.getBankOfficeList().get(random.nextInt(bank.getBankOfficeList().size()));
    }

    private Employee getRandomEmployeeOfBank(Bank bank) {
        Random random = new Random();
        return bank.getEmployeeList().get(random.nextInt(bank.getEmployeeList().size()));
    }

    private String getRandomFullName() {
        String[] firstNames = {"Иван", "Михаил", "Александр", "Андрей", "Игорь", "Даниил", "Сергей", "Дмитрий", "Егор", "Владимир"};
        String[] lastNames = {"Иванов", "Петров", "Сидоров", "Смирнов", "Кузнецов", "Васильев", "Попов", "Соколов", "Михайлов", "Федоров"};

        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];

        return firstName + " " + lastName;
    }

    private String getRandomPosition() {
        return positions[random.nextInt(positions.length)];
    }

    private String getRandomWorkplace() {
        return workplaces[random.nextInt(workplaces.length)];
    }
}
