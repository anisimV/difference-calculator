package practice.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;


import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DifferTest {

    private Map<String, Object> parseJson(String filename) throws Exception {
        // загружаем файл как InputStream из classpath (папки resources)
        InputStream is = getClass().getClassLoader().getResourceAsStream(filename);

        // проверяем, что файл был действительно найденю Иначе тест упадет с пояснением
        assertNotNull(is, "Файл: " + filename + " не найден");

        // читаем содержимое файла как строку с годировкой UTF-8
        String content = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        // создаем Jackson ObjectMapper для преобразование JSON в Map
        ObjectMapper mapper = new ObjectMapper();

        // преобразуем JSON-строку в Map и возвращаем
        return mapper.readValue(content, Map.class);
    }

    @Test
    void estFlatJsonDiff() throws Exception {
        // Загружаем и парсим два JSON-файла из ресурсов
        Map<String, Object> data1 = parseJson("file1.json");
        Map<String, Object> data2 = parseJson("file2.json");

        // Ожидаемый результат работы метода generate — строка с разницей между двумя Map
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  - timeout: 20
                  - verbose: true
                }""";

        // Вызываем метод generate() и получаем фактический результат
        // Метод trim() удаляет пробелы и переносы по краям
        String actual = Differ.generate(data1, data2).trim();

        // Проверяем, совпадает ли ожидаемый и фактический результат
        // Если нет — тест упадёт и покажет расхождение
        assertEquals(expected, actual);
    }
}
