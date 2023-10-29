package ru.vyatsu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuService {
    private static final Logger logger = LoggerFactory.getLogger(MenuService.class);
    private final Scanner scanner;

    public MenuService() {
        this.scanner = new Scanner(System.in);
    }

    public int getUserChoice() {
        logger.info("Выберите операцию:");
        logger.info("1. XML в JSON");
        logger.info("2. JSON в XML");
        try {
            int choice = scanner.nextInt();
            // Проверяем, что выбор пользователя в пределах допустимых значений
            if (choice < 1 || choice > 2) {
                logger.error("Неверный выбор: {}. Пожалуйста, выберите 1 или 2", choice);
                return -1;
            }
            return choice;
        } catch (InputMismatchException e) {
            logger.error("Введено некорректное значение. Пожалуйста, введите число.");
            scanner.nextLine(); // Очищаем сканер
            return -1;
        }
    }

    public String getInputFilePath() {
        logger.info("Введите путь к входному файлу:");
        return scanner.next();
    }

    public String getOutputFilePath() {
        logger.info("Введите путь к выходному файлу:");
        return scanner.next();
    }
}