package designpatterns.strategy.gift;

import database.MainDB;

public interface GiftStrategy {
    /**
     * strategie de asignare a cadourilor
     */
    void sendGifts(MainDB mainDB, Double santaBudget);
}
