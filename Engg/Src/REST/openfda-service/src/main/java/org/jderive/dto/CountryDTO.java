package org.jderive.dto;

import org.jderive.api.Country;
import org.jderive.domain.CountryDomain;
import org.springframework.beans.BeanUtils;

/**
 * Created by Durga on 6/20/2015.
 */
public final class CountryDTO {

    private CountryDTO() {

    }

    public static Country country(CountryDomain countryDomain) {
        Country country = new Country();
        BeanUtils.copyProperties(countryDomain, country);
        return country;
    }

    public static CountryDomain countryDomain(Country country) {
        CountryDomain countryDomain = new CountryDomain();
        BeanUtils.copyProperties(country, countryDomain);
        return countryDomain;
    }
}
