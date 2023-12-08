package com.model;

public enum UserAction {
    DELETE( "DELETE" ),
    UPDATE( "UPDATE" ),
    CREATE( "CREATE" );

    private final String text;

    private UserAction( final String text ) {
        this.text = text;
    }

}
