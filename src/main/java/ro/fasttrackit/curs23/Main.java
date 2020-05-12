package ro.fasttrackit.curs23;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        RestTemplate rest = new RestTemplate();
        String result = rest.getForObject("https://restcountries.eu/rest/v2/all", String.class);
        System.out.println(result);

        Country oneCountry = rest.getForObject("https://restcountries.eu/rest/v2/alpha/ro", Country.class);
        System.out.println("Fetched country:");
        System.out.println(oneCountry);

        List<Country> allCountries = rest.exchange(
                "https://restcountries.eu/rest/v2/all",
                HttpMethod.POST,
                new HttpEntity<>(null),
                new ParameterizedTypeReference<List<Country>>() {
                })
                .getBody();

        allCountries.forEach(System.out::println);
    }

    private static MultiValueMap<String,String> headers() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authentication","jfjkdsla;fjask;hjior;esenio");
        headers.add("name","Ana");
        return headers;
    }
}

class Country {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                '}';
    }
}

