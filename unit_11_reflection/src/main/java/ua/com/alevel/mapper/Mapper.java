package ua.com.alevel.mapper;

import java.util.Properties;

public interface Mapper {

    <T> T mappingTheFile(Class<T> originalType, Properties singleObject);
}
