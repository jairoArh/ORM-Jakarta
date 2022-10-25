package edu.uptc.dao;

import edu.uptc.model.Room;
import jakarta.persistence.Query;

import java.util.List;

public class RoomDao extends AbstracDao<Room>{
    public RoomDao() {
        setEntityClass( Room.class);
    }

    public List<Room> findByCapacity( short capacity ){
        String hQuery = "FROM Room WHERE capacity > ?1";

        Query query = getEntityManager().createQuery( hQuery );

        query.setParameter(1,capacity );

        return query.getResultList();
    }

    public List<Room> findByCapacityBetween( short min, short max ){
        String hQuery = "FROM Room WHERE capacity between ?1 and ?2";

        Query query = getEntityManager().createQuery( hQuery );

        query.setParameter(1,min );
        query.setParameter(2,max );

        return query.getResultList();
    }
}
