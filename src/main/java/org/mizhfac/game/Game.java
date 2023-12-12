package org.mizhfac.game;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Game {
    public static boolean fight(Warrior first, Warrior second){
        while (first.isAlive()) {
            first.hit(second);
            if (!second.isAlive()) {
                return true;
            }
            second.hit(first);
        }
        return false;
    }

    public static boolean fight(Army first, Army second){
        log.info("Army {} fights against army {}", first, second);
        var it1 = first.firstAliveIterator();
        var it2 = second.firstAliveIterator();
        while (it1.hasNext() && it2.hasNext()) {
            fight(it1.next(), it2.next());
        }
        return it1.hasNext();
    }

    public static boolean straightFight(Army first, Army second){
        log.info("Army {} fights against army {}", first, second);

        while (!first.isEmpty() && !second.isEmpty()) {
            var it1 = first.iterator();
            var it2 = second.iterator();
            while (it1.hasNext() && it2.hasNext()) {
                fight(it1.next(), it2.next());
            }
        }
        return !first.isEmpty();
    }
}
