package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.javabrains.coronavirustracker.model.LocationStats;
import io.javabrains.coronavirustracker.service.CoronaVirusDataService;

@Controller

public class HomeController {
	@Autowired

	CoronaVirusDataService coronaVirusDataService;

	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = coronaVirusDataService.getAllStats();
		int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalCases);

		return "home";
	}

	@GetMapping("/read")
	public String read() {
		return "read";
	}

}
