package coffee.machine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AppTest {
    private static final PrintStream systemOut = System.out;

    private ByteArrayOutputStream sOut;

    @Before
    public void setUp() {
        sOut = new ByteArrayOutputStream();

        System.setOut(new PrintStream(sOut));
    }

    @After
    public void tearDown() {
        System.setOut(systemOut);
    }

    @Test
    public void testCoffeeMachineScenario1() {
        InputStream stream = getClass().getResourceAsStream("/test_1.json");
        System.setIn(stream);
        App.main(new String[]{});

        Set<String> output = Set.of(sOut.toString().trim().split("\n"));
        Set<String> expected = Set.of(
                "hot_coffee is prepared",
                "green_tea cannot be prepared because green_mixture is not available",
                "black_tea is prepared",
                "hot_tea cannot be prepared because sugar_syrup is not sufficient"
        );

        Set<String> expected2 = Set.of(
                "hot_tea is prepared",
                "hot_coffee is prepared",
                "green_tea cannot be prepared because green_mixture is not available",
                "black_tea cannot be prepared because item hot_water is not sufficient"
        );

        Set<String> expected3 = Set.of(
                "hot_tea is prepared",
                "black_tea is prepared",
                "green_tea cannot be prepared because green_mixture is not available",
                "hot_coffee cannot be prepared because item hot_water is not sufficient"
        );

        Set<String> expected4 = Set.of(
                "hot_coffee is prepared",
                "black_tea is prepared",
                "green_tea cannot be prepared because green_mixture is not available",
                "hot_tea cannot be prepared because item hot_water is not sufficient"
        );

        assertTrue(expected.equals(output)
                || expected2.equals(output)
                || expected3.equals(output)
                || expected4.equals(output));
    }

    @Test
    public void testCoffeeMachineScenario2() {
        System.setIn(getClass().getResourceAsStream("/test_2.json"));
        App.main(new String[]{});

        Set<String> output = Set.of(sOut.toString().trim().split("\n"));

        Set<String> expected = Set.of(
                "green_tea is prepared",
                "hot_coffee is prepared",
                "hot_tea is prepared",
                "black_tea is prepared"
        );

        assertEquals(expected, output);
    }

    @Test
    public void testCoffeeMachineScenario3() {
        System.setIn(getClass().getResourceAsStream("/test_3.json"));
        App.main(new String[]{});

        Set<String> output = Set.of(sOut.toString().trim().split("\n"));

        Set<String> expected1 = Set.of(
                "espresso_strong cannot be prepared because coffee is not sufficient",
                "espresso_extreme cannot be prepared because coffee is not sufficient",
                "hot_tea cannot be prepared because tea_leaves_syrup is not available",
                "espresso is prepared",
                "hot_coffee cannot be prepared because coffee is not sufficient",
                "black_tea cannot be prepared because tea_leaves_syrup is not available"
        );

        Set<String> expected2 = Set.of(
                "espresso_strong cannot be prepared because coffee is not sufficient",
                "espresso_extreme cannot be prepared because coffee is not sufficient",
                "hot_tea cannot be prepared because tea_leaves_syrup is not available",
                "hot_coffee is prepared",
                "espresso cannot be prepared because coffee is not sufficient",
                "black_tea cannot be prepared because tea_leaves_syrup is not available"
        );

        assertTrue(expected1.equals(output) || expected2.equals(output));
    }
}
