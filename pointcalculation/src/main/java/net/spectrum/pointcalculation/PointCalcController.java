package net.spectrum.pointcalculation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@RestController
public class PointCalcController {

    @Resource
    private TransactionRepository transactionRepo;

    @Resource
    private TransactionService transactionService;

    @Component
    class PointCommandLineRunner implements CommandLineRunner {
        private final TransactionRepository repository;

        public PointCommandLineRunner(TransactionRepository repository) {
            this.repository = repository;
        }

        // Sample Data Insert
        @Override
        public void run (String[] strings) throws Exception{

            Transaction transaction1 = new Transaction("example1", new BigDecimal(120), LocalDate.now());
            Transaction transaction2 = new Transaction("example1", new BigDecimal(90), LocalDate.now().plusMonths(1));
            Transaction transaction3 = new Transaction("example1", new BigDecimal(300), LocalDate.now().plusMonths(2));

            transactionService.saveTransaction(transaction1);
            transactionService.saveTransaction(transaction2);
            transactionService.saveTransaction(transaction3);
        }

    }

    /*
     * Add a Transaction of a Customer
     * @RequestBody transaction transaction
     * @return Collection<RewardPointsReport>
     */
    @PostMapping(value="/customerTransaction")
    @CrossOrigin(origins = "http://localhost:3000")
    public Collection<RewardPointsReport> saveTransaction(@RequestBody Transaction transaction) {
        return  transactionService.saveTransaction(transaction);
    }

    /*
     * Get Points Report of a customer
     * @Param custid
     * @return Collection<RewardPointsReport>
     */
    @GetMapping("/totalpoint/{custid}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Collection<RewardPointsReport> getTotalPoint(@PathVariable("custid") String custid) {
        return transactionService.getTotalPoint(custid);
    }

}
