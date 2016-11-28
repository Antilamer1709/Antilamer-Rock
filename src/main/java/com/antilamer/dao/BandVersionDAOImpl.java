package com.antilamer.dao;

import com.antilamer.beans.band.BandSearchBean;
import com.antilamer.model.BandVersionDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.*;

@Transactional
@Repository
public class BandVersionDAOImpl extends AbstractJpaDAOImpl<BandVersionDTO> implements BandVersionDAO {

    @Override
    public List<BandVersionDTO> findAllById(BandSearchBean searchBean) {
//        String sql =
//                "select v"
//                        + " from BandVersionDTO v"
//                        + " where v.bandId = :bandId";
//
//        Query query = entityManager.createQuery(sql);
//        query.setParameter("username", username);
//        List<UserDTO> result = query.getResultList();
//        if(result != null && !result.isEmpty()) {
//            return result.get(0);
//        }
//        return null;
        Query query = makeBandHistoryQuery(searchBean);
        List<BandVersionDTO> result = query.getResultList();
        if (result != null && !result.isEmpty()) {
            return result;
        }
        return null;
    }

    private Query makeBandHistoryQuery(BandSearchBean searchBean) {
        StringBuilder jpaTemplateBuilder = new StringBuilder(
                "select v"
                        + " from BandVersionDTO v " +
                        " left outer join v.band band "
        );
        Map<String, Object> params = new TreeMap<String, Object>();
        jpaTemplateBuilder.append(" where 1=1 ");

        initBandHistoryFilter(searchBean, jpaTemplateBuilder, params);

        initBandHistoryOrderBy(jpaTemplateBuilder);

        Query query = entityManager.createQuery(jpaTemplateBuilder.toString());
        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }
        return query;
    }

    private void initBandHistoryFilter(BandSearchBean searchBean, StringBuilder jpaTemplateBuilder, Map<String, Object> params) {
        if (searchBean != null) {
            if (searchBean.getId() != null) {
                jpaTemplateBuilder.append(" and band.id = :bandId ");
                params.put("bandId", searchBean.getId());
            }
        }
    }

    private void initBandHistoryOrderBy(StringBuilder jpaTemplateBuilder) {
        jpaTemplateBuilder.append(" order by v.id").append(" desc");
    }

}
