/**
 * Created by Bohumil Brázda on 16.7.2017.
 */
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.jackson.JsonNodeValueReader;


public class JsonModelMapperTest {

    private final JsonNodeValueReader valueReader = new JsonNodeValueReader();
    private ModelMapper modelMapper;

    public JsonModelMapperTest() {
    }

    public static class Foo {

        private String a;
        private ArrayList<String> b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public ArrayList<String> getB() {
            return b;
        }

        public void setB(ArrayList<String> b) {
            this.b = b;
        }
    }

    @Before
    public void setUpMethod() throws Exception {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .addValueReader(valueReader);
    }

    @Test
    public void shouldMapFromJsonNode() throws Exception {
        String json = "{\"a\": \"aaa\", \"b\": [\"ccc\"]} ";

        JsonNode node = new ObjectMapper().readTree(json);

        Foo foo = modelMapper.map(node, Foo.class);

        ArrayList<String> list = new ArrayList<String>() {{
            add("ccc");
        }};

        Assert.assertEquals(foo.getA(), "aaa");
        Assert.assertEquals(foo.getB(), list);

    }

}