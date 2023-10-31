package ru.vyatsu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class MenuService {
    private static final Logger logger = LoggerFactory.getLogger(MenuService.class);
    private static final Scanner scanner = new Scanner(System.in);

    // Запрет на создание экземпляров
    private MenuService() {
    }

    public static int getUserChoice() {
        while (true) {
            logger.info("Выберите операцию:");
            logger.info("1. XML в JSON");
            logger.info("2. JSON в XML");
            try {
                int choice = scanner.nextInt();
                if (choice >= 1 && choice <= 2) {
                    return choice;
                } else {
                    logger.error("Неверный выбор: {}. Пожалуйста, выберите 1 или 2", choice);
                }
            } catch (InputMismatchException e) {
                logger.error("Введено некорректное значение. Пожалуйста, введите число.");
                scanner.nextLine(); // Очищаем сканер
            }
        }
    }

    public static String getInputFilePath() {
        logger.info("Введите путь к входному файлу:");
        scanner.nextLine(); // Читаем остаток предыдущей строки (если есть)
        return scanner.nextLine();
    }

    public static String getOutputFilePath() {
        logger.info("Введите путь к выходному файлу:");
        return scanner.nextLine();
    }
}