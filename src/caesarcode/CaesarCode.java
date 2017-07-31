package balance;

import java.io.IOException;
import java.util.Scanner;

class CaesarCode {


    public static void main(String[] args) throws IOException {

        CaesarCodeConverter.translateToCaesarCode();

    }
}

class CaesarCodeConverter {


    static void translateToCaesarCode() throws IOException {

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String temp;

        while (!(temp = sc.nextLine()).isEmpty()) {

            sb.append(temp);
            sb.append('\n');
        }

        sb.chars()
                .map(c -> {
                    if (c >= 65 && c <= 87) {
                        c += 3;

                    } else if (c > 87 && c <= 90) {
                        c -= 22;
                    }
                    return c;

                }).forEach(i -> System.out.print((char) i));

    }

}






