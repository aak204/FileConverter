package ru.vyatsu;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;

class MainMethodTests {
    @Test
    void testMainWithInsufficientArgs() {
        try (MockedStatic<LoggerFactory> mocked = mockStatic(LoggerFactory.class)) {
            Logger mockLogger = mock(Logger.class);
            mocked.when(() -> LoggerFactory.getLogger(any(Class.class))).thenReturn(mockLogger);

            String[] args = {"data.json"}; // Недостаточно аргументов
            Main.main(args);

            verify(mockLogger).error(contains("Неверное количество аргументов."));
        }
    }
}
