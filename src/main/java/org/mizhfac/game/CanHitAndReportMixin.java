package org.mizhfac.game;

public interface CanHitAndReportMixin extends CanHit{
    default int hitAndReportDealtDamage(CanAcceptDamage opponent){
        var healthBefore = opponent.getHealth();
        CanHit.super.hit(opponent);
        var healthAfter = opponent.getHealth();
        var damageDealt = healthBefore - healthAfter;
        return damageDealt;
    }
}
