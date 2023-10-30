package racingcar.collaborator.race;

import java.util.ArrayList;
import java.util.List;
import racingcar.generic.LapProgress;
import racingcar.generic.RaceTotalProgress;
import racingcar.generic.RacerProgress;

public class Race {

    private final List<LapProgress> lapProgresses;
    private List<Racer> racers;
    private Integer numberOfRound;

    public Race() {
        this.lapProgresses = new ArrayList<>();
    }

    public void registerRacer(List<Racer> racers) {
        this.racers = racers;
    }

    public void decideRoundNumber(Integer numberOfRound) {
        this.numberOfRound = numberOfRound;
    }

    public void run() {
        for (int i = 0; i < numberOfRound; i++) {
            driveEachRacer();
            saveProgressEachRacer();
        }
    }

    private void driveEachRacer() {
        for (Racer racer : racers) {
            racer.drive();
        }
    }

    private void saveProgressEachRacer() {
        List<RacerProgress> list = new ArrayList<>();
        for (Racer racer : racers) {
            RacerProgress progress = racer.getProgress();
            list.add(progress);
        }
        lapProgresses.add(new LapProgress(list));
    }

    public RaceTotalProgress getTotalProgress() {
        return new RaceTotalProgress(lapProgresses);
    }

}
