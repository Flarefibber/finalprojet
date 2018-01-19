package pavlo.restserver.controller;

import org.springframework.http.MediaType;
import pavlo.restserver.service.WorkReportService;
import pavlo.restserver.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private WorkReportService workReportService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Report> addReport(@RequestBody Report report) {
        workReportService.add(report);

        return new ResponseEntity<Report>(report, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Report>> getAllReport() {
        List<Report> reports = workReportService.findAll();

        if (reports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Report>> getReportByID(@PathVariable("id") long tabelID) {
        List<Report> reports = workReportService.findByTabelId(tabelID);

        if (reports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/{begin}/{end}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Report> getReport(@PathVariable("id") long tabelID, @PathVariable("begin") String beginPeriod, @PathVariable("end") String endPeriod) {
        List<Report> list = workReportService.findReportByEmployeeAndBeginPeriodAndEndPeriod(tabelID, beginPeriod, endPeriod);
        Report report = null;

        if (list.size() > 0) {
            report = list.get(list.size() - 1);
        }
        if (report == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(report, HttpStatus.OK);
    }
}
