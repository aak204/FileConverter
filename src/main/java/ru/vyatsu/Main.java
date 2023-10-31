package ru.vyatsu;

import ru.vyatsu.service.ConversionService;
import ru.vyatsu.service.ConversionType;
import ru.vyatsu.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Главный класс приложения для преобразования данных между форматами XML и JSON.
 * Поддерживает конвертацию XML в JSON и наоборот.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            if (args.length == 2) {
                // Запуск с аргументами командной строки
                String inputFile = args[0];
                String outputFile = args[1];

                ConversionType conversionType = determineConversionType(inputFile, outputFile);
                if (conversionType == ConversionType.INVALID) {
                    logger.error("Неподдерживаемый формат или комбинация файлов.");
                    return;
                }
                processConversion(inputFile, outputFile, conversionType);
            } else if (args.length == 0) {
                // Интерактивный режим
                int userChoice = MenuService.getUserChoice();
                ConversionType conversionType = ConversionType.fromInt(userChoice);
                if (conversionType == ConversionType.INVALID) {
                    logger.error("Неверный выбор операции или ошибка ввода.");
                    return;
                }

                String inputFile = MenuService.getInputFilePath();
                String outputFile = MenuService.getOutputFilePath();
                processConversion(inputFile, outputFile, conversionType);
            } else {
                // Ошибка в количестве аргументов
                logger.error("Неверное количество аргументов. Для ручного режима не указывайте аргументы, для автоматического используйте 2 аргумента.");
            }
        } catch (Exception exception) {
            logger.error("Произошла ошибка: {}", exception.getMessage());
        }
    }

    private static ConversionType determineConversionType(String inputFile, String outputFile) {
        if (ConversionService.isXMLtoJSON(inputFile, outputFile)) {
            return ConversionType.XML_TO_JSON;
        } else if (ConversionService.isJSONtoXML(inputFile, outputFile)) {
            return ConversionType.JSON_TO_XML;
        } else {
            return ConversionType.INVALID;
        }
    }

    private static void processConversion(String inputFile, String outputFile, ConversionType conversionType) {
        if (conversionType == ConversionType.INVALID) {
            logger.error("Неверный выбор операции.");
            return;
        }
        ConversionService.convert(inputFile, outputFile, conversionType);
    }
}
