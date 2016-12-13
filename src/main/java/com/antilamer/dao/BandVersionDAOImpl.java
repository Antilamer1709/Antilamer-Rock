package com.antilamer.dao;

import com.antilamer.dto.band.BandSearchDTO;
import com.antilamer.entity.BandVersionEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.*;

@Transactional
@Repository
public class BandVersionDAOImpl extends AbstractJpaDAOImpl<BandVersionEntity> implements BandVersionDAO {

    @Override
    public List<BandVersionEntity> findAllById(BandSearchDTO searhDTO) {
        Query query = makeBandHistoryQuery(searhDTO);
        List<BandVersionEntity> result = query.getResultList();
        if (result != null && !result.isEmpty()) {
            return result;
        }
        return null;
    }

    private Query makeBandHistoryQuery(BandSearchDTO searchBean) {
        StringBuilder jpaTemplateBuilder = new StringBuilder(
                "select v"
                        + " from BandVersionEntity v " +
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

    private void initBandHistoryFilter(BandSearchDTO searchBean, StringBuilder jpaTemplateBuilder, Map<String, Object> params) {
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
