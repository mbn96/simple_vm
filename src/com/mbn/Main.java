package com.mbn;

import com.mbn.compiler.lexer.Lexer;
import com.mbn.compiler.parser.Parser;
import com.mbn.interpreter.Interpreter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String line;

        while (!(line = scanner.nextLine()).equals("exit")) {
            try {


                Lexer lexer = new Lexer(line);
                var tokens = lexer.getTokens();
                System.out.println(tokens);

                Parser parser = new Parser(tokens);
                var parseRes = parser.parse();
                System.out.println(parseRes);

                Interpreter interpreter = new Interpreter(parseRes);
                System.out.println(interpreter.solve());



//              Test...
//                var ts = System.currentTimeMillis();
//                for (int i = 0; i < 1_000_000; i++) {
//                    interpreter.solve();
//                }
//                var te = System.currentTimeMillis();
//                System.out.println("Run time: " + (te - ts));


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
