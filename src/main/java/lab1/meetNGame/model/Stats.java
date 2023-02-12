package lab1.meetNGame.model;

public class Stats {

    private final long descriptions;
    private final long likes;
    private final long matches;

    public Stats(long descriptions, long likes, long matches) {
        this.descriptions = descriptions;
        this.likes = likes;
        this.matches = matches;
    }

    public long getDescriptions() {
        return descriptions;
    }

    public long getLikes() {
        return likes;
    }

    public long getMatches() {
        return matches;
    }
}
