package view;

import domain.Ladder;
import domain.LadderResults;
import domain.Line;
import domain.Participants;

public class OutputView {

    private static final String ABLE_TO_MOVE = "-----";
    private static final String DISABLE_TO_MOVE = "     ";
    private static final String LINE_START = "    |";
    private static final String BLOCK_DELIMITER = "|";
    private static final String RESULT_MESSAGE = "\n실행결과\n";
    private static final boolean CONNECTED = true;

    public void printGameMap(Participants participants, Ladder ladder, LadderResults ladderResults) {
        System.out.println(RESULT_MESSAGE);
        StringBuilder gameMap = new StringBuilder();
        setNames(gameMap, participants);
        gameMap.append(System.lineSeparator());
        setLadder(gameMap, ladder);
        setResults(gameMap, ladderResults);
        System.out.print(gameMap);
    }

    private void setNames(StringBuilder gameMap, Participants participants) {
        participants.getNames()
            .forEach((participantName) -> gameMap.append(reformatName(participantName)));
    }

    private String reformatName(String name) {
        return String.format("%5s ", name);
    }

    private void setLadder(StringBuilder gameMap, Ladder ladder) {
        ladder.getLines()
            .forEach((line) -> gameMap.append(reformatLine(line)));
    }

    private String reformatLine(Line line) {
        final StringBuilder result = new StringBuilder();
        result.append(LINE_START);
        for (Boolean block : line.getBlocks()) {
            result.append(reformatStatus(block)).append(BLOCK_DELIMITER);
        }
        result.append(System.lineSeparator());
        return result.toString();
    }

    private String reformatStatus(Boolean status) {
        if (status == CONNECTED) {
            return ABLE_TO_MOVE;
        }
        return DISABLE_TO_MOVE;
    }

    private void setResults(StringBuilder gameMap, LadderResults ladderResults) {
        ladderResults.getResults()
            .forEach((ladderResult) -> gameMap.append(reformatResult(ladderResult)));
    }

    private String reformatResult(String ladderResult) {
        return String.format("%5s ", ladderResult);
    }
}
