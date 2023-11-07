package ru.vyatsu.service.structure;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.*;

/**
 * Класс для представления гаража с автомобилями в формате XML.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GarageXML {
    @JacksonXmlElementWrapper(useWrapping = false) // Без обертки
    @JacksonXmlProperty(localName = "car")
    private List<CarXML> cars; // Список автомобилей в гараже
}
