package racingcar.collaborator.race;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.api.AbstractLongAssert;
import org.junit.jupiter.api.Test;
import racingcar.generic.RaceTotalProgress;
import racingcar.generic.Winners;
import racingcar.io.racing.FailureRaceRandoms;
import racingcar.io.racing.SuccessRaceRandoms;

class RaceTest {

    @Test
    void registerRacer로_레이서를등록하고_run을_실행하면_getTotalProgress로_승자를확인할수있다() {
        String expectedWinnerName = "김길동";
        Race race = new Race();
        race.registerRacer(
                List.of(new Racer(expectedWinnerName, new SuccessRaceRandoms()),
                        new Racer("홍길동", new FailureRaceRandoms()))
        );
        race.decideRoundNumber(1);

        race.run();
        RaceTotalProgress totalProgress = race.getTotalProgress();
        Winners actualWinners = totalProgress.decideWinner();

        mustBeOneWinner(actualWinners);
        assertThat(getWinnerName(actualWinners)).isEqualTo(expectedWinnerName);
    }

    private static AbstractLongAssert<?> mustBeOneWinner(Winners actualWinners) {
        return assertThat(actualWinners.stream().count()).isEqualTo(1);
    }

    private static String getWinnerName(Winners actualWinners) {
        return actualWinners.winners().get(0);
    }

}