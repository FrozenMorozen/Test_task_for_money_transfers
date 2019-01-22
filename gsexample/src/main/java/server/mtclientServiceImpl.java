package server;

import com.google.gson.Gson;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mtclient.client.mtclientService;
import com.mtclient.server.backend.entities.Country;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class mtclientServiceImpl extends RemoteServiceServlet implements mtclientService {

    private List<String> suggestions;

    // Implementation of sample interface method
    public String getMessage(String msg) throws Exception {

        String json = readUrl("http://localhost:8083/countries");

        Gson gson = new Gson();
        Page page = gson.fromJson(json, Page.class);

        // System.out.println(page.title);
        for (Country country : page.countries){
            return "    " + country.name;
        }
        return null;

        //return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }

    static class Item {
        String title;
        String link;
        String description;
    }

    static class Page {

        List<Country> countries;
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    public List<String> getSuggestListByFilter(String filter) {
        databaseInit();
        List<String> filterData = suggestions;

        for (String data: suggestions) {
            if (!data.substring(1,filter.length()).equals(filter)) {
                filterData.remove(data);
            }
        }

        for (String data: filterData) {
            System.out.println(data);
        }
        return filterData;
    }

    private void databaseInit(){
        suggestions.add("A");
        suggestions.add("AB");
        suggestions.add("ABC");
        suggestions.add("ABCD");
        suggestions.add("B");
        suggestions.add("BC");
        suggestions.add("BCD");
        suggestions.add("BCDE");
        suggestions.add("C");
        suggestions.add("CD");
        suggestions.add("CDE");
        suggestions.add("CDEF");
        suggestions.add("D");
        suggestions.add("DE");
        suggestions.add("DEF");
        suggestions.add("DEFG");
    }
}