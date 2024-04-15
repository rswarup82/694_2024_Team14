#from sqlalchemy import create_engine, insert, text
import time
import psycopg2

from searchapp.model.identity import Identity

def get_user_db_connection():
    conn = psycopg2.connect(database="users", user="postgres", password="postgres", host="postgres", port="5432")
    return conn

def insert_into_identity_tbl(identity):
    conn = get_user_db_connection()
    cur = conn.cursor()
    cur.execute("""INSERT INTO identity (_name, surname) VALUES (%s, %s)""", (identity['_name'], identity['surname']))
    conn.commit()
    cur.close()
    conn.close()
    print('insert successfully!')

def find_from_identity_tbl(_name):
    conn = get_user_db_connection()
    cur = conn.cursor()
    cur.execute("SELECT * FROM identity WHERE _name = '%s'", (str(_name)))
    identity = cur.fetchall()
    cur.close()
    conn.close()
    print('search successfully!')
    return identity