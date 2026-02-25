package com.example.reward.service;

import com.example.reward.entity.Transaction;
import com.example.reward.entity.User;
import com.example.reward.repository.TransactionRepository;
import com.example.reward.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

	@InjectMocks
	private TransactionService transactionService;

	@Mock
	private TransactionRepository transactionRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private RewardService rewardService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void addTransaction_shouldSaveTransactionWithCorrectRewardPoints() {
		Long userId = 1L;
		Double amount = 120.0;

		User user = new User();
		user.setId(userId);
		user.setUsername("john");
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));
		when(rewardService.calculatePoints(amount)).thenReturn(90);

		Transaction savedTransaction = new Transaction();
		savedTransaction.setId(1L);
		savedTransaction.setAmount(amount);
		savedTransaction.setRewardPoints(90);
		savedTransaction.setTransactionDate(LocalDate.now());
		savedTransaction.setUser(user);

		when(transactionRepository.save(any(Transaction.class))).thenReturn(savedTransaction);

		Transaction result = transactionService.addTransaction(userId, amount);

		// Verify
		assertNotNull(result);
		assertEquals(amount, result.getAmount());
		assertEquals(90, result.getRewardPoints());
		assertEquals(user, result.getUser());

		verify(userRepository, times(1)).findById(userId);
		verify(rewardService, times(1)).calculatePoints(amount);
		verify(transactionRepository, times(1)).save(any(Transaction.class));
	}

	@Test
	void addTransaction_shouldThrowExceptionWhenUserNotFound() {
		Long userId = 999L;
		Double amount = 50.0;

		when(userRepository.findById(userId)).thenReturn(Optional.empty());

		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> transactionService.addTransaction(userId, amount));

		assertEquals("User not found", exception.getMessage());

		verify(userRepository, times(1)).findById(userId);
		verifyNoInteractions(rewardService);
		verify(transactionRepository, never()).save(any());
	}
}
