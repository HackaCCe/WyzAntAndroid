import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JSOUPtest {
    public static void main (String args[]) throws IOException
    {
        Document doc = Jsoup.connect("http://www.wyzant.com/tutorsearch?kw=&z=90048").get();
        System.out.println(doc.title());
        Elements tutors = doc.getElementsByClass("TutorResult");
        for(int i=0;i < tutors.size(); i++) {
            System.out.print("#"+(i+1)+": ");
            Tutor t = new Tutor(tutors.get(i));
            System.out.println(t.toString());
        }
    }
}
