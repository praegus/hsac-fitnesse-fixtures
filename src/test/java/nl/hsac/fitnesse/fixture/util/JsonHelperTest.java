package nl.hsac.fitnesse.fixture.util;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class JsonHelperTest {
    private JsonHelper helper = new JsonHelper();

    @Test
    public void testFormatNull() {
        assertNull(helper.format(null));
    }

    @Test
    public void testFormatSimple() {
        assertEquals(
                "{\n" +
                        "    \"category\": \"reference\",\n" +
                        "    \"price\": 8.95\n" +
                        "}",
                helper.format("{\"category\": \"reference\",\"price\": 8.95}"));
    }

    @Test
    public void testNullToMap() {
        assertNull(helper.jsonStringToMap(null));
    }

    @Test
    public void testSimpleMap() {
        Map<String, Object> expected = new LinkedHashMap<String, Object>();
        expected.put("category", "reference");
        expected.put("price", 8.95);
        assertEquals(expected,
                helper.jsonStringToMap("{\"category\": \"reference\",\"price\": 8.95}"));
    }

    @Test
    public void testComplexMap() {
        Map<String, Object> mobileEmulation = new LinkedHashMap<String, Object>();
        mobileEmulation.put("deviceName", "Google Nexus 5");

        Map<String, Object> chromeOptions = new LinkedHashMap<String, Object>();
        chromeOptions.put("mobileEmulation", mobileEmulation);

        Map<String, Object> expected = new LinkedHashMap<String, Object>();
        expected.put("browserName", "chrome");
        expected.put("chromeOptions", chromeOptions);

        assertEquals(expected,
                helper.jsonStringToMap("{\"browserName\":\"chrome\",\"chromeOptions\":{\"mobileEmulation\":{\"deviceName\":\"Google Nexus 5\"}}}"));
    }
}
