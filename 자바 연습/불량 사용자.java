import java.util.*;
class Solution {
    private int result = 0;
    private String[] user, banned;
    private HashSet<HashSet<String>> answer;
    public int solution(String[] user_id, String[] banned_id) {
        answer = new HashSet<>();

        user = new String[user_id.length];
        banned = new String[banned_id.length];

        user = user_id;
        banned = banned_id;
        dfs(0, new LinkedHashSet<>());
        return answer.size();
    }

    public void print(HashSet<String> tmp) {
        for(String a: tmp) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public boolean possible(HashSet<String> tmp) {
        int index = 0;
        for(String cur : tmp) {
            String ban = banned[index++];
            if(!correct(cur, ban)) {
                return false;
            }
        }
        return true;
    }

    public void dfs(int cnt,HashSet<String> tmp) {
        if(cnt == banned.length) {
            if (possible(tmp)) {
                answer.add(new HashSet<>(tmp));
                // print(tmp);
            }
            return;
        }

        for(String cur_user: user) {
            if(tmp.add(cur_user)) {
                dfs(cnt+1, tmp);
                tmp.remove(cur_user);
            }
        }
    }

    public boolean correct(String origin, String ban) {
        if(origin.length() != ban.length()) {
            return false;
        }
        for(int i = 0; i < origin.length(); i++) {
            if(ban.charAt(i) == '*') {
                continue;
            }
            if(ban.charAt(i) != origin.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}