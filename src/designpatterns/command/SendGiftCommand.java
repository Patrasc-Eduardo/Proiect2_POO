package designpatterns.command;

import database.MainDB;
import designpatterns.strategy.gift.GiftFactory;
import designpatterns.strategy.gift.GiftStrategy;
import entities.Child;

import java.util.ArrayList;

public final class SendGiftCommand implements Command {
    private MainDB mainDB;
    private ArrayList<Child> beforeChildList;
    private ArrayList<Child> childrenList;
    private String strategy;

    public SendGiftCommand(final MainDB mainDB, final String strategy) {
        this.mainDB = mainDB;
        this.strategy = strategy;
    }

    /**
     * Creaza o strategie de impartire a cadourile si le imparte.
     */
    @Override
    public void execute() {
        beforeChildList = new ArrayList<>(mainDB.getChildrenList());

        GiftFactory giftFactory = new GiftFactory();
        GiftStrategy sendGiftStrategy = giftFactory.createStrategy(strategy);
        sendGiftStrategy.sendGifts(mainDB, mainDB.getSanta().getSantaBudget());
    }

    @Override
    public void undo() {
        childrenList = new ArrayList<>(beforeChildList);
        mainDB.setChildrenList(childrenList);
    }

    public MainDB getMainDB() {
        return mainDB;
    }

    public void setMainDB(final MainDB mainDB) {
        this.mainDB = mainDB;
    }

    public ArrayList<Child> getBeforeChildList() {
        return beforeChildList;
    }

    public void setBeforeChildList(final ArrayList<Child> beforeChildList) {
        this.beforeChildList = beforeChildList;
    }

    public ArrayList<Child> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(final ArrayList<Child> childrenList) {
        this.childrenList = childrenList;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(final String strategy) {
        this.strategy = strategy;
    }
}
