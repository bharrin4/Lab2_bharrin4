package com.a40333.bharrin4.lab2_bharrin4;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by User on 3/22/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public SQLiteDatabase database;
    private static String DATABASE_NAME = "DBName";
    private static final int DATABASE_VERSION = 1;

    //Table Name
    private static final String GAMES_NAME = "games";
    public static final String TEAMS_NAME = "teams";
    public static final String IMAGES_NAME = "images";


    //column names
    private static final String GAME_COL_ID = "_id";
    private static final String GAME_TIME = "name";
    private static final String GAME_LOCATION = "location";
    private static final String G_OPPOSING_NAME = "opposing_name";
    private static final String SCORE_ID = "score_id";
    private static final String FINAL_SCORE = "final_score";
    private static final String DATE = "date";

    public static final String TEAM_COL_ID = "_id";
    private static final String T_OPPOSING_NAME = "opposing_name";
    private static final String OPPOSING_LOGO = "opposing_logo";
    private static final String OPPOSING_MASCOT = "opposing_mascot";
    private static final String OPPOSING_REC = "opposing_rec";
    private static final String ND_NAME = "nd_name";
    private static final String ND_LOGO = "nd_logo";
    private static final String ND_MASCOT = "nd_mascot";
    private static final String ND_REC = "nd_rec";

    public static final String IMAGE_COL_ID = "_id";
    public static final String IMAGE_TEAM_ID = "team_id";
    public static final String COL_IMAGE = "image";
    public static final String COL_URI = "uri";
    public static final String COL_DATE = "date";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        String G_DATABASE_CREATE = "CREATE TABLE " + GAMES_NAME + " ( "
                + GAME_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + GAME_TIME + " TEXT,"
                + GAME_LOCATION + " TEXT,"
                + G_OPPOSING_NAME + " TEXT,"
                + SCORE_ID + " TEXT,"
                + FINAL_SCORE + " TEXT,"
                + DATE + " TEXT )";
        database.execSQL(G_DATABASE_CREATE);

        String T_DATABASE_CREATE = "CREATE TABLE " + TEAMS_NAME + " ( "
                + TEAM_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + T_OPPOSING_NAME + " TEXT,"
                + OPPOSING_LOGO + " TEXT,"
                + OPPOSING_MASCOT + " TEXT,"
                + OPPOSING_REC + " TEXT,"
                + ND_NAME + " TEXT,"
                + ND_MASCOT + " TEXT,"
                + ND_REC + " TEXT,"
                + ND_LOGO + " TEXT,"
                + DATE + " TEXT )";
        database.execSQL(T_DATABASE_CREATE);

        String I_DATABASE_CREATE = "CREATE TABLE " + IMAGES_NAME + " ( "
                + IMAGE_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + IMAGE_TEAM_ID + " TEXT,"
                + COL_IMAGE + " BLOB, "
                + COL_DATE + " TEXT, "
                + COL_URI + " TEXT )";
        database.execSQL(I_DATABASE_CREATE);

    }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase database,int oldVersion,int newVersion){

        //Drop older table if existed
        database.execSQL("DROP TABLE IF EXISTS " + GAMES_NAME);
        database.execSQL("DROP TABLE IF EXISTS " + TEAMS_NAME);
        database.execSQL("DROP TABLE IF EXISTS " + IMAGES_NAME);

        //create tables again
        onCreate(database);
    }

    public long insertData(Game game, Team team) {
        SQLiteDatabase database = getWritableDatabase();

        //insert into Game table
        ContentValues contentValues = new ContentValues();
        //contentValues.put(COL_ID, game.get_id());
        contentValues.put(GAME_TIME, game.getGameTime());
        contentValues.put(GAME_LOCATION, game.getGameLocation());
        contentValues.put(G_OPPOSING_NAME, game.getOpposingName());
        contentValues.put(SCORE_ID, game.getScoreID());
        contentValues.put(FINAL_SCORE, game.getFinalString());
        contentValues.put(DATE, game.getDate());

        long ret = database.insert(GAMES_NAME, null, contentValues);

        if (ret > -1) {
            System.out.println("successfully inserted");
        } else {
            System.out.println("insert unsuccessful");
        }


        //insert into Team table
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(T_OPPOSING_NAME, team.getOpposingName());
        contentValues1.put(OPPOSING_LOGO, team.getOpposingLogo());
        contentValues1.put(OPPOSING_MASCOT, team.getOpposingMascot());
        contentValues1.put(OPPOSING_REC, team.getOpposingRec());
        contentValues1.put(ND_NAME, team.getNdName());
        contentValues1.put(ND_LOGO, team.getNdLogo());
        contentValues1.put(ND_MASCOT, team.getNdMascot());
        contentValues1.put(ND_REC, team.getNdRec());
        contentValues1.put(DATE, team.getDate());

        long ret1 = database.insert(TEAMS_NAME, null, contentValues1);

        if (ret1 > -1) {
            System.out.println("successfully inserted");
        } else {
            System.out.println("insert unsuccessful");
        }

        database.close();
        return ret1;
    }

    public void insertImageData(String tblName, ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();

        long ret = db.insert(tblName, null, contentValues );

        if (ret > 0) {
            System.out.println("Successfully inserted");
        } else {
            System.out.println("Insert Unsuccessful");
        }

        db.close();
    }

    public void deleteData(int _id) {
        database = getWritableDatabase();
        database.delete(GAMES_NAME, " _id = ?", new String[]{Integer.toString(_id)});
        database.delete(TEAMS_NAME, " _id = ?", new String[]{Integer.toString(_id)});
        database.delete(IMAGES_NAME, " _id = ?", new String[]{Integer.toString(_id)});

        database.close();
    }


    public ArrayList<Game> returnGames() {
        database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + GAMES_NAME, new String[]{});

        ArrayList<Game> games = new ArrayList<Game>();

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                do {
                    String [] gameObject = new String [6];

                    gameObject[0] = (cursor.getString(cursor.getColumnIndex(G_OPPOSING_NAME)));
                    gameObject[1] = (cursor.getString(cursor.getColumnIndex(GAME_TIME)));
                    gameObject[2] = (cursor.getString(cursor.getColumnIndex(GAME_LOCATION)));
                    gameObject[3] = (cursor.getString(cursor.getColumnIndex(SCORE_ID)));
                    gameObject[4] = (cursor.getString(cursor.getColumnIndex(FINAL_SCORE)));
                    gameObject[5] = (cursor.getString(cursor.getColumnIndex(DATE)));

                    Game game = new Game(gameObject);

                    games.add(game);

                }while (cursor.moveToNext());
            }
        }

        cursor.close();

        return games;
    }

    public long returnTeamID() {
        database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TEAMS_NAME, new String[]{});

        Long ID = cursor.getLong(cursor.getColumnIndex("_id"));
        return ID;

    }

    public ArrayList<Team> returnTeams() {
        database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TEAMS_NAME, new String[]{});

        ArrayList<Team> teams = new ArrayList<Team>();

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                do {
                    String [] teamObject = new String [9];

                    teamObject[0] = (cursor.getString(cursor.getColumnIndex(T_OPPOSING_NAME)));
                    teamObject[1] = (cursor.getString(cursor.getColumnIndex(OPPOSING_LOGO)));
                    teamObject[2] = (cursor.getString(cursor.getColumnIndex(OPPOSING_MASCOT)));
                    teamObject[3] = (cursor.getString(cursor.getColumnIndex(OPPOSING_REC)));
                    teamObject[4] = (cursor.getString(cursor.getColumnIndex(ND_NAME)));
                    teamObject[5] = (cursor.getString(cursor.getColumnIndex(ND_MASCOT)));
                    teamObject[6] = (cursor.getString(cursor.getColumnIndex(ND_REC)));
                    teamObject[7] = (cursor.getString(cursor.getColumnIndex(ND_LOGO)));
                    teamObject[8] = (cursor.getString(cursor.getColumnIndex(DATE)));

                    Team team = new Team(teamObject);

                    teams.add(team);

                }while (cursor.moveToNext());
            }
        }

        cursor.close();

        return teams;
    }

    public Cursor getAllEntries(String tblName, String[] columns) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tblName, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getSelectEntries(String tblName, String[] columns, String where, String[] args, String orderBy) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tblName, columns, where, args, null, null, orderBy);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public String[] getTableFields(String tblName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor dbCursor = db.query(tblName, null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();
        return columnNames;
    }

    //write function to return ArrayList -- put all row contents into an ArrayList
}
