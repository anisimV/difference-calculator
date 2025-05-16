package practice.code;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    // метод для генерации строки с различиями между двумя JSON-картами
    public static String generate(Map<String, Object> data1, Map<String, Object> data2) {
        // собираем все ключи из двух словарей и сортирруем по алфавиту
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(data1.keySet());
        allKeys.addAll(data2.keySet());

        StringBuilder result = new StringBuilder();
        result.append("{\n");

        // проходим по каждому ключу

        for (String key : allKeys) {
            boolean hasKey1 = data1.containsKey(key);
            boolean hasKey2 = data2.containsKey(key);
            Object val1 = data1.get(key);
            Object val2 = data2.get(key);

            // если ключ есть в обоих файлах
            if (hasKey1 && hasKey2) {
                if (Objects.equals(val1, val2)) {
                    // значит одинаковые
                    result.append("    ").append(key).append(": ").append(val1).append("\n");
                } else {
                    result.append("  - ").append(key).append(": ").append(val1).append("\n");
                    result.append("  - ").append(key).append(": ").append(val2).append("\n");
                }
            } else if (hasKey1) {
                // ключ только в пермов файле (удален)
                result.append("  - ").append(key).append(": ").append(val1).append("\n");
            } else {
                // ключ только во втором файле (добавлен)
                result.append("  - ").append(key).append(": ").append(val2).append("\n");
            }
        }
        result.append("}");
        return result.toString();
    }
}
