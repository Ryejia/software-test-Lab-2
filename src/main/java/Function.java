import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

@Data
@AllArgsConstructor
public class Function {
    Csc csc;
    Log log;
    Ln ln;
    Tan tan;
    Cos cos;

    public Function() {
        this.csc = new Csc();
        this.log = new Log();
        this.ln = new Ln();
        this.tan = new Tan();
    }

    public Function(Csc csc, Log log, Ln ln, Tan tan) {
        this.csc = csc;
        this.log = log;
        this.ln = ln;
        this.tan = tan;
        this.cos = new Cos();
    }

    public double SystemSolve(double x, double eps) {
        if (x <= 0) {
            return (csc.csc(x, eps) * csc.csc(x, eps) + cos.cos(x, eps)) * tan.tan(x, eps);
        } else {
            return Math.pow(ln.ln(x, eps) / log.log(2, x, eps) + ln.ln(x, eps), 2) / (log.log(2, x, eps) + log.log(5, x, eps));
        }
    }
}
