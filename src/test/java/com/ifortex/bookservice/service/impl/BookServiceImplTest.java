package com.ifortex.bookservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ifortex.bookservice.config.TestContainersConfig;
import com.ifortex.bookservice.repository.impl.BookRepositoryImpl;

@SpringBootTest
public class BookServiceImplTest extends TestContainersConfig {

    @Autowired
    BookRepositoryImpl bookRepository;

    @Test
    public void testGetBooks_Success() {

        Map<String, Long> result = bookRepository.countBooksByGenre();
        assertEquals(16, result.size());
    }

}
