package com.example.food_ordering_firebase

import android.app.Activity
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DataBaseHelper (var ctx: Activity): SQLiteOpenHelper(ctx,"Food",null,1)
{
    override fun onCreate(db: SQLiteDatabase?) {
        var qry="create table userdata(id INTEGER primary key autoincrement,fname text,lname text,email text,password text,mno integer,bdate text)"
        db?.execSQL(qry)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {


    }
    fun insert(user:User):Long
    {
        var db=writableDatabase
        var cv= ContentValues()
        cv.put("fname",user.fnm)
        cv.put("lname",user.unm)
        cv.put("email",user.email)
        cv.put("password",user.password)
        cv.put("mno",user.mno)
        cv.put("bdate",user.bdate)
        return db.insert("userdata",null,cv)

    }
    fun login1(mail:String,pass:String):Int
    {
        var n:Int=0
        try {
            var db=readableDatabase
            var crsData: Cursor =db.rawQuery("select password from userdata where email='$mail' and password='$pass'",null)

            while (crsData.moveToNext()){
                n=1
            }
        }
        catch (ex:Exception){

            Toast.makeText(ctx,ex.toString(), Toast.LENGTH_LONG).show()
        }

        return n
    }

}