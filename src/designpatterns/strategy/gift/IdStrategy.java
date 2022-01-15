package designpatterns.strategy.gift;

import database.MainDB;
import utilsprocess.UtilsProcess;

public final class IdStrategy implements GiftStrategy {

    public IdStrategy() {
    }

    /**
     * Strategia de asignare a cadourilor in functie de ID
     * @param mainDB baza de date principala
     * @param santaBudget bugetul lui santa
     */
    @Override
    public void sendGifts(final MainDB mainDB, final Double santaBudget) {
        UtilsProcess.sendGifts(mainDB, mainDB.getChildrenList(), santaBudget);
    }
}
