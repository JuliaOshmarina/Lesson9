package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import tests.model.JsonFile;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonTest {
    private ClassLoader cl = JsonTest.class.getClassLoader();
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void jsonFileTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("Example.json");
             InputStreamReader zf = new InputStreamReader(is)) {
                JsonFile info = objectMapper.readValue(zf, JsonFile.class);
                assertThat(info.getName()).isEqualTo("Madame Uppercut");
                assertThat(info.getAge()).isEqualTo("39");
                assertThat(info.getSecretIdentity()).isEqualTo("Jane Wilson");
                assertThat(info.getPowers()).isEqualTo(List.of("Million tonne punch", "Damage resistance", "Superhuman reflexes"));
            }
        }
        }
