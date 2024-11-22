package com.pricehistory.dao;

import com.pricehistory.constant.Queries;
import com.pricehistory.model.FileLog;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Date;
import java.util.Optional;

@RegisterBeanMapper(FileLog.class)
public interface FileLogDAO {
    @SqlQuery(Queries.SELECT_FILE_LOGS_TODAY_NOT_BY_STATUS)
    Optional<Integer> findFileLogsTodayNotByStatus(@Bind("status") String status);

    @SqlUpdate(Queries.INSERT_FILE_LOG)
    void insertFileLog(@Bind("idConfig") int idConfig, @Bind("fileName") String fileName, @Bind("status") String status, @Bind("extractTime") Date extractTime, @Bind("totalRecords") int totalRecords);
}
