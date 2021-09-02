package org.benhallam;

import org.benhallam.extentions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;

import static org.benhallam.extentions.MyParameterResolver.VALUE_INJECTED_BY_PARAMETER_RESOLVER_EXTENSION;

/**
 * PER_CLASS => A single instance will be created for executing all tests
 * PER_METHOD => For each test, a new instance will be created
 * <p>
 * The default behaviour is PER_METHOD
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith({MyTestInstancePostProcessor.class, MyCallbacks.class, MyTestExecutionExceptionHandler.class})
class TranslatorTest {
    Translator translator;

    TranslatorTest() {
        System.out.println(">>>>>>>> Constructor");
    }

    @BeforeAll
    static void init() {
        System.out.println(">> @BeforeAll");
    }

    @AfterAll
    static void destroy() {
        System.out.println(">> @AfterAll");
    }

    @BeforeEach
    void setUp() {
        translator = new Translator("fr");
        System.out.println(">> @BeforeEach");
    }

    @AfterEach
    void tearDown() {
        System.out.println(">> @AfterEach");
    }

    /**
     * @EnabledOnOs
     */
    @Test
    @EnabledOnOs(value = OS.WINDOWS, disabledReason = "A message specifying why")
    void enabledOnOs() {
        Assertions.assertEquals("salut", translator.translate("hello"));
    }


    /**
     * Repeated Test
     */
    @RepeatedTest(value = 3,
            name = RepeatedTest.CURRENT_REPETITION_PLACEHOLDER + "/" + RepeatedTest.TOTAL_REPETITIONS_PLACEHOLDER)
    void repeatedTest() {
        Assertions.assertEquals("hola", translator.translate("es", "hello"));
    }


    /**
     * Assumptions : This test will not fail, instead it will be skipped due to the assumption
     */
    @Test
    void falseAssumption() {
        Assumptions.assumeFalse(true);
        Assertions.fail("This test will not fail, instead it will be skipped due to the instruction before");
    }

    /**
     * Parametrized test (ValueSource)
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void parametrizedValueSource(int n) {
        Assertions.assertEquals(n * n, Math.pow(n, 2));
    }

    /**
     * Parametrized test (CsvSource)
     */
    @ParameterizedTest
    @CsvSource(value = {
            "1, 1",
            "2, 4",
            "3 , 9",
            "  5  ,  16 "})
    void parametrizedCsvSource(int n) {
        System.err.println(n);
        Assertions.assertEquals(n * n, Math.pow(n, 2));
    }

    /**
     * Parametrized test (CsvFileSource)
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/csvFile.csv")
    void parametrizedCsvFileSource(String word, String lang, String translatedWord) {
        Assertions.assertEquals(translatedWord, translator.translate(lang, word));
    }

    /**
     * Parametrized test (MethodSource)
     */
    @ParameterizedTest
    @MethodSource("theNameOfTheMethod")
    void parametrizedMethodSource(String word, String translatedWord, String lang) {
        Assertions.assertEquals(translatedWord, translator.translate(lang, word));
    }

    private static List<Arguments> theNameOfTheMethod() {
        return List.of(
                Arguments.of("hello", "hola", "es"),
                Arguments.arguments("morning", "mañana", "es"),
                Arguments.arguments("night", "noche", "es")
        );
    }

    /**
     * Disabled test
     */
    @Test
    @Disabled
    void disabled() {
        Assertions.fail();
    }

    /**
     * ExecutionCondition Extension
     */
    @Test
    @ExtendWith(MyExecutionCondition.class)
    void disabledByExecutionCondition() {

    }

    /**
     * ParameterResolver Extension
     */
    @Test
    @ExtendWith(MyParameterResolver.class)
    void parameterResolverExtension(String value, String value2) {
        Assertions.assertEquals(VALUE_INJECTED_BY_PARAMETER_RESOLVER_EXTENSION, value);
        Assertions.assertEquals(VALUE_INJECTED_BY_PARAMETER_RESOLVER_EXTENSION, value2);
        System.out.println(value2);
    }

    /**
     * TestExecutionExceptionHandler Extension
     */
    @Test
//    @ExtendWith(MyTestExecutionExceptionHandler.class)
    void testExecutionExceptionHandler() {
        throw new MyCustomException();
    }


}