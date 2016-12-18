package nl.zoostation.database.model.form;

import nl.zoostation.database.model.domain.ProgrammingLanguage;

import java.util.List;

/**
 * @author valentinnastasi
 */
public class FrameworkFormWrapper implements IFormWrapper<FrameworkFormObject> {

    private FrameworkFormObject formObject;
    private List<ProgrammingLanguage> programmingLanguages;

    @Override
    public FrameworkFormObject getForm() {
        return formObject;
    }

    public void setForm(FrameworkFormObject form) {
        this.formObject = form;
    }

    public List<ProgrammingLanguage> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(List<ProgrammingLanguage> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }
}
