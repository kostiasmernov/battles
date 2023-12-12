package org.mizhfac.game;

import java.util.Optional;

public interface WarriorInArmy extends Warrior {
     Optional<WarriorInArmy> getWarriorBehind();
}
