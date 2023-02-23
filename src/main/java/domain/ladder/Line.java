package domain.ladder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import util.BooleanGenerator;

public class Line {

    private final List<Block> blocks;

    private Line(List<Block> blocks) {
        this.blocks = blocks;
    }

    public static Line valueOf(LineWeight lineWeight, BooleanGenerator booleanGenerator) {
        return new Line(generateBlocks(lineWeight, booleanGenerator));
    }

    private static List<Block> generateBlocks(LineWeight lineWeight, BooleanGenerator booleanGenerator) {
        List<Block> blocks = new ArrayList<>();
        IntStream.range(0, lineWeight.getWeight())
            .forEach((current) -> blocks.add(new Block(generateBlock(blocks, booleanGenerator))));
        return blocks;
    }

    private static boolean generateBlock(List<Block> blocks, BooleanGenerator booleanGenerator) {
        if (blocks.size() == 0) {
            return booleanGenerator.generate();
        }
        Block prevBlock = blocks.get(blocks.size() - 1);
        if (prevBlock.isConnected()) {
            return false;
        }
        return booleanGenerator.generate();
    }

    public List<Block> getBlocks() {
        return Collections.unmodifiableList(blocks);
    }
}
