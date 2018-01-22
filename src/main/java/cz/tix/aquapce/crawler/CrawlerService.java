package cz.tix.aquapce.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
@EnableScheduling
public class CrawlerService {

    @Autowired
    private TrafficRepository trafficRepository;

    @Scheduled(cron = "0 */5 * * * *")
    public void getTraffic() throws IOException {
        Document doc = Jsoup.connect("http://www.aquapce.cz/").get();
        Elements infoTable = doc.select(".fast-info .table li span");
        Traffic traffic = new Traffic();
        traffic.setTimestamp(new Date());
        traffic.setPool(Integer.valueOf(infoTable.get(0).childNodes().get(0).outerHtml()));
        traffic.setAqua(Integer.valueOf(infoTable.get(1).childNodes().get(0).outerHtml()));
        traffic.setWellness(Integer.valueOf(infoTable.get(2).childNodes().get(0).outerHtml()));

        trafficRepository.save(traffic);

    }
}
