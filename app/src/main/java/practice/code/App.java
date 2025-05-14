package practice.code;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.ls.LSOutput;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference."
)

public class App implements Runnable {

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1; // переменная для хранения пути к первому файлу

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filepath2; // переменная для хранения пути ко второму файлу

    // Опция -f или --format задаёт формат вывода результата.
    // description — описание для справки.
    // Значение по умолчанию — "stylish".
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";

    // Метод run() вызывается, когда пользователь запускает команду без -h или -V.
    // Здесь будет основная логика программы.
    @Override
    public void run() {
        try {
            Map<String, Object> data1 = parseJson(filepath1);
            Map<String, Object> data2 = parseJson(filepath2);

            System.out.println("Parsed data from file 1:");
            System.out.println(data1);
            System.out.println("Parsed data from file 2:");
            System.out.println(data2);
        } catch (Exception e) {
            System.err.println("Ошибка при чтении или паринге файлов: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Map<String, Object> parseJson(String resorceName) throws Exception {
        InputStream is = getClass().getClassLoader().getResourceAsStream(resorceName);
        if (is == null) {
            throw new FileNotFoundException("Файл " + resorceName + " не найден в resources");
        }
        String content = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Map.class);

    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
