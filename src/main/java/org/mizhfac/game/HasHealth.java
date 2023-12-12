package org.mizhfac.game;

public interface HasHealth {
    int getHealth();
    default boolean isAlive(){
        return getHealth() > 0;
    }
}
