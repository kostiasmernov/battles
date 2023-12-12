package org.mizhfac.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mizhfac.game.WarriorClasses.*;

public class BattleTest {
    @Test
    @DisplayName("1. Battle: army of 1 warrior loses to army of 2 warriors")
    void battle01() {
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(WARRIOR::make, 1);
        army2.addUnits(() -> WarriorClasses.factory(WARRIOR), 2);

        var res = Game.fight(army1, army2);

        assertFalse(res);
    }
    @Test
    @DisplayName("30W > 25W + 5K")
    void smokeTest() {
        var army1 = new Army()
                .addUnits(WARRIOR, 20)
                .addUnits(KNIGHT, 5);
        var army2 = new Army()
                .addUnits(WARRIOR, 30);

        var res = Game.fight(army2, army1);

        assertTrue(res);
    }

}
