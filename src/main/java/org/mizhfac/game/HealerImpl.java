package org.mizhfac.game;

public class HealerImpl extends AbstractWarrior implements CanHeal {
    static final int INITIAL_HEALTH = 50;
    static final int HEAL_POWER = 2;

    public HealerImpl() {
        super(INITIAL_HEALTH);
    }

    @Override
    public int getAttack() {
        return 0;
    }

    int getHealPower() {
        return HEAL_POWER;
    }

    @Override
    public void heal(HasHealth patient) {
        if (patient instanceof AbstractWarrior abstractWarrior) {
            abstractWarrior.setHealth(abstractWarrior.getHealth() + getHealPower());
        }
    }
}
