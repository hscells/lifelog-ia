package com.hscells.lifelogia.views;

import com.hscells.lifelogia.model.stats.Statistic;
import io.dropwizard.views.View;

/**
 * Created by Harry Scells on 24/08/2016.
 */
public class StatisticsView extends View {

    private Statistic statistic;

    public StatisticsView(Statistic statistic) {
        super("stats.mustache");
        this.statistic = statistic;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }
}
