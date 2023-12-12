package org.mizhfac.game;

public class KnightImpl extends AbstractWarrior {
    static final int INITIAL_HEALTH = 50;
    static final int ATTACK = 7;

    public KnightImpl() {
        super(INITIAL_HEALTH);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

}
