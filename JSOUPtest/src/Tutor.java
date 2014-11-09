import org.jsoup.nodes.Element;

/**
 * A tutor object represents a search result of querying WyzAnt.com
 * A tutor has one field for each corresponding HTML element in a div of class "TutorResult"
 */
public class Tutor {
    String name, imageurl, location, distance, zip, hourlyRate, tagline, blurb, subjects, numRatings, responseTime;

    public Tutor(Element e) {
        name = e.getElementsByTag("h4").text();
        imageurl = e.select(".customTutorPic img").attr("src");
        location = e.getElementsByClass("location").text();
        distance = e.getElementsByClass("distance").text();
        zip = e.getElementsByClass("zip").text();
        tagline = e.getElementsByClass("txt-std").text();
        hourlyRate = e.getElementsByClass("hourly-rate").get(0).text();
        blurb = e.getElementsByClass("tutorFR").text();
        subjects = e.getElementsByClass("tutorSubjectList").text().trim();
        numRatings = e.getElementsByClass("starsdesc").text();
        responseTime = e.getElementsByClass("stat-response").text();
    }

    @Override
    public String toString() {
        return name + " charges " + hourlyRate + " has pic " + imageurl + "\n" +
                subjects + " and says: " + tagline;
    }
}
