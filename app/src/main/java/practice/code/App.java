package practice.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

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
        System.out.println("File 1: " + filepath1);
        System.out.println("File 2: " + filepath2);
        System.out.println("Format: " + format);
    }


    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
