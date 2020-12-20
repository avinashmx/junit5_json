import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;

public class JSONStaticTest {
    @ParameterizedTest
    @JSONLiteralSource(valueSauce = "{\"names\":[{\"name\":\"God\"},{\"name\":\"Dog\"}]}")
    public void testName(ArgumentsAccessor argumentsAccessor) {
        System.out.println("Asserting on " + argumentsAccessor.getString(0));
        Assertions.assertTrue(argumentsAccessor.getString(0).length() == 3);
    }

}
