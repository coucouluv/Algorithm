import java.util.*;

class Pair<L,R> implements Comparable<Pair>{
    private L name;
    private R play;
    Pair(L name, R play) {
        this.name = name;
        this.play = play;
    }
    public L getName() {
        return name;
    }
    public R getPlay() {
        return play;
    }
    @Override
    public int compareTo(Pair p) {
        return (Integer)p.play - (Integer)this.play;
    }
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap <String, List<Pair>> map = new HashMap<>(); //장르에 해당하는 노래들
        List <Pair> list = new ArrayList<>(); //장르랑 재생수
        for(int i = 0; i < plays.length; i++) {
            boolean here = false;
            int index = 0;
            if(map.containsKey(genres[i])) {
                map.get(genres[i]).add(new Pair(i, plays[i]));
            }
            else {
                List <Pair> tmp = new ArrayList<>();
                tmp.add(new Pair(i, plays[i]));
                map.put(genres[i], tmp);
            }
            for(int j = 0; j < list.size(); j++) {
                if(list.get(j).getName().equals(genres[i])) {
                    here = true;
                    index = j;
                    break;
                }
            }
            if(here) {
                int tmpplay = (int)list.get(index).getPlay()+plays[i];
                list.set(index, new Pair(genres[i], tmpplay));
            }
            else {
                list.add(new Pair(genres[i], plays[i]));
            }

        }
        Collections.sort(list);
        List <Integer> tmpanswer = new ArrayList <>();
        for(int i = 0; i < list.size(); i++) {
            String name = (String)list.get(i).getName();
            //이제 여기서 정렬하고 넣어야함
            List <Pair> songs = map.get(name);
            Collections.sort(songs);
            for(int j = 0; j < songs.size(); j++) {
                if(j == 2) break;
                tmpanswer.add((int)songs.get(j).getName());
            }
        }

        int[] answer = new int[tmpanswer.size()];
        for(int i = 0; i < tmpanswer.size(); i++) {
            answer[i] = tmpanswer.get(i);
        }
        return answer;
    }
}