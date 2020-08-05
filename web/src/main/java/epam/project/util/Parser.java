package epam.project.util;

import epam.project.entity.Bicycle;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Parser {

    private static final Logger LOGGER = LogManager.getLogger(Parser.class);

    private List<Bicycle> bicycles;

    public List<Bicycle> parse() {
        bicycles = new ArrayList<>();
//        parseFromSpeedyGo();
//        parseFromProstoProkat();
        return bicycles;
    }

//    private void parseFromSpeedyGo() {
//
//        try {
//            Document doc = Jsoup.connect("https://speedygo.by/bicycle-rental/").get();
//            Elements bicycleNames = doc.select(".caption-item-bicycle");
//            Elements links = doc.select(".item-bicycle").select("a[href]");
//            PointHire pointHire=new PointHire();
//            pointHire.setId(1);
//
//            for (Element names : bicycleNames) {
//
//                Bicycle bicycle = new Bicycle();
//                bicycle.setPointHire(pointHire);
//                bicycle.setName(names.text());
//                bicycles.add(bicycle);
//            }
//            for (int i = 0; i < bicycles.size(); i++) {
//                bicycles.get(i).setLink(links.get(i).attr("abs:href"));
//                Document descriptionDoc = Jsoup.connect(links.get(i).attr("abs:href")).get();
//                Element description = descriptionDoc.getElementsByClass("clear-container product-description").first();
//                if (description != null && description.hasText()) {
//                    bicycles.get(i).setDescription(description.text());
//                }
//            }
//        } catch (IOException e) {
//            LOGGER.error("Failed to parse site", e);
//        }
//    }

//    private void parseFromProstoProkat() {
//
//        try {
//            Document doc = Jsoup.connect("https://prostoprokat.by").get();
//
//            Elements bicycleNames = doc.select(".sp-smart-title");
//            Elements bicycleDescription = doc.select(".slider-text");
//            PointHire pointHire=new PointHire();
//            pointHire.setId(2);
//
//            for (int i = 0; i < bicycleNames.size(); i++) {
//                Bicycle bicycle = new Bicycle();
//                bicycle.setName(bicycleNames.get(i).text());
//                bicycle.setPointHire(pointHire);
//                bicycle.setLink("https://prostoprokat.by");
//                bicycle.setDescription(bicycleDescription.get(i).text());
//                bicycles.add(bicycle);
//            }
//
//        } catch (IOException e) {
//            LOGGER.error("Failed to parse site", e);
//            System.out.println(e);
//        }
//
//    }

}
