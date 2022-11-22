import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        String number = in.readLine();
        int sum = 0;
        for(int i = 0; i < N; i++) {
            sum += number.charAt(i) - '0';
        }
        System.out.println(sum);
    }
}