/**
 *
 */
package com.csf.whoami.template;

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;

import org.springframework.stereotype.Component;

/**
 * Extends class get playlist status.
 *
 * @author at-tuandang
 */
@Component
public class PlaylistRepositoryExtendsImpl implements PlaylistRepositoryExtends {

//    /** Entity manager. */
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    /**
//     * Get playlist status in next 7 days.
//     * 
//     * @author at-tuandang
//     * @param channelId
//     * @return
//     * @throws Exception 
//     */
//    @SuppressWarnings("unchecked")
//    public List<PlaylistAndStatusDomain> getPlaylistInWeek(Long channelId) throws Exception {
//
//        List<String> tables = new ArrayList<>();
//        tables.add("PlaylistEntity pl");
//        tables.add("INNER JOIN PlaylistMediaEntity pm on pl.id = pm.playlistId and pm.isDeleted = "
//                + DatabaseConstant.IsDeleted.FALSE.getValue());
//
//        List<String> columns = new ArrayList<>();
//        columns.add("pm.playlistId");
//        columns.add("pl.playlistDate");
//        columns.add("pl.status");
//        columns.add("case when pm.isError = 1 then '2' " + 
//                "    when (" +
//                "            EXTRACT(hour from to_timestamp(pl.duration, 'HH24:MI:SS:MS')) * 60 * 60 \n" + 
//                "           + EXTRACT(minute from to_timestamp(pl.duration, 'HH24:MI:SS:MS')) * 60 \n" + 
//                "           + EXTRACT(second from to_timestamp(pl.duration, 'HH24:MI:SS:MS')) " + 
//                "        ) " +
//                "       = SUM(" +
//                "            EXTRACT(hour from to_timestamp(pm.duration, 'HH24:MI:SS:MS')) * 60*60 \n" + 
//                "            + EXTRACT(minute from to_timestamp(pm.duration, 'HH24:MI:SS:MS')) * 60 \n" + 
//                "            + EXTRACT(second from to_timestamp(pm.duration, 'HH24:MI:SS:MS')) " +
//                "       ) then '0' " +
//                "    else '1' " + 
//                "    end as valid_status");
//
//        List<String> conditions = new ArrayList<>();
//        conditions.add("pl.isDeleted = " + DatabaseConstant.IsDeleted.FALSE.getValue());
//        String filterConditions = createFilterInputRequest(channelId);
//        String groupBy = "GROUP BY pm.playlistId, pl.duration, pl.playlistDate, pm.isError, pl.status";
//        String orderBy = " ORDER BY pm.playlistId";
//        String sql = StringUtils.buildSelectQuery(columns, tables, conditions) + filterConditions + groupBy + orderBy;
//
//        Query query = this.entityManager.createQuery(sql);
//
//        Map<String, Object> listParams = createParamsGetPlaylists(channelId);
//        listParams.forEach((key, value) -> {
//            query.setParameter(key, value);
//        });
//
//        List<Object[]> results = null;
//        results = query.getResultList();
//
//        List<PlaylistAndStatusDomain> playlistAndStatus = new ArrayList<>();
//        PlaylistAndStatusDomain playlistItem = null;
//        for(Object[] rs: results) {
//            playlistItem = convertObjectToPlaylistAndStatusDomain(rs, columns);
//            playlistAndStatus.add(playlistItem);
//        }
//
//        return playlistAndStatus;
//    }
//
//    /**
//     * Create filter value.
//     * 
//     * @author at-tuandang
//     * @param channelId
//     * @return
//     */
//    private String createFilterInputRequest(Long channelId) {
//        StringBuilder conditions = new StringBuilder("");
//        conditions.append(" AND pl.channelId = :channel ");
//        conditions.append(" AND CAST(:fromDate AS date) <= CAST(pl.playlistDate AS date)");
//        conditions.append(" AND CAST(pl.playlistDate AS date) < cast(:toDate AS date)");
//        return conditions.toString();
//    }
//
//    /**
//     * Convert record to Playlist domain.
//     * 
//     * @author at-tuandang
//     * @param recordData
//     * @param columns
//     * @return
//     */
//    private PlaylistAndStatusDomain convertObjectToPlaylistAndStatusDomain(Object[] recordData, List<String> columns) {
//
//        PlaylistAndStatusDomain domain = new PlaylistAndStatusDomain();
//        List<String> listResult = Arrays.asList(recordData).stream().map(
//                t -> t == null ? null : t.toString()).collect(Collectors.toList());
//        domain.setId(listResult.get(columns.indexOf("pm.playlistId")));
//        domain.setTime(listResult.get(columns.indexOf("pl.playlistDate")));
//        domain.setUploadStatus(listResult.get(columns.indexOf("pl.status")));
//        for (String col : columns) {
//            if (col.contains("valid_status")) {
//                domain.setValidStatus(listResult.get(columns.indexOf(col)));
//            }
//        }
//        return domain;
//    }
//
//    /**
//     * Create params get playlists.
//     * 
//     * @author at-tuandang
//     * @param channelId
//     * @return
//     * @throws Exception
//     */
//    private Map<String, Object> createParamsGetPlaylists(Long channelId) throws  Exception {
//        Map<String, Object> listParams = new HashMap<>();
//        Date currentDate = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(currentDate);
//        calendar.add(Calendar.DATE, 7);
//        Date nextWeekDate = calendar.getTime();
//
//        listParams.put("channel", channelId);
//        listParams.put("fromDate", currentDate);
//        listParams.put("toDate", nextWeekDate);
//        return listParams;
//    }
}
