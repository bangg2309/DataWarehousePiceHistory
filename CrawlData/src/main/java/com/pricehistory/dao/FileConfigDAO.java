package com.pricehistory.dao;

import com.pricehistory.constant.Queries;
import com.pricehistory.model.FileConfig;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.Optional;

@RegisterBeanMapper(FileConfig.class)
public interface FileConfigDAO {
    @SqlQuery(Queries.SELECT_FILE_CONFIG_BY_SOURCE_NAME)
    Optional<FileConfig> findFileConfig(@Bind("sourceName") String sourceName);
}
