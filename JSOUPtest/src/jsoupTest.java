import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Beast on 11/8/2014.
 */
public class JSOUPtest {

    public static void main (String args[]) throws IOException
    {
        Document doc = Jsoup.connect("http://www.wyzant.com/tutorsearch?kw=&z=90048").get();
       Elements tutors = doc.getElementsByClass("TutorResult");
       for(int i=0;i < tutors.size(); i++) {
           System.out.println("Tutor #"+(i+1));
           System.out.println(tutors.get(i).text());
       }
    }

}
