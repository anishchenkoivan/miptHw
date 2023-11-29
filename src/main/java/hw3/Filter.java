package hw3;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Filter {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Exactly 3 arguments expected");
        }
        try(
                BufferedReader sourceFile = new BufferedReader(new FileReader(args[0]));
                BufferedReader restrictedWordsFile = new BufferedReader(new FileReader(args[1]));
                BufferedWriter destinationFile = new BufferedWriter(new FileWriter(args[2]));
        ) {
            HashSet<String> restrictedWords = restrictedWordsFile.lines().collect(Collectors.toCollection(HashSet::new));
            destinationFile.write(sourceFile.lines()
                    .filter((String l) -> {
                        Set<String> line = new HashSet<>(Set.of(l.split(" ")));
                        line.retainAll(restrictedWords);
                        return line.isEmpty();
                    }).collect(Collectors.joining("\n")));
            destinationFile.newLine();
        }
    }
}
