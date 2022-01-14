package designpatterns.strategy.gift;

import database.MainDB;
import utilsprocess.UtilsProcess;

public class IdStrategy implements GiftStrategy{

    public IdStrategy(){

    }

    @Override
    public void sendGifts(MainDB mainDB, Double santaBudget) {
        UtilsProcess.sendGifts(mainDB, mainDB.getChildrenList(), santaBudget);
    }
}
