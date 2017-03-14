/* CityClient.java
 * 
 * Copyright (c) 2014 Qunar.com. All Rights Reserved. */
package CityClient;

import com.google.common.base.MoreObjects;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableSet;
import javafx.scene.layout.Priority;

import java.util.*;

/**
 * * CityClient is used to query information about a city. <br/>
 * * A city can be identified by either a cityId or a cityUrl, and both of them are overall unique. Every city belongs
 * to a single province, i.e. there is an one to many relationship between provinces and cites
 */
public class CityClient {
    Map<Integer, CityEntry> cityEntryMap = new HashMap<Integer, CityEntry>();     // 存放city ID与city映射
    Map<Integer, Province> provinceMap = new HashMap<Integer, Province>();      //存放privince ID与 province映射
    private BiMap<Integer, String> cityIdAndCityUrlBiMap = HashBiMap.create();    //存放双向映射

    public static Builder create() {
        return new Builder();
    }

    private CityClient(Builder builder) {
        cityEntryMap = builder.cityEntryMap;
        provinceMap = builder.provinceMap;
        cityIdAndCityUrlBiMap = builder.cityIdAndCityUrlBiMap;
        // TODO implement this;
    }

    /**
     * 淡新举
     * get all cities under an province identified by the given provinceId
     *
     * @param provinceId used to identify the target province
     * @return the set of cities in the target province, or empty city if the target province doesn't exist
     */
    public ImmutableSet<City> getCities(int provinceId) {
        List<City> citiesList = new ArrayList<City>();
        for (Map.Entry<Integer, CityEntry> entry : cityEntryMap.entrySet()) {
            if (cityEntryMap.get(entry.getKey()).getProvinceId() == provinceId) {
                int key = entry.getKey();
                City tempCity = new City(key
                        , cityEntryMap.get(key).getCityUrl(), cityEntryMap.get(key).getCityName());
                citiesList.add(tempCity);
            }
        }
        ImmutableSet<CityClient.City> cities = ImmutableSet.copyOf(new HashSet(citiesList));
        // TODO implement this
        return cities;
    }

    /**
     * get all provinces in the CityClient
     */
    public ImmutableSet<Province> getProvinces() {
        List<Province> provincesList = new ArrayList<Province>();
        for (Map.Entry<Integer, Province> entry : provinceMap.entrySet()) {
            provincesList.add(entry.getValue());
        }
        ImmutableSet<Province> provinces = ImmutableSet.copyOf(new HashSet(provincesList));
        // TODO implement this
        return provinces;
    }

    /**
     * query for the province a city belonging to
     *
     * @param cityId the id used to identify the city
     * @return the province that contains the city, or null if the city doesn't exist
     */
    public Province getProvinceFor(int cityId) {
        Province province = new Province(cityEntryMap.get(cityId).getProvinceId()
                , cityEntryMap.get(cityId).getProvinceName());// TODO implement this
        return province;
    }

    /**
     * find a province by its id
     *
     * @param provinceId the id used to query Province
     * @return the target province or null
     */
    public Province getProvince(int provinceId) {
        // TODO implement this
        return provinceMap.get(provinceId);
    }

    /**
     * find a city by its id
     */
    public City getCity(int cityId) {
        // TODO implement this
        CityEntry cityEntry = this.cityEntryMap.get(cityId);
        City city = new City(cityEntry.getCityId(), cityEntry.getCityUrl(), cityEntry.getCityName());
        return city;
    }

    /**
     * get corresponding cityUrl for a cityId
     *
     * @return the corresponding cityUrl or null if not exists
     */
    public String getCityUrlFor(int cityId) {
        // TODO implement this
        return cityEntryMap.get(cityId).getCityUrl();
    }

    /**
     * get corresponding cityId for a city
     *
     * @return the corresponding cityId or -1 if not exists
     */
    public int getCityIdFor(String cityUrl) {
        // TODO implement this
        return cityIdAndCityUrlBiMap.inverse().get(cityUrl);
    }

    public final static class Builder {
        Map<Integer, CityEntry> cityEntryMap = new HashMap<Integer, CityEntry>();
        Map<Integer, Province> provinceMap = new HashMap<Integer, Province>();
        private BiMap<Integer, String> cityIdAndCityUrlBiMap = HashBiMap.create();

        private Builder() {
        }

        public Builder cityEntry(CityEntry cityEntry) {
            cityEntryMap.put(cityEntry.getCityId(), cityEntry);
            Province province = new Province(cityEntry.getProvinceId(), cityEntry.getProvinceName());
            provinceMap.put(cityEntry.getProvinceId(), province);
            cityIdAndCityUrlBiMap.put(cityEntry.getProvinceId(), cityEntry.getCityUrl());
            // TODO implement this
            return this;
        }

        public Builder cityEntries(Iterable<CityEntry> cityEntries) {
            for (CityEntry cityEntry : cityEntries) {
                Province province = new Province(cityEntry.getProvinceId(), cityEntry.getProvinceName());
                cityEntryMap.put(cityEntry.getCityId(), cityEntry);
                provinceMap.put(cityEntry.getProvinceId(), province);
                cityIdAndCityUrlBiMap.put(cityEntry.getProvinceId(), cityEntry.getCityUrl());
            }
            // TODO implement this
            return this;
        }

        public CityClient build() {
            return new CityClient(this);
        }
    }

    public static class CityEntry {

        private int provinceId;

        private String provinceName;

        private int cityId;

        private String cityUrl;

        private String cityName;

        public int getProvinceId() {
            return provinceId;
        }

        public CityEntry setProvinceId(int provinceId) {
            this.provinceId = provinceId;
            return this;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public CityEntry setProvinceName(String provinceName) {
            this.provinceName = provinceName;
            return this;
        }

        public int getCityId() {
            return cityId;
        }

        public CityEntry setCityId(int cityId) {
            this.cityId = cityId;
            return this;
        }

        public String getCityUrl() {
            return cityUrl;
        }

        public CityEntry setCityUrl(String cityUrl) {
            this.cityUrl = cityUrl;
            return this;
        }

        public String getCityName() {
            return cityName;
        }

        public CityEntry setCityName(String cityName) {
            this.cityName = cityName;
            return this;
        }
    }

    public static class City {

        private final int cityId;

        private final String cityUrl;

        private final String name;

        public City(int cityId, String cityUrl, String name) {
            this.cityId = cityId;
            this.cityUrl = cityUrl;
            this.name = name;
        }

        public int getCityId() {
            return cityId;
        }

        public String getCityUrl() {
            return cityUrl;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof City))
                return false;

            City city = (City) o;

            if (cityId != city.cityId)
                return false;
            if (cityUrl != null ? !cityUrl.equals(city.cityUrl) : city.cityUrl != null)
                return false;
            if (name != null ? !name.equals(city.name) : city.name != null)
                return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = cityId;
            result = 31 * result + (cityUrl != null ? cityUrl.hashCode() : 0);
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).add("cityId", cityId).add("cityUrl", cityUrl).add("name", name)
                    .toString();
        }
    }

    public static class Province {

        private final int provinceId;

        private final String name;

        public Province(int provinceId, String name) {
            this.provinceId = provinceId;
            this.name = name;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Province))
                return false;

            Province province = (Province) o;

            if (provinceId != province.provinceId)
                return false;
            if (name != null ? !name.equals(province.name) : province.name != null)
                return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = provinceId;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).add("provinceId", provinceId).add("name", name).toString();
        }
    }

}
