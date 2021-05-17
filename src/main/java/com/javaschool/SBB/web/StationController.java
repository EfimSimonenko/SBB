package com.javaschool.SBB.web;

import com.javaschool.SBB.db.entities.Station;
import com.javaschool.SBB.service.StationService;
import com.javaschool.SBB.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StationController {
    @Autowired
    StationService stationService;

    @Autowired
    TimetableService timetableService;

    @RequestMapping(value = "/stations", method = RequestMethod.GET)
    public String stationsPage(Model model) {
        model.addAttribute("station", new Station());
        model.addAttribute("listStations", stationService.listAllStations());
        return "all_stations";
    }

    @RequestMapping(value = "stationInfo/{id}", method = RequestMethod.GET)
    public String getBoookInfo(@PathVariable("id") int id, Model model) {
        Station station = this.stationService.getStationById(id);
        model.addAttribute("station", station);
        model.addAttribute("timetableList", this.timetableService.getStationTimetableForToday(station));
        return "station_info";
    }


}
