package ru.vyatsu;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import ru.vyatsu.service.ConversionException;
import ru.vyatsu.service.ConversionService;
import ru.vyatsu.service.ConversionType;
import ru.vyatsu.service.MenuUtils;

import static ru.vyatsu.service.ConversionType.*;

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
                val inputPath = args[0];
                val outputPath = args[1];
                val conversionType = determineConversionType(inputPath, outputPath);
                processConversion(inputPath, outputPath, conversionType);
            } else {
                // Интерактивный режим
                val userChoice = MenuUtils.getUserChoice();
                processConversion(MenuUtils.getInputFilePath(),
                    MenuUtils.getOutputFilePath(), ConversionType.fromInt(userChoice));
            }
        } catch (ConversionException conversionException) {
            System.err.println(String.format("Ошибка конвертации: %s", conversionException.getMessage()));
            log.error("Ошибка конвертации", conversionException);
        } catch (Exception generalException) {
            System.err.println(String.format("Необработанная ошибка: %s - %s", generalException.getClass().getSimpleName(),
                generalException.getMessage()));
            log.error("Необработанная ошибка", generalException);
        }
    }

    private static ConversionType determineConversionType(final String inputPath, final String outputPath) {
        if (ConversionService.isXMLtoJSON(inputPath, outputPath)) {
            return XML_TO_JSON;
        }
        return JSON_TO_XML;
    }

    private static void processConversion(final String inputPath, final String outputPath, final ConversionType conversionType) throws ConversionException {
        ConversionService.getInstance().convert(inputPath, outputPath, conversionType);
    }
}
