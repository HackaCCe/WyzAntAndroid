package com.hackacce.WyzAntAndroid;

import org.jsoup.nodes.Element;

/**
 * A tutor object represents a search result of querying WyzAnt.com
 * A tutor has one field for each corresponding HTML element in a div of class "TutorResult"
 */
public class Tutor {
    String name, imageurl, location, distance, zip, hourlyRate, tagline, blurb, subjects, numRatings, responseTime,
    profile;

    public String getProfile() {
        return profile;
    }

    public Tutor(Element e) {
        name = e.getElementsByTag("h4").text();
        imageurl = e.select(".customTutorPic img").attr("src");
        location = e.getElementsByClass("location").text();
        distance = e.getElementsByClass("miles").text();
        zip = e.getElementsByClass("zip").text();
        tagline = e.getElementsByClass("txt-std").text();
        hourlyRate = e.getElementsByClass("hourly-rate").get(0).text();
        blurb = e.getElementsByClass("tutorFR").text();
        subjects = e.getElementsByClass("tutorSubjectList").text().trim();
        numRatings = e.getElementsByClass("starsdesc").text();
        responseTime = e.getElementsByClass("stat-response").text();
        profile = e.select(".tutorPicture a").attr("href");
    }

    public String getName() {
        return name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getLocation() {
        return location;
    }

    public String getDistance() {
        return distance;
    }

    public String getZip() {
        return zip;
    }

    public String getHourlyRate() {
        return hourlyRate;
    }

    public String getTagline() {
        return tagline;
    }

    public String getBlurb() {
        return blurb;
    }

    public String getSubjects() {
        return subjects;
    }

    public String getNumRatings() {
        return numRatings;
    }

    public String getResponseTime() {
        return responseTime;
    }

    @Override
    public String toString() {
        return name + " charges " + hourlyRate + " has pic " + imageurl + "\n" +
                subjects + " and says: " + tagline;
    }
}
