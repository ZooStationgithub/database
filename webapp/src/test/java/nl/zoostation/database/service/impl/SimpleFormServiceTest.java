package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IGenericEntityDAO;
import nl.zoostation.database.model.domain.ProgrammingLanguage;
import nl.zoostation.database.model.form.IdNameFormObject;
import nl.zoostation.database.model.form.SimpleFormWrapper;
import nl.zoostation.database.service.BaseServiceTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author val
 */
public class SimpleFormServiceTest extends BaseServiceTest {

    private static final long ID = 1L;
    private static final String NAME = "Name";

    @Autowired
    private IGenericEntityDAO<ProgrammingLanguage, Long> programmingLanguageDAO;

    @Autowired
    private SimpleFormService<ProgrammingLanguage> programmingLanguageFormService;

    @SuppressWarnings("unchecked")
    @After
    public void tearDown() throws Exception {
        Mockito.reset(programmingLanguageDAO);
    }

    @Test
    public void testPrepareFormNew() throws Exception {
        ProgrammingLanguage pl = new ProgrammingLanguage();
        when(programmingLanguageDAO.create()).thenReturn(pl);

        SimpleFormWrapper<IdNameFormObject> formWrapper = programmingLanguageFormService.prepareForm(Optional.empty());
        assertThat(formWrapper).isNotNull();
        assertThat(formWrapper.getForm()).isNotNull().isInstanceOf(IdNameFormObject.class)
                .isEqualToComparingFieldByField(new IdNameFormObject(null, null));

        verify(programmingLanguageDAO, times(0)).findOne(any(Long.class));
    }

    @Test
    public void testPrepareFormExisting() throws Exception {
        ProgrammingLanguage pl = new ProgrammingLanguage(ID, NAME);
        when(programmingLanguageDAO.findOne(ID)).thenReturn(Optional.of(pl));

        SimpleFormWrapper<IdNameFormObject> formWrapper = programmingLanguageFormService.prepareForm(Optional.of(ID));
        assertThat(formWrapper).isNotNull();
        assertThat(formWrapper.getForm()).isNotNull().isInstanceOf(IdNameFormObject.class)
                .isEqualToComparingFieldByField(new IdNameFormObject(ID, NAME));
    }

    @Test
    public void testPrepareFormNotExisting() throws Exception {
        ProgrammingLanguage pl = new ProgrammingLanguage();
        when(programmingLanguageDAO.create()).thenReturn(pl);
        when(programmingLanguageDAO.findOne(ID)).thenReturn(Optional.empty());

        SimpleFormWrapper<IdNameFormObject> formWrapper = programmingLanguageFormService.prepareForm(Optional.of(ID));
        assertThat(formWrapper).isNotNull();
        assertThat(formWrapper.getForm()).isNotNull().isInstanceOf(IdNameFormObject.class)
                .isEqualToComparingFieldByField(new IdNameFormObject(null, null));

        verify(programmingLanguageDAO).findOne(ID);
        verify(programmingLanguageDAO).create();
    }

    @Test
    public void testSaveNew() throws Exception {
        ProgrammingLanguage pl = new ProgrammingLanguage();
        when(programmingLanguageDAO.create()).thenReturn(pl);
        when(programmingLanguageDAO.save(pl)).thenReturn(new ProgrammingLanguage(ID, NAME));

        ProgrammingLanguage savedPL = programmingLanguageFormService.save(new IdNameFormObject(null, NAME));
        assertThat(savedPL).isNotNull().isEqualToComparingFieldByField(new ProgrammingLanguage(ID, NAME));

        verify(programmingLanguageDAO).create();
        verify(programmingLanguageDAO, times(0)).findOne(any(Long.class));
    }

    @Test
    public void testSaveExisting() throws Exception {
        final String newName = "NEW NAME";
        ProgrammingLanguage pl = new ProgrammingLanguage(ID, NAME);
        when(programmingLanguageDAO.findOne(ID)).thenReturn(Optional.of(pl));
        when(programmingLanguageDAO.save(pl)).thenReturn(new ProgrammingLanguage(ID, newName));

        ProgrammingLanguage savedPL = programmingLanguageFormService.save(new IdNameFormObject(ID, newName));
        assertThat(savedPL).isNotNull().isEqualToComparingFieldByField(new ProgrammingLanguage(ID, newName));
    }

    @Test
    public void testSaveNotExisting() throws Exception {
        ProgrammingLanguage pl = new ProgrammingLanguage();
        when(programmingLanguageDAO.findOne(ID)).thenReturn(Optional.empty());
        when(programmingLanguageDAO.create()).thenReturn(pl);
        when(programmingLanguageDAO.save(pl)).thenReturn(new ProgrammingLanguage(ID, NAME));

        ProgrammingLanguage savedPL = programmingLanguageFormService.save(new IdNameFormObject(ID, NAME));
        assertThat(savedPL).isNotNull().isEqualToComparingFieldByField(new ProgrammingLanguage(ID, NAME));

        verify(programmingLanguageDAO).create();
        verify(programmingLanguageDAO).findOne(ID);
    }

    @Test
    public void testDelete() throws Exception {
        programmingLanguageFormService.delete(ID);
        verify(programmingLanguageDAO).delete(ID);
    }
}