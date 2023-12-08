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
                val inputPath = args[0];
                val outputPath = args[1];

                val conversionType = determineConversionType(inputPath, outputPath);
                processConversion(inputPath, outputPath, conversionType);
            } else {
                // Интерактивный режим
                val userChoice = MenuService.getUserChoice();
                val conversionType = ConversionType.fromInt(userChoice);

                val inputPath = MenuService.getInputFilePath();
                val outputPath = MenuService.getOutputFilePath();
                processConversion(inputPath, outputPath, conversionType);
            }
        } catch (Exception exception) {
            System.err.println(String.format("Произошла ошибка: %s - %s", exception.getClass().getSimpleName(), exception.getMessage()));
        }
    }

    private static ConversionType determineConversionType(final String inputPath,final String outputPath) {
        if (ConversionService.isXMLtoJSON(inputPath, outputPath)) {
            return XML_TO_JSON;
        }
        return JSON_TO_XML;
    }

    private static void processConversion(final String inputPath, final String outputPath, final ConversionType conversionType) throws ConversionException {
        ConversionService.getInstance().convert(inputPath, outputPath, conversionType);
    }
}
