import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class JSONArgumentSource implements ArgumentsProvider, AnnotationConsumer<JSONLiteralSource> {

    private String variableSauce;

    @Override
    public void accept(JSONLiteralSource JSONLiteralSource) {
        variableSauce = JSONLiteralSource.valueSauce();
    }

    @Override
    public Stream<Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        Map<String, String> myMap;
        ObjectMapper mapper = new ObjectMapper();
        List<Arguments> nameArguments = new ArrayList<>();
        mapper.readTree(variableSauce).path("names").elements().forEachRemaining(s -> nameArguments.add(Arguments.of(s.get("name").textValue())));
        return nameArguments.stream();
    }
}
