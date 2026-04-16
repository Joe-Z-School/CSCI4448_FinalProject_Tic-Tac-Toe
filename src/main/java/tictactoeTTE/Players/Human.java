package tictactoeTTE.Players;

import tictactoeTTE.Movement.Movement;

public class Human extends Player {

    public Human(String name, String symbol, Movement movement) {
        super(name,symbol,movement);
    }

    @Override
    public boolean isHuman(){return true;}
}
