package com.jiyun.demo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

    private static UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.abc","table1",0);
        uriMatcher.addURI("com.abc","table1/#",1);
        uriMatcher.addURI("com.abc","person",2);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        switch (uriMatcher.match(uri)){
            case 0:
                //table1
                break;
            case 1:
                //table1 / id
                break;
            case 2:
                //person
                break;
        }
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case 0:
                //table1
                return "vnd.android.cursor.dir/vnd.com.abc.table1";
            case 1:
                //table1 / id
                return "vnd.android.cursor.item/vnd.com.abc.table1";
            case 2:
                //person
               return "vnd.android.cursor.dir/vnd.com.abc.person";
        }
        return "";
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri)){
            case 0:
                //table1
                break;
            case 1:
                //table1 / id
                break;
            case 2:
                //person
                break;
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)){
            case 0:
                //table1
                break;
            case 1:
                //table1 / id
                break;
            case 2:
                //person
                break;
        }
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        switch (uriMatcher.match(uri)){
            case 0:
                //table1
                break;
            case 1:
                //table1 / id
                break;
            case 2:
                //person
                break;
        }
        return 0;
    }
}
