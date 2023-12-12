package org.mizhfac.game;

public class WarriorImpl extends AbstractWarrior {
    static final int INITIAL_HEALTH = 50;
    static final int ATTACK = 5;

    public WarriorImpl() {
        super(INITIAL_HEALTH);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }
}
