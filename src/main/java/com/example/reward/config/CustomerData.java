package com.example.reward.config;

import com.example.reward.model.Transaction;
import com.example.reward.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class CustomerData {

    @Bean
    CommandLineRunner loadData(TransactionRepository repository) {
        return args -> {

            repository.save(new Transaction(null, "Kiran", 120.0, LocalDate.of(2025, 12, 5)));
            repository.save(new Transaction(null, "Kiran", 75.0, LocalDate.of(2025, 12, 15)));
            repository.save(new Transaction(null, "Kiran", 200.0, LocalDate.of(2026, 1, 10)));
            repository.save(new Transaction(null, "Kiran", 90.0, LocalDate.of(2026, 2, 1)));
            
            repository.save(new Transaction(null, "Jyoti", 100.0, LocalDate.of(2025, 2, 5)));
            repository.save(new Transaction(null, "Jyoti", 45.0, LocalDate.of(2025, 1, 15)));
            repository.save(new Transaction(null, "Jyoti", 210.0, LocalDate.of(2026, 1, 10)));
            repository.save(new Transaction(null, "Jyoti", .86, LocalDate.of(2026, 2, 1)));
        };
    }
}
