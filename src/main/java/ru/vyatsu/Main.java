package ru.vyatsu;

import ru.vyatsu.service.ConversionService;
import ru.vyatsu.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ru.vyatsu.service.ConversionService.processConversion;

/**
 * Главный класс приложения для преобразования данных между форматами XML и JSON.
 * Поддерживает конвертацию XML в JSON и наоборот.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        MenuService menuService = new MenuService();
        ConversionService conversionService = new ConversionService();

        try {
            if (args.length >= 2) {
                // Запуск с аргументами командной строки
                String inputFile = args[0];
                String outputFile = args[1];
                int choice;
                if (conversionService.isXMLtoJSON(inputFile, outputFile)) {
                    choice = 1;
                } else if (conversionService.isJSONtoXML(inputFile, outputFile)) {
                    choice = 2;
                } else {
                    logger.error("Неподдерживаемый формат или комбинация файлов.");
                    return;
                }
                processConversion(inputFile, outputFile, choice, conversionService);
            } else {
                // Интерактивный режим
                int choice = menuService.getUserChoice();
                if (choice == -1) {
                    logger.error("Неверный выбор операции или ошибка ввода.");
                    return;
                }

                String inputFile = menuService.getInputFilePath();
                String outputFile = menuService.getOutputFilePath();
                processConversion(inputFile, outputFile, choice, conversionService);
            }
        } catch (Exception e) {
            logger.error("Произошла ошибка: {}", e.getMessage());
        }
    }
}
