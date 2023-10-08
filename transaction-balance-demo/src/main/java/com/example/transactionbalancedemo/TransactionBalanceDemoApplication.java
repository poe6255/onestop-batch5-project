package com.example.transactionbalancedemo;

import com.example.transactionbalancedemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransactionBalanceDemoApplication implements CommandLineRunner {

    @Autowired  private AccountService accountService;
    public static void main(String[] args) {
        SpringApplication.run(TransactionBalanceDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String counter="";
        try{
            accountService.transfer("John","Mary",1300);
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Caught!");
        }

        accountService.accountList().forEach(System.out::println);

       /* System.out.println("John Balance Before::" + accountService.getBalance("John"));
        accountService.deposit("John",7000);
        System.out.println("John Balance After::" + accountService.getBalance("John"));
*/
       /* do{
            System.out.println("1.Withdraw");
            System.out.println("2.Deposit");
            System.out.println("3.Transfer");
            System.out.println("exit");
            System.out.print("Enter number what you want to perform:");
            int doAction = 0;
            switch (doAction){
                case 1 -> {

                }
                case 2 -> {

                }
                case 3 -> {

                }

            }

        }while(!counter.equals("exit"));

        */
    }
}
