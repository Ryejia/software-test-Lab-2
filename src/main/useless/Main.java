//public class Main {
//    public static void main(String[] args) {
//        double PI = Math.PI;
//        double eps = 0.01;
//        Function function = new Function();
//        Tan tan = new Tan();
//        Log log = new Log();
//        Csc csc = new Csc();
//        Sin sin = new Sin();
//        Cos cos = new Cos();
//        Ln ln = new Ln();
//        double[] arr = {0, -0.5235987, -1.047198, -1.570796, -3.1415926, -4.712389, -6.283185};
//        double[] arr2 = {0.25, 0.5, 1, 2, 2.718281828, 4, 10, 100};
//        System.out.println("------------Sin---------------");
//        for (double v : arr) {
//            var answer = sin.sin(v, eps);
//            System.out.println(v + ", " + answer);
//        }
//        System.out.println("------------Cos---------------");
//        for (double v : arr) {
//            var answer = cos.cos(v, eps);
//            System.out.println(v + ", " + answer);
//        }
//        System.out.println("------------Tan---------------");
//        for (double v : arr) {
//            var answer = tan.tan(v, eps);
//            System.out.println(v + ", " + answer);
//        }
//        System.out.println("------------Csc---------------");
//        for (double v : arr) {
//            var answer = csc.csc(v, eps);
//            System.out.println(v + ", " + answer);
//        }
//        System.out.println("------------Ln---------------");
//        for (double v : arr2) {
//            var answer = ln.ln(v, eps);
//            System.out.println(v + ", " + answer);
//        }
//        System.out.println("------------Log2---------------");
//        for (double v : arr2) {
//            var answer = log.log(2, v, eps);
//            System.out.println(v + ", " + answer);
//        }
//        System.out.println("------------Log5---------------");
//        for (double v : arr2) {
//            var answer = log.log(5, v, eps);
//            System.out.println(v + ", " + answer);
//        }
//        System.out.println("------------Func---------------");
//        for (double v : arr) {
//            var answer = function.SystemSolve(v, eps);
//            System.out.println(v + ", " + answer);
//        }
//        for (double v : arr2) {
//            var answer = function.SystemSolve(v, eps);
//            System.out.println(v + ", " + answer);
//        }
//    }
//}
