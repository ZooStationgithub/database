package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IGenericEntityDAO;
import nl.zoostation.database.exception.ObjectNotFoundException;
import nl.zoostation.database.model.domain.Framework;
import nl.zoostation.database.model.domain.ProgrammingLanguage;
import nl.zoostation.database.model.form.FrameworkFormObject;
import nl.zoostation.database.model.form.FrameworkFormWrapper;
import nl.zoostation.database.service.BaseServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.*;

/**
 * @author valentinnastasi
 */
public class FrameworkFormServiceTest extends BaseServiceTest {

    @Autowired
    private IGenericEntityDAO<ProgrammingLanguage, Long> programmingLanguageDAO;

    @Autowired
    private IGenericEntityDAO<Framework, Long> frameworkDAO;

    @Autowired
    private FrameworkFormService frameworkFormService;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        Mockito.reset(programmingLanguageDAO);
    }

    @Test
    public void testPrepareFormNew() throws Exception {
        when(programmingLanguageDAO.findAll()).thenReturn(Arrays.asList(new ProgrammingLanguage(1L, "1"), new ProgrammingLanguage(2L, "2")));
        when(frameworkDAO.create()).thenReturn(new Framework());

        FrameworkFormWrapper formWrapper = frameworkFormService.prepareForm(Optional.empty());
        assertThat(formWrapper).isNotNull();
        assertThat(formWrapper.getForm()).isNotNull().isEqualToComparingFieldByField(new FrameworkFormObject());
        assertThat(formWrapper.getProgrammingLanguages()).isNotNull().hasSize(2).extracting("id", "name")
                .contains(tuple(1L, "1"), tuple(2L, "2"));

        verify(frameworkDAO, times(0)).findOne(any(Long.class));
    }

    @Test
    public void testPrepareFormExisting() throws Exception {
        final long id = 1L;
        ProgrammingLanguage pl = new ProgrammingLanguage(1L, "1");
        Framework framework = new Framework(id, "f", pl);

        when(frameworkDAO.findOne(id)).thenReturn(Optional.of(framework));
        when(programmingLanguageDAO.findAll()).thenReturn(Collections.singletonList(pl));

        FrameworkFormWrapper formWrapper = frameworkFormService.prepareForm(Optional.of(id));
        assertThat(formWrapper).isNotNull();
        assertThat(formWrapper.getForm()).isNotNull().isEqualToComparingFieldByField(new FrameworkFormObject(id, "f", pl.getId()));
        assertThat(formWrapper.getProgrammingLanguages()).isNotNull().hasSize(1);
    }

    @Test
    public void testPrepareFormNotExisting() throws Exception {
        final long id = 1L;

        when(frameworkDAO.findOne(id)).thenReturn(Optional.empty());
        when(frameworkDAO.create()).thenReturn(new Framework());
        when(programmingLanguageDAO.findAll()).thenReturn(Collections.singletonList(new ProgrammingLanguage(1L, "1")));

        FrameworkFormWrapper formWrapper = frameworkFormService.prepareForm(Optional.of(id));
        assertThat(formWrapper).isNotNull();
        assertThat(formWrapper.getForm()).isNotNull().isEqualToComparingFieldByField(new FrameworkFormObject());
        assertThat(formWrapper.getProgrammingLanguages()).isNotNull().hasSize(1);

        verify(frameworkDAO).findOne(id);
        verify(frameworkDAO).create();
    }

    @Test
    public void testSave() throws Exception {
        ProgrammingLanguage pl = new ProgrammingLanguage(1L, "1");
        FrameworkFormObject formObject = new FrameworkFormObject(null, "f", pl.getId());

        when(frameworkDAO.create()).thenReturn(new Framework());
        when(programmingLanguageDAO.findOne(1L)).thenReturn(Optional.of(pl));

        frameworkFormService.save(formObject);

        ArgumentCaptor<Framework> argumentCaptor = ArgumentCaptor.forClass(Framework.class);
        verify(frameworkDAO).save(argumentCaptor.capture());

        Framework framework = argumentCaptor.getValue();
        assertThat(framework).isNotNull().isEqualToComparingFieldByField(new Framework(null, "f", pl));
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testSaveMissingProgrammingLanguage() throws Exception {
        FrameworkFormObject formObject = new FrameworkFormObject(null, "f", 1L);

        when(frameworkDAO.create()).thenReturn(new Framework());
        when(programmingLanguageDAO.findOne(1L)).thenReturn(Optional.empty());

        frameworkFormService.save(formObject);
    }
}