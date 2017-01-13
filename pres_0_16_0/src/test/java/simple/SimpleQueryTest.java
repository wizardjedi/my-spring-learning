package simple;

import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class SimpleQueryTest {

    @Test
    public void testProcessData() throws Exception {
        SimpleQuery sq = new SimpleQuery();

        List<Map<String, String>> result = sq.processData();

        Assert.assertNotNull(result);
        Assert.assertEquals(10, result.size());

        Assert.assertEquals("21", result.get(0).get("id"));
        Assert.assertEquals("25", result.get(4).get("id"));
        Assert.assertEquals("30", result.get(9).get("id"));

        Map<String, String> sampleRow = result.get(0);

        Assert.assertEquals(5, sampleRow.size());

        Assert.assertTrue(sampleRow.containsKey("id"));
        Assert.assertTrue(sampleRow.containsKey("name"));
        Assert.assertTrue(sampleRow.containsKey("surname"));
        Assert.assertTrue(sampleRow.containsKey("department_id"));
        Assert.assertTrue(sampleRow.containsKey("create_date"));
    }

}
