package ru.vyatsu;

import lombok.val;
import ru.vyatsu.service.ConversionException;
import ru.vyatsu.service.ConversionService;
import ru.vyatsu.service.ConversionType;
import ru.vyatsu.service.MenuService;
import lombok.extern.slf4j.Slf4j;

/**
 * Главный класс приложения для преобразования данных между форматами XML и JSON.
 * Поддерживает конвертацию XML в JSON и наоборот.
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        try {
            if (args.length != 2 && args.length != 0) {
                System.err.println("Неверное количество аргументов. Для ручного режима не указывайте аргументы, для автоматического используйте 2 аргумента.");
                return;
            }

            if (args.length == 2) {
                // Запуск с аргументами командной строки
                val inputFile = args[0];
                val outputFile = args[1];

                val conversionType = determineConversionType(inputFile, outputFile);
                if (conversionType == ConversionType.INVALID) {
                    System.err.println("Неподдерживаемый формат или комбинация файлов.");
                    return;
                }
                processConversion(inputFile, outputFile, conversionType);
            } else {
                // Интерактивный режим
                val userChoice = MenuService.getUserChoice();
                val conversionType = ConversionType.fromInt(userChoice);
                if (conversionType == ConversionType.INVALID) {
                    System.err.println("Неверный выбор операции или ошибка ввода.");
                    return;
                }

                val inputFile = MenuService.getInputFilePath();
                val outputFile = MenuService.getOutputFilePath();
                processConversion(inputFile, outputFile, conversionType);
            }
        } catch (Exception exception) {
            log.error("Произошла ошибка: {}", exception.getMessage());
        }
    }

    private static ConversionType determineConversionType(String inputFile, String outputFile) {
        if (ConversionService.isXMLtoJSON(inputFile, outputFile)) {
            return ConversionType.XML_TO_JSON;
        }
        if (ConversionService.isJSONtoXML(inputFile, outputFile)) {
            return ConversionType.JSON_TO_XML;
        }
        return ConversionType.INVALID;
    }

    private static void processConversion(String inputFile, String outputFile, ConversionType conversionType) throws ConversionException {
        ConversionService.convert(inputFile, outputFile, conversionType);
    }
}
