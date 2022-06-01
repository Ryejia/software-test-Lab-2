import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import static java.lang.Double.*;

// variant: 41224
class FunctionTest {

    static double functionEps = 0.1;
    double eps = 0.1;

    static Csc csc_Mock;
    static Cos cos_Mock;
    static Sin sin_Mock;
    static Ln ln_Mock;
    static Log log_Mock;
    static Tan tan_Mock;

    static Reader csc_In;
    static Reader cos_In;
    static Reader sin_In;
    static Reader ln_In;
    static Reader log2_In;
    static Reader log5_In;
    static Reader tan_In;


    @BeforeAll
    static void init() {
        csc_Mock = Mockito.mock(Csc.class);
        cos_Mock = Mockito.mock(Cos.class);
        sin_Mock = Mockito.mock(Sin.class);
        tan_Mock = Mockito.mock(Tan.class);
        ln_Mock = Mockito.mock(Ln.class);
        log_Mock = Mockito.mock(Log.class);
        try {
            csc_In = new FileReader("src/test/resources/CsvFiles/Inputs/CscIn.csv");
            cos_In = new FileReader("src/test/resources/CsvFiles/Inputs/CosIn.csv");
            sin_In = new FileReader("src/test/resources/CsvFiles/Inputs/SinIn.csv");
            ln_In = new FileReader("src/test/resources/CsvFiles/Inputs/LnIn.csv");
            log2_In = new FileReader("src/test/resources/CsvFiles/Inputs/Log2In.csv");
            log5_In = new FileReader("src/test/resources/CsvFiles/Inputs/Log5In.csv");
            tan_In = new FileReader("src/test/resources/CsvFiles/Inputs/TanIn.csv");

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(csc_In);
            for (CSVRecord record : records) {
                Mockito.when(csc_Mock.csc(parseDouble(record.get(0)), functionEps)).thenReturn(valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(cos_In);
            for (CSVRecord record : records) {
                Mockito.when(cos_Mock.cos(parseDouble(record.get(0)), functionEps)).thenReturn(valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(sin_In);
            for (CSVRecord record : records) {
                Mockito.when(sin_Mock.sin(parseDouble(record.get(0)), functionEps)).thenReturn(valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(tan_In);
            for (CSVRecord record : records) {
                Mockito.when(tan_Mock.tan(parseDouble(record.get(0)), functionEps)).thenReturn(valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(ln_In);
            for (CSVRecord record : records) {
                Mockito.when(ln_Mock.ln(parseDouble(record.get(0)), functionEps)).thenReturn(valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log2_In);
            for (CSVRecord record : records) {
                Mockito.when(log_Mock.log(2, parseDouble(record.get(0)), functionEps)).thenReturn(valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log5_In);
            for (CSVRecord record : records) {
                Mockito.when(log_Mock.log(5, parseDouble(record.get(0)), functionEps)).thenReturn(valueOf(record.get(1)));
            }
        } catch (IOException ex) {
            System.err.println("How did you get here?");
            System.out.println(ex.getMessage());
        }

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
    void testSystemWithMocks(double value, double expected) {
        Function function = new Function(csc_Mock, log_Mock, ln_Mock, tan_Mock);
        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
    void testWithCsc(double value, double expected) {
        Function function = new Function(new Csc(sin_Mock), log_Mock, ln_Mock, tan_Mock);
        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
    void testWithTan(double value, double expected) {
        Function function = new Function(csc_Mock, log_Mock, ln_Mock, new Tan(sin_Mock, cos_Mock));
        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
    void testWithSin(double value, double expected) {
        Function function = new Function(new Csc(new Sin()), log_Mock, ln_Mock, new Tan(new Sin(), new Cos(new Sin())));
        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
    void testWithCos(double value, double expected) {
        Function function = new Function(csc_Mock, log_Mock, ln_Mock, new Tan(sin_Mock, new Cos(sin_Mock)));
        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
    void testWithLog(double value, double expected) {
        Function function = new Function(csc_Mock, new Log(), ln_Mock, tan_Mock);
        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
    void testWithLn(double value, double expected) {
        Function function = new Function(csc_Mock, new Log(new Ln()), new Ln(), tan_Mock);
        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps * 50);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
    void testWithSinAndLn(double value, double expected) {
        Function function = new Function();
        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps * 50);
    }
}