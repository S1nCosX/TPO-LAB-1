package tests.task1;

import main.task1.Cos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CosTest {
        @ParameterizedTest(name = "Cos({0})")
        @DisplayName("Check PI dots")
        @ValueSource(doubles = {-2 * Math.PI, -Math.PI, -0.5 * Math.PI, 0, 0.5 * Math.PI, Math.PI, 1.5 * Math.PI, 2 * Math.PI})
        void checkPiDots(double param) {
            assertAll(
                    () -> assertEquals(Math.cos(param), Cos.get_power_series(param, 100), 0.0001)
            );
        }

        @ParameterizedTest(name = "Cos  ({0}) = {1}")
        @DisplayName("Check between dots [-1; +1]")
        @CsvFileSource(resources = "/vals.csv", numLinesToSkip = 1, delimiter = ';')
        void checkBetweenDotsMinusPiAndPi(double x, double y) {
            assertAll(
                    () -> assertEquals(y, Cos.get_power_series(x, 100), 0.0001)
            );
        }
}
