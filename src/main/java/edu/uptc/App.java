package edu.uptc;

import edu.uptc.dao.ActDao;
import edu.uptc.dao.MeetDao;
import edu.uptc.dao.RoomDao;
import edu.uptc.model.Act;
import edu.uptc.model.Meet;
import edu.uptc.model.Room;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        RoomDao roomDao = new RoomDao();

        ActDao actDao = new ActDao();

        MeetDao meetDao = new MeetDao();

        Room roomOne = new Room("Sala 1",(short)32);

        roomDao.save( roomOne );

        roomDao.save( new Room("Alcantuz",(short)45));
        roomDao.save( new Room("Silva Celis",(short)42));
        Room roomElectiva = new Room("Sala AA 207",(short)20);
        roomDao.save( roomElectiva );
        roomDao.save( new Room("Sala 10",(short)25));

        roomOne.setCapacity( (short)30 );
        roomDao.update( roomOne );

        Optional<Room> room = roomDao.findById(3);

        System.out.printf("************Datos de Consulta**********\n");

        if ( room.isPresent()){
            System.out.println( room.get());
        }else{
            System.out.println("No Existe");
        }

        System.out.printf("************Ver todos los Objetos**********\n");
        roomDao.findAll().forEach( System.out::println );

        System.out.printf("************Datos de Consultas**********\n");

        roomDao.findByCapacity((short)42).forEach( System.out::println);
        System.out.println("-------------");
        roomDao.findByCapacityBetween((short)20, (short)40).forEach( System.out::println);

        System.out.println("****************Reuniones**************+");
        Meet classElectiva = new Meet("Clase de Electiva-II", LocalDateTime.of(2022, Month.OCTOBER,22,18,0), roomElectiva );
        meetDao.save( classElectiva  );
        meetDao.save( new Meet("Clase de Sistemas Operativos",LocalDateTime.of(2022,Month.OCTOBER,25,16,0),roomElectiva));
        meetDao.findAll().forEach( System.out::println);

        Act actClass = new Act("Acta de la Clase, se realiza OK", classElectiva);
        actDao.save( actClass );

        Optional<Meet> meet = meetDao.findById( 1 );
        if( meet.isPresent()){
            System.out.printf("%s Numero Acta=%d\n",meet.get(),meet.get().getAct().getId());
        }

        System.out.println("****************Actas**************+");
        actDao.findAll().forEach( System.out::println );

        System.out.println("****************Consultar Salas**************+");
        Optional<Room> auxRoom = roomDao.findById( 4 );
        if( auxRoom.isPresent()){
            System.out.printf("La Sala %s Tiene las Reuniones Siguientes\n", auxRoom.get().getDescription());
            auxRoom.get().getMeets().forEach( System.out::println);
        }
    }
}
