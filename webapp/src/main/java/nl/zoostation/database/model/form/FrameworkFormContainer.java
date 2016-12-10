package nl.zoostation.database.model.form;

import nl.zoostation.database.model.domain.ProgrammingLanguage;

import java.util.List;

/**
 * @author valentinnastasi
 */
public class FrameworkFormContainer {

    private FrameworkForm frameworkForm;
    private List<ProgrammingLanguage> programmingLanguages;

    public FrameworkFormContainer() {
    }

    public FrameworkForm getFrameworkForm() {
        return frameworkForm;
    }

    public void setFrameworkForm(FrameworkForm frameworkForm) {
        this.frameworkForm = frameworkForm;
    }

    public List<ProgrammingLanguage> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(List<ProgrammingLanguage> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }
}
