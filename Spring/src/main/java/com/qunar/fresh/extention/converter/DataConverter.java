package com.qunar.fresh.extention.converter;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kingsley.zhang on 17-3-16.
 */
public class DataConverter implements Converter<String, Date> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataConverter.class);

    @Override
    public Date convert(String source) {
        Preconditions.checkNotNull(source);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(source);
        } catch (ParseException e) {
            LOGGER.error("日期转换错误，{}", e);
            throw new IllegalArgumentException(source);
        }
    }
}
