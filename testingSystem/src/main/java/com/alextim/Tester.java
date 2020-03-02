package com.alextim;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Tester {

    private String jarFile;
    private final String key;
    private List<List<String>> inputData;
    private List<List<String>> outputData;

    public Tester(String[] args) throws IOException {
        String path = args[0];
        this.key = args[1];
        this.inputData = getDataFromFiles(path, "in");
        this.outputData = getDataFromFiles(path, "out");
        this.jarFile = findJarFile(path).orElseThrow(()-> new RuntimeException("Jar file not found")).toString();
    }

    public String run() {
        StringBuilder report = new StringBuilder();
        for(int i=0; i<inputData.size(); i++) {
            try {
                System.out.print("Test " + (i+1));
                List<String> expected = outputData.get(i);
                List<String> actual = externalProcessStart( key, inputData.get(i));
                report.append(createRecordForReport(i, expected, actual));
                System.out.println( " done");
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return report.toString();
    }

    private String createRecordForReport(int number, List<String> expected, List<String> actual){
        return String.valueOf(number + 1) + ") " +
                " Actual: " + actual +
                " Expected: " + expected +
                " Result: " + expected.equals(actual) + "\n";
    }

    private List<String> externalProcessStart(String key, List<String> argIn) throws IOException {
        List<String> actual = new ArrayList<>();

        StringBuilder builder = new StringBuilder();
        builder.append("java -jar ").append(jarFile);
        builder.append(" ").append(key);
        argIn.forEach(s -> builder.append(" ").append(s));

        Process process = new ProcessBuilder((builder.toString()).split(" ")).start();

        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while((line = reader.readLine()) !=  null){
            actual.add(line);
        }
        return actual;
    }

    private Optional<Path> findJarFile(String pathToDirectory) throws IOException {
        return Files.walk(Paths.get(pathToDirectory))
                .filter(p ->Files.isRegularFile(p) && p.getFileName().toString().endsWith(".jar"))
                .findFirst();
    }

    private List<List<String>> getDataFromFiles(String pathToDirectory, String type) throws IOException {
        return Files.walk(Paths.get(pathToDirectory))
                .filter(p -> Files.isRegularFile(p) && p.getFileName().toString().endsWith(type))
                .sorted((o1, o2) -> {
                    int number1 = Integer.parseInt(o1.getFileName().toString().replaceAll("[\\D]", ""));
                    int number2 = Integer.parseInt(o2.getFileName().toString().replaceAll("[\\D]", ""));
                    return number1 - number2;
                })
                .map(path -> {
                    try {
                        return Files.readAllLines(path);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }
}
