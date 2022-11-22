import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String words = in.readLine();
        int[] alpha = new int[30];
        for(int i = 0; i < words.length(); i++) {
            char a = words.charAt(i);
            a = Character.toUpperCase(a);
            alpha[a-'A'] += 1;
        }
        int big = 0, bigindex = 0, otherindex = 0;
        for(int i = 0; i < 26; i++) {
            if(big < alpha[i]) {
                big = alpha[i];
                bigindex = i;
            }
            else if(big == alpha[i])
                otherindex = i;
        }

        if (bigindex != otherindex && alpha[bigindex] == alpha[otherindex]) {
            System.out.println('?');
        }
        else {
            char result = (char) (bigindex + 'A');
            System.out.println(result);
        }

    }
}