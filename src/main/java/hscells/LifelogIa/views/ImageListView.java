package hscells.LifelogIa.views;

import hscells.LifelogIa.dto.LeaderboardDto;
import io.dropwizard.views.View;

import java.util.List;

/**
 * Created by Harry Scells on 16/12/2015.
 */
public class ImageListView extends View {

    private List<LeaderboardDto> leaderboard;
    private int unannotated;

    public ImageListView(List<LeaderboardDto> leaderboard, int unannotated) {
        super("ImageList.mustache");
        this.leaderboard = leaderboard;
        this.unannotated = unannotated;
    }

    public List<LeaderboardDto> getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(List<LeaderboardDto> leaderboard) {
        this.leaderboard = leaderboard;
    }

    public int getUnannotated() {
        return unannotated;
    }

    public void setUnannotated(int unannotated) {
        this.unannotated = unannotated;
    }


}
