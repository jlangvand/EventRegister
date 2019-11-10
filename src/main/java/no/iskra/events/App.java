package no.iskra.events;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Arrays;
import no.iskra.database.Database;
import no.iskra.database.TerminalInterface;
import java.util.List;

/**
 * @Author: Joakim Skogø Langvand <jlangvand@gmail.com>
 */
class App {
  public static void main(String[] args) {
    System.out.println("EventManager 0.1 running on " + System.getProperty("os.name"));
    Database db;
    TerminalInterface terminal;

    try {
      /* Load database from file if possible */
      db = Database.loadFromFile("events");
      terminal = new TerminalInterface(db);
      System.out.println("Loaded database");
    } catch (Exception e) {
      /* We assume the file does not exist, so we create a new one */
      final Hashtable<String, String> fields = new Hashtable<String, String>();
      fields.put("name", "string");
      fields.put("type", "string");
      fields.put("location", "string");
      fields.put("start_date", "date");
      fields.put("end_date", "date");
      fields.put("start_time", "time");
      fields.put("end_time", "time");
      db = new Database("events");
      db.addTable("event", fields);
      System.out.println("Corrupt file or file not found; created new database");
      terminal = new TerminalInterface(db);

      /* Fill table with sample data */
      terminal.parseLine("new event name:abc,type:t1,location:l2,start_time:2300,end_time:1200,"
          + "start_date:2019-11-23,end_date:2019-01-12");
      terminal.parseLine("new event name:def,type:t1,location:l1,start_time:1200,end_time:1200,"
          + "start_date:2019-12-11,end_date:2019-01-12");
      terminal.parseLine("new event name:ghi,type:t2,location:l2,start_time:0300,end_time:1200,"
          + "start_date:2019-11-11,end_date:2019-01-12");
      terminal.parseLine("new event name:jkl,type:t2,location:l3,start_time:1500,end_time:1200,"
          + "start_date:2019-12-12,end_date:2019-01-12");
      terminal.parseLine("new event name:mno,,type:t3location:l2,start_time:1700,end_time:1200,"
          + "start_date:2019-10-13,end_date:2019-01-12");
      terminal.parseLine("new event name:pqr,type:t3,location:l1,start_time:2200,end_time:1200,"
          + "start_date:2019-11-11,end_date:2019-01-12");
      terminal.parseLine("new event name:stu,type:t1s,location:l3,start_time:1100,end_time:1200,"
          + "start_date:2019-11-10,end_date:2019-01-12");
    }

    /* All implicit commands should refer to the 'event' table */
    terminal.parseLine("select event");

    /* Print a list of stored events */
    System.out.println(db.getTableByName("event").printableTable());

    /* Hand over control to the command line interface */
    terminal.cli();
  }
}
