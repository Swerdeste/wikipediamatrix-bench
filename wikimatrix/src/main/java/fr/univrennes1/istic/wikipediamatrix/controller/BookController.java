package fr.univrennes1.istic.wikipediamatrix.controller;

import fr.univrennes1.istic.wikipediamatrix.link.Html;
import fr.univrennes1.istic.wikipediamatrix.link.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.univrennes1.istic.wikipediamatrix.visitor.CSVExportVisitor;
import org.json.* ;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/wikis/{page}")
public class BookController {

	@Autowired
	private CSVExportVisitor CSVExport;

	@GetMapping
	public org.json.JSONArray getCSV(String page) {
		Html html = new Html("https://en.wikipedia.org/wiki/"+page);
		List<String[]> list = CSVExport.export(html);
		String r = "";
		int i;
		for (i=0; i<list.size(); i++) {
			String s = Arrays.toString(list.get(i));
			for (int count=1; count < s.length()-1; count++) {
				r = r + s.charAt(count);
			}
			r = r + " \n";

		};
		JSONArray result = CDL.toJSONArray(r);
		return result;
	};

}

