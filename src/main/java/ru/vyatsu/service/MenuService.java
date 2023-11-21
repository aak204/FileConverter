package ru.vyatsu.service;

import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import lombok.val;
import java.util.InputMismatchException;
import java.util.Scanner;

@UtilityClass
@FieldDefaults(makeFinal = true)
public final class MenuService {
    Scanner scanner = new Scanner(System.in);
    public int getUserChoice() {
        while (true) {
            System.out.println("Выберите операцию:");
            System.out.println("1. XML в JSON");
            System.out.println("2. JSON в XML");
            try {
                val choice = scanner.nextInt();
                if (choice >= 1 && choice <= 2) {
                    return choice;
                }
                System.out.printf("Неверный выбор: %s. Пожалуйста, выберите 1 или 2%n", choice);
            } catch (InputMismatchException invalidInputException) {
                System.err.println("Введено некорректное значение. Пожалуйста, введите число.");
                scanner.nextLine(); // Очищаем сканер
            }
        }
    }

    public String getInputFilePath() {
        System.out.println("Введите путь к входному файлу:");
        scanner.nextLine(); // Читаем остаток предыдущей строки (если есть)
        return scanner.nextLine();
    }

    public String getOutputFilePath() {
        System.out.println("Введите путь к выходному файлу:");
        return scanner.nextLine();
    }
}
