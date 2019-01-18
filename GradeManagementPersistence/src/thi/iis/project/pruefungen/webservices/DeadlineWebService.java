package thi.iis.project.pruefungen.webservices;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;

import thi.iis.project.pruefungen.jpa.entities.Deadline;
import thi.iis.project.pruefungen.jpa.services.DeadlineService;

/**
 * WebService for Deadline Modification
 * 
 * @author Katrin Krüger
 *
 */
@WebService
public class DeadlineWebService {

    @Inject
    DeadlineService deadlineService;

    /**
     * Create a new Deadline
     * @param date
     * @param deadlineName
     * @return created Deadline
     */
    public Deadline createDeadline(Date date, String deadlineName) {
        Deadline deadline = new Deadline(date, deadlineName);
        deadlineService.create(deadline);
        return deadline;
    }

    /**
     * select a deadline by its name
     * @param name
     * @return Deadline
     */
    public Deadline selectDeadlineByName(String name) {
        return deadlineService.selectByName(name);
    }

    /**
     * select all Deadlines
     * @return List<Deadline>
     */
    public List<Deadline> selectAll() {
        return deadlineService.selectAll();
    }
}
