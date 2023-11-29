package hw3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class EncryptorTest {

    private final String SOURCE_FILE = "src/test/java/hw3/files/source.txt";
    private final String DESTINATION_FILE = "src/test/java/hw3/files/destination.txt";
    private final String NONEXISTENT_FILE = "src/test/java/hw3/files/nonexistent.txt";

    @BeforeEach
    void createFiles() throws IOException {
        File source = new File(SOURCE_FILE);
        File destination = new File(DESTINATION_FILE);
        if (!(source.createNewFile() & destination.createNewFile())) {
            fail("File creation failed");
        }
    }

    @AfterEach
    void deleteFiles() {
        File source = new File(SOURCE_FILE);
        File destination = new File(DESTINATION_FILE);
        File nonexistent = new File(NONEXISTENT_FILE);
        if (!(source.delete() & destination.delete()) & nonexistent.delete()) {
            fail("File deletion failed");
        }
    }

    @Test
    void EncryptionTest() {
        try (
                BufferedWriter sourceFile = new BufferedWriter(new FileWriter(SOURCE_FILE))
        ) {
            sourceFile.write("abcd");
            sourceFile.newLine();
        } catch (IOException e) {
            fail("Destination file creation failed");
        }

        try {
            Encryptor.main(new String[]{SOURCE_FILE, "1", DESTINATION_FILE});
        } catch (IOException e) {
            fail();
        }

        try (
                BufferedReader destinationFile = new BufferedReader(new FileReader(DESTINATION_FILE))
        ) {
            assertEquals("bcde", destinationFile.readLine());
        } catch (IOException e) {
            fail("Result reading failed");
        }
    }

    @Test
    void EncryptionExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> Encryptor.main(new String[1]));
        assertThrows(IllegalArgumentException.class, () -> Encryptor.main(new String[4]));
        assertThrows(IOException.class, () -> Encryptor.main(new String[] {NONEXISTENT_FILE, "1", DESTINATION_FILE}));
    }
}
