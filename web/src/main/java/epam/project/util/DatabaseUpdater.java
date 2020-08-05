package epam.project.util;

import epam.project.service.impl.BicycleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUpdater {

    private static Logger LOGGER = Logger.getLogger(DatabaseUpdater.class);

    @Autowired
    private BicycleService bicycleService;

    @Autowired
    private Parser parser;

    @Scheduled(cron = "*/5 * * * * ?")
    public void updateDB() {
//        try {
//            List<Bicycle> parseBicycles = parser.parse();
//            List<Bicycle> bicycles = bicycleService.takeAll();
//
//            int size = bicycles.size();
//            int parseSize = parseBicycles.size();
//
//            if (bicycles.isEmpty()) {
//                for (Bicycle parseBicycle : parseBicycles) {
//                    bicycleService.add(parseBicycle);
//                }
//            } else if (parseBicycles.size() > bicycles.size()) {
//                for (int i = size; i < parseSize; i++) {
//                    bicycleService.add(parseBicycles.get(i));
//                }
//            } else if (parseBicycles.size() < bicycles.size()) {
//                bicycles.removeAll(parseBicycles);
//                for (Bicycle bicycle : bicycles) {
//                    bicycleService.delete(bicycle);
//                }
//            }
//
//        } catch (ServiceException e) {
//            LOGGER.error("Failed update database", e);
//        }
    }
}
