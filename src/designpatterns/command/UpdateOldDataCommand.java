package designpatterns.command;

import data.AnnualChange;
import database.MainDB;
import utilsprocess.UtilsProcess;

public final class UpdateOldDataCommand implements Command {
    private AnnualChange anChange;
    private MainDB mainDB;
    private MainDB beforeDB;

    public UpdateOldDataCommand(final AnnualChange anChange, final MainDB mainDB) {
        this.anChange = anChange;
        this.mainDB = mainDB;
    }

    /**
     * Actualizeaza baza de date in functie de annual change.
     */
    @Override
    public void execute() {
        this.beforeDB = null;

        UtilsProcess.removeReceivedGifts(mainDB.getChildrenList());

        UtilsProcess.updateOldChilds(mainDB.getChildrenList(), anChange);

        UtilsProcess.addNewChildren(mainDB.getChildrenList(), anChange.getNewChildren());

        UtilsProcess.updateOldSanta(mainDB.getSanta(), anChange);

    }

    @Override
    public void undo() {
        mainDB = null;
    }

    public AnnualChange getAnChange() {
        return anChange;
    }

    public void setAnChange(final AnnualChange anChange) {
        this.anChange = anChange;
    }

    public MainDB getMainDB() {
        return mainDB;
    }

    public void setMainDB(final MainDB mainDB) {
        this.mainDB = mainDB;
    }

    public MainDB getBeforeDB() {
        return beforeDB;
    }

    public void setBeforeDB(final MainDB beforeDB) {
        this.beforeDB = beforeDB;
    }
}
