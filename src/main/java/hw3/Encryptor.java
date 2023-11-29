package hw3;

import java.io.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Encryptor {

    @FunctionalInterface
    interface CaesarEncryptor extends Function<String, String> {

        @Override
        String apply(String s);
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Exactly 3 arguments expected");
        }
        try (
                BufferedReader sourceFile = new BufferedReader(new FileReader(args[0]));
                BufferedWriter destinationFile = new BufferedWriter(new FileWriter(args[2]));
        ) {
            int key = Integer.parseInt(args[1]);
            CaesarEncryptor caesarEncryptor = line -> {
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < line.length(); i++) {
                    result.append((char)((line.charAt(i) + key) % Character.MAX_VALUE));
                }
                return result.toString();
            };
            destinationFile.write(sourceFile.lines()
                    .map(caesarEncryptor).collect(Collectors.joining("\n"))
            );
            destinationFile.newLine();
        }
    }
}
