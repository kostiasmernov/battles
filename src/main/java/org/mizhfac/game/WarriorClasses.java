package org.mizhfac.game;

public enum WarriorClasses {
    WARRIOR, KNIGHT, DEFENDER, VAMPIRE, LANCER, HEALER;

    public static Warrior factory(WarriorClasses warriorClasses){
        return switch (warriorClasses){
            case WARRIOR -> new WarriorImpl();
            case KNIGHT -> new KnightImpl();
            case DEFENDER -> new DefenderImpl();
            case VAMPIRE -> new VampireImpl();
            case LANCER -> new LancerImpl();
            case HEALER -> new HealerImpl();
        };
    }
    public Warrior make(){
        return factory(this);
    }

}
