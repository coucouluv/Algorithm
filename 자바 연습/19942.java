import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class Main {

    private static int [][]foods;
    private static int N;
    private static int []minIngredients = new int[4];
    private static int result = Integer.MAX_VALUE;
    private static String resultNum = "";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++) {
            minIngredients[i] = Integer.parseInt(st.nextToken());
        }

        foods = new int [N][5];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++) {
                foods[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, new int[5], "");
        if(result == Integer.MAX_VALUE) {
            System.out.println("-1");
        }
        else {
            System.out.println(result);
            System.out.println(resultNum);
        }
    }
    public static void solve(int index, int [] curScore, String curNum) {

        if(isPossible(curScore)) {
            if(result > curScore[4]) {
                result = curScore[4];
                //여기서 번호 저장
                resultNum = curNum;
            }
            return;
        }
        if(index == N)
            return;
        for(int i = 0; i < 5; i++)
            curScore[i] += foods[index][i];

        solve(index+1, curScore, curNum+(index+1)+ " ");
        for(int i = 0; i < 5; i++)
            curScore[i] -= foods[index][i];
        solve(index+1, curScore, curNum);


    }
    public static boolean isPossible(int []curScore) {
        for(int i = 0; i < 4; i++) {
            if(minIngredients[i] > curScore[i]) {
                return false;
            }
        }
        return true;
    }
}