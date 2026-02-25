package com.example.reward.service;

import com.example.reward.entity.User;
import com.example.reward.repository.UserRepository;
import com.example.reward.security.JwtUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() {

    	User user = new User();
		user.setId(1L);
		user.setUsername("john");
		user.setPassword("password");

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> {
                    User u = invocation.getArgument(0);
                    u.setId(1L); // simulate DB assigning ID
                    return u;
                });

        User savedUser = userService.register(user);

        assertNotNull(savedUser);
        assertEquals(1L, savedUser.getId());
        assertEquals("john", savedUser.getUsername());
        assertEquals("encodedPassword", savedUser.getPassword());

        verify(passwordEncoder, times(1)).encode("password");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testLoginUser_Failure_WrongPassword() {

        String username = "john";
        String rawPassword = "wrong";
        String encodedPassword = "encodedPassword";

        User user = new User();
		user.setId(1L);
		user.setUsername(username);
		user.setPassword(rawPassword);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> userService.login(username, rawPassword));

        assertEquals("Invalid credentials", exception.getMessage());

        verify(userRepository, times(1)).findByUsername(username);
        verify(jwtUtil, never()).generateToken(any());
    }

    
    @Test
    void testLoginUser_Failure_UserNotFound() {

        String username = "unknown";
        String rawPassword = "password";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> userService.login(username, rawPassword));

        assertEquals("User not found", exception.getMessage());

        verify(userRepository, times(1)).findByUsername(username);
        verify(passwordEncoder, never()).matches(any(), any());
        verify(jwtUtil, never()).generateToken(any());
    }
}