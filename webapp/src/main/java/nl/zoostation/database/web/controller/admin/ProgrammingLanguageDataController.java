package nl.zoostation.database.web.controller.admin;

import nl.zoostation.database.model.form.IdNameForm;
import nl.zoostation.database.web.datatables.DataTablesRequest;
import nl.zoostation.database.web.datatables.DataTablesResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author valentinnastasi
 */
@Controller
@RequestMapping("/admin/data/lang")
public class ProgrammingLanguageDataController {

    @RequestMapping(value = "/tab", method = RequestMethod.GET)
    public String openTab(
            Model model) {
        return "";
    }

    @RequestMapping(value = "/grid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataTablesResponse getGridData(
            DataTablesRequest dataTablesRequest) {
        return null;
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String openForm(
            Model model) {
        return "";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(
            @RequestBody IdNameForm form) {

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable("id") Long id,
            @RequestBody IdNameForm form) {

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable("id") Long id) {

    }

}
