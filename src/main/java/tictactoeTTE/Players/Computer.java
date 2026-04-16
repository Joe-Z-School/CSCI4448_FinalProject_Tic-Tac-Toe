package tictactoeTTE.Players;

import tictactoeTTE.Movement.Movement;

public class Computer extends Player {

    public Computer(String name, String symbol, Movement movement) {
        super(name,symbol,movement);
    }

    @Override
    public boolean isComputer(){ return true;}
}
