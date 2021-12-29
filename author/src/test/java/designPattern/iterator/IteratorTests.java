package designPattern.iterator;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IteratorTests {

    @Test
    public void test_with_iterator() throws Exception {
        List<Double> remuneration = Arrays
                .asList(2500, 3400, 10042, 5700, 800)
                .stream().map(e -> e * 1.2)
                .collect(Collectors.toList());

        Double result1 = remuneration.stream().mapToDouble(e -> e).average().orElse(0.0);
        Double result2 = remuneration.stream().mapToDouble(e -> e).sum();
        long result3 = remuneration.size();
        Assert.assertTrue(result1 == result2/result3);   //  ??????
    }

    @Test
    public void test_without_iterator() throws Exception {
        List<Double> input = Arrays.asList(2500.0, 3400.0, 10042.0, 5700.0, 800.0);
        List<Double> remuneration = new ArrayList<>();
        for (int i = 0; i<input.size(); i++){
            remuneration.add(input.get(i) * 1.2);
        }

        Double result1 = 0.0;
        for (int i = 0; i<input.size(); i++){
            result1 += remuneration.get(i);
        }
        result1 = result1/remuneration.size();

        Double result2 = 0.0;
        for (int i = 0; i<input.size(); i++){
            result2 += remuneration.get(i);
        }

        long result3 = remuneration.size();
        Assert.assertTrue(result1 == result2/result3);   //  ??????
    }

}
