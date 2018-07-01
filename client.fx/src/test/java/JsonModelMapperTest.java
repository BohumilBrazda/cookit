/**
 * Created by Bohumil Brázda on 16.7.2017.
 */

import ch.qos.logback.core.net.SyslogOutputStream;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

import java.io.OutputStream;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.*;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.modelmapper.jackson.JsonNodeValueReader;
import org.modelmapper.spi.MappingContext;
import org.modelmapper.spi.ValueReader;


public class JsonModelMapperTest {

    private final JsonNodeValueReader valueReader = new JsonNodeValueReader();
    private ModelMapper modelMapper;
    private ObjectMapper om;

    public JsonModelMapperTest() {
    }

    public static class Foo {

        private String a;
        private List<ComposedClass> items = new ArrayList<>();

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public List<ComposedClass> getItems() {
            return items;
        }

        public void setItems(ArrayList<ComposedClass> items) {
            this.items = items;
        }
    }

    public static class ComposedClass{
        private String a;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }
    }

    @Before
    public void setUpMethod() throws Exception {
        modelMapper = new ModelMapper();
        modelMapper.
                getConfiguration().setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR)
                .setMatchingStrategy(MatchingStrategies.LOOSE)
                .addValueReader(valueReader);

        om = new ObjectMapper();
        om.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        om.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);


    }


    @Test
    public void shouldMapFromJsonNode() throws Exception {
        //String json = "{\"a\": \"aaa\", \"items\": [\"aaaaa\"]} ";
//        ObjectMapper om = new ObjectMapper();
//        om.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        ComposedClass cc = new ComposedClass();
        cc.setA("a");

        Foo foo = new Foo();
        foo.setA("aaa");
        //foo.getB().add(cc);
        final StringWriter stringWriter = new StringWriter();
        om.writeValue(stringWriter, foo);

        JsonNode node = om.readTree(stringWriter.toString());
//        JsonNode resultArray = node.path("b");

        Condition<JsonNode, Foo> isEmptyItems = new Condition<JsonNode, Foo>() {
            @Override
            public boolean applies(MappingContext<JsonNode, Foo> context) {
                if(context.getSource().get("items").isArray() && context.getSource().get("items").size() == 0){
                    return true;
                }return false;
            }
        };
        PropertyMap<JsonNode, Foo> orderMap = new PropertyMap<JsonNode, Foo>() {
            protected void configure() {
                when(isEmptyItems).map().setItems(new ArrayList<>());
            }
        };
        modelMapper.createTypeMap(JsonNode.class, Foo.class).addMappings(orderMap);

        Foo result = modelMapper.map(node, Foo.class);
        System.out.println(result);

//        if(resultArray.size()>0){
//            JavaType listType = om.getTypeFactory().constructCollectionType(List.class, ComposedClass.class);
//            List<ComposedClass> users = om.readValue(resultArray.traverse(), listType);

//        }






    }
}