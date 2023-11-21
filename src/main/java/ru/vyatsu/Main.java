package ru.vyatsu;

import lombok.val;
import ru.vyatsu.service.ConversionException;
import ru.vyatsu.service.ConversionService;
import ru.vyatsu.service.ConversionType;
import ru.vyatsu.service.MenuService;

import static ru.vyatsu.service.ConversionType.*;

/**
 * Главный класс приложения для преобразования данных между форматами XML и JSON.
 * Поддерживает конвертацию XML в JSON и наоборот.
 */
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
                processConversion(inputFile, outputFile, conversionType);
            } else {
                // Интерактивный режим
                val userChoice = MenuService.getUserChoice();
                val conversionType = ConversionType.fromInt(userChoice);

                val inputFile = MenuService.getInputFilePath();
                val outputFile = MenuService.getOutputFilePath();
                processConversion(inputFile, outputFile, conversionType);
            }
        } catch (Exception exception) {
            System.err.printf(exception.getMessage());
        }
    }

    private static ConversionType determineConversionType(final String inputFile,final String outputFile) {
        if (ConversionService.isXMLtoJSON(inputFile, outputFile)) {
            return XML_TO_JSON;
        }
        return JSON_TO_XML;
    }

    private static void processConversion(final String inputFile, final String outputFile, final ConversionType conversionType) throws ConversionException {
        ConversionService.getInstance().convert(inputFile, outputFile, conversionType);
    }
}
