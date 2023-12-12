package org.mizhfac.game;

public class LancerImpl
        extends AbstractWarrior
        implements CanHitAndReportMixin {
    static final int INITIAL_HEALTH = 50;
    static final int ATTACK = 6;
    static final int PENETRATION = 50;

    public LancerImpl() {
        super(INITIAL_HEALTH);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }

    @Override
    public void hit(CanAcceptDamage opponent) {
        var damageDealt = hitAndReportDealtDamage(opponent);
        if (opponent instanceof WarriorInArmy warriorInArmy){
            var nextBehind = warriorInArmy.getWarriorBehind();
            if (nextBehind.isPresent()) {
                int secondDamage = damageDealt * PENETRATION / 100;
                CanHit proxySecondHitByLancer = () -> secondDamage;
                proxySecondHitByLancer.hit(nextBehind.get());
                //nextBehind.get().acceptDamage(secondDamage);
            }
        }
    }
}
