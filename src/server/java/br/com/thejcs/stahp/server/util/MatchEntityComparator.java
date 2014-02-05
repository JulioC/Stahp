package br.com.thejcs.stahp.server.util;

import br.com.thejcs.stahp.server.entity.MatchEntity;

import java.util.Comparator;

public class MatchEntityComparator implements Comparator<MatchEntity> {
    @Override
    public int compare(MatchEntity o1, MatchEntity o2) {
        if(o1.getUpdated() == null || o2.getUpdated() == null) {
            return 0;
        }

        return o1.getUpdated().compareTo(o2.getUpdated());
    }
}
