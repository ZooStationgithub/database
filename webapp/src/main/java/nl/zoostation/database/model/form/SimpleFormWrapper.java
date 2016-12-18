package nl.zoostation.database.model.form;

/**
 * @author valentinnastasi
 */
public class SimpleFormWrapper<F extends IFormObject> implements IFormWrapper<F> {

    private F formObject;

    public SimpleFormWrapper() {
    }

    public SimpleFormWrapper(F formObject) {
        this.formObject = formObject;
    }

    @Override
    public F getForm() {
        return formObject;
    }

    public void setForm(F formObject) {
        this.formObject = formObject;
    }

}
