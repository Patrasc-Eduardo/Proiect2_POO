package designpatterns.strategy.gift;

import database.MainDB;

public interface GiftStrategy {
    void sendGifts(MainDB mainDB, Double santaBudget);
}
