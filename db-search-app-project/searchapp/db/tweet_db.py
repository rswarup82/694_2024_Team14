import time
pymongo
from pymongo import MongoClient

from searchapp.model.identity import Identity

def get_tweet_db_connection():
    mongoClient = MongoClient(host='mongodb', port='27017', username='root', password='pass', authSource="admin")
    db = mongoClient["final_project_latest"]
    return db

def insert_into_identity_collection(identity):
    db = get_tweet_db_connection()
    identity_collection = db["identity"]
    identity_collection.insert_one({ "_name" : identity.name, "surname" : identity.surname })
    print('insert successfully!')

def find_from_identity_collection(_name):
    db = get_tweet_db_connection()
    identity_collection = db["identity"]
    identities = db.identity_collection.find()
    return identities