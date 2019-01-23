package com.mtclient.server;

import com.google.gson.Gson;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mtclient.client.mtclientService;
import com.mtclient.server.backend.entities.City;
import com.mtclient.server.backend.entities.Country;
import com.mtclient.server.backend.entities.Point;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class mtclientServiceImpl extends RemoteServiceServlet implements mtclientService {

    /**
     *  Получить отфильтрованные названия стран и городов
     */
    public String[] getSuggestListByFilter(String filter) throws Exception {

        List<String> suggestList = new ArrayList<String>();

        // Поиск стран по фильтру
        String json = readUrl("http://192.168.0.126:8083/countries?name="+filter);
        Country[] countries= new Gson().fromJson(json, Country[].class);
        for (Country country : countries) {
            suggestList.add(country.name);
        }

        // Поиск городов по фильтру
        json = readUrl("http://192.168.0.126:8083/cities?name=" + filter);
        City[] cities= new Gson().fromJson(json, City[].class);
        for (City city : cities) {
            suggestList.add(city.name);
        }

        return suggestList.toArray(new String[0]);
    }

    /**
     *  Получить отфильтрованный список пунктов денежных переводов
     */
    public String[] getPointsForCountryOrCity(String countryOrCityName) throws Exception {

        List<String> pointsList = new ArrayList<String>();

        String json = readUrl("http://192.168.0.126:8083/pointsForCountryOrCity?name="+countryOrCityName);
        Point[] points= new Gson().fromJson(json, Point[].class);
        Arrays.sort(points);
        for (Point point : points) {
            pointsList.add(point.city.country.name + ", "
                            + point.city.name + ", "
                            + point.pointType + ", "
                            + point.name + ", "
                            + point.address + ", "
                            + point.pointAbility);
        }
        return pointsList.toArray(new String[0]);
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
}