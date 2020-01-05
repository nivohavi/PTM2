package boot;

import server_side.*;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        CacheManager cm = new FileCacheManager();

        Searcher<Pair<Integer,Integer>> searcher = new BestFirstSearch<>();

        Solver<Searchable<Pair<Integer, Integer>>,
                List<State<Pair<Integer, Integer>>>> s = new SearcherAdapter<>(searcher);

        ClientHandler c1 = new MyClientHandler(s, cm);
        Server s1 = new MySerialServer();

        s1.start(Integer.valueOf("9876"), c1);
    }
}
