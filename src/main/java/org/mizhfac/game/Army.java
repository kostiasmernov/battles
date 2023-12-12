package org.mizhfac.game;

import java.util.*;
import java.util.function.Supplier;

public class Army implements Iterable<Warrior> {
    private static int idCounter = 0;
    private int id = ++idCounter;

    private Deque<WarriorInArmyImpl> troops = new ArrayDeque<>();

    public Army addUnits(WarriorClasses warriorClasses, int quantity) {
        return addUnits(warriorClasses::make, quantity);
    }

    public Army addUnits(Supplier<Warrior> warriorFactory, int quantity) {
        for (int i = 0; i < quantity; i++) {
            Warrior novice = warriorFactory.get();
            var noviceInArmy = new WarriorInArmyImpl(novice);
            Optional.ofNullable(troops.peekLast())
                    .ifPresent(w -> w.setWarriorBehind(noviceInArmy));
//            var last = troops.peekLast();
//            if (last != null) {
//                last.setWarriorBehind(noviceInArmy);
//            }
            troops.add(noviceInArmy);
        }
        return this;
    }

    public boolean isEmpty() {
        return !new FirstAliveIterator().hasNext();
    }

    @Override
    public Iterator<Warrior> iterator() {
        return troops.stream()
                .filter(Warrior::isAlive)
                .map(WarriorInArmyImpl::unwrap)
                .iterator();
    }

    public Iterator<Warrior> firstAliveIterator() {
        return new FirstAliveIterator();
    }

    @Override
    public String toString() {
        return "Army#" + id +
                "{" + troops +
                '}';
    }

    interface Command {
    }

    enum ChampionDealsHit implements Command {
        INSTANCE;
    }

    private class WarriorInArmyImpl implements WarriorInArmy {
        private final Warrior warrior;
        private WarriorInArmy warriorBehind;

        public WarriorInArmyImpl(Warrior warrior) {
            this.warrior = Objects.requireNonNull(warrior);
        }

        private void setWarriorBehind(WarriorInArmy warriorBehind) {
            this.warriorBehind = Objects.requireNonNull(warriorBehind);
        }

        @Override
        public Optional<WarriorInArmy> getWarriorBehind() {
            return Optional.ofNullable(warriorBehind);
        }

        @Override
        public void acceptDamage(int damage) {
            warrior.acceptDamage(damage);
        }

        Warrior unwrap() {
            return warrior;
        }

        void passCommand(Command command, WarriorInArmy passer) {
            if (passer != this) {
                if (command instanceof ChampionDealsHit &&
                        warrior instanceof CanHeal healer) {
                    healer.heal(passer);
                }
            }
            getWarriorBehind().ifPresent(
                    w -> ((WarriorInArmyImpl) w).passCommand(
                            command, this)
            );
        }

        @Override
        public void hit(CanAcceptDamage opponent) {
            warrior.hit(opponent);
            passCommand(ChampionDealsHit.INSTANCE, this);
        }

        @Override
        public int getAttack() {
            return warrior.getAttack();
        }

        @Override
        public int getHealth() {
            return warrior.getHealth();
        }

        @Override
        public boolean isAlive() {
            return warrior.isAlive();
        }

        @Override
        public String toString() {
            return warrior.toString();
        }
    }

    private class FirstAliveIterator
            implements Iterator<Warrior> {
        @Override
        public boolean hasNext() {
            while (!troops.isEmpty() && !troops.peek().isAlive()) {
                troops.poll();
            }
            return !troops.isEmpty();
        }

        @Override
        public Warrior next() {
            if (!hasNext()) throw new NoSuchElementException();
            return troops.peek();
        }
    }
}
