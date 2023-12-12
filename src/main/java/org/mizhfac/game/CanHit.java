package org.mizhfac.game;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

public interface CanHit extends HasAttack{
    Logger log = LoggerFactory.getLogger(CanHit.class);
    default void hit(CanAcceptDamage opponent){
        log.info("Warrior {} hits {}", this, opponent);
        opponent.acceptDamage(getAttack());
    }
}
