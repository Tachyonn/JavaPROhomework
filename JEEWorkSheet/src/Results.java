import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Results {
    private Map<String, Integer> resultsMap = new HashMap<>();
    private Map<String, Voter> voters = new HashMap();
    private static Results results = new Results();

    public static Results getInstance() {
        return results;
    }

    private Results() {
        super();
    }

    public synchronized void addResult(String result) {
        if (resultsMap.containsKey(result)) {
            Integer tmp = resultsMap.get(result);
            resultsMap.put(result, tmp + 1);
        } else {
            resultsMap.put(result, 1);
        }
    }

    public synchronized Map<String, Integer> getResults() {
        return new HashMap<>(resultsMap);
    }

    public synchronized List<Voter> getVoters() {
        return new ArrayList<>(voters.values());
    }

    public synchronized void addVoter(String name, String age, String sessionID) {
        int vAge = Integer.parseInt(age);
        Voter voter = new Voter(name, vAge);
        voters.put(sessionID, voter);
    }

    public String votersToSting() {
        StringBuilder sb = new StringBuilder();
        voters.forEach((key, value) ->
                sb.append("Voter ").append("\t").append(value.toString()).append("<br>"));
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        resultsMap.forEach((key, value) ->
                sb.append(key).append("\t").append(value).append("<br>"));
        return sb.toString();
    }
}
